package com.epam.gym.service.impl;

import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.mapper.TrainingMapper;
import com.epam.gym.messaging.producer.MessageProducer;
import com.epam.gym.model.Trainee;
import com.epam.gym.model.Trainer;
import com.epam.gym.model.Training;
import com.epam.gym.model.dto.request.TrainerSummaryDto;
import com.epam.gym.model.dto.request.TrainingRequestDTO;
import com.epam.gym.repository.TraineeRepository;
import com.epam.gym.repository.TrainerRepository;
import com.epam.gym.repository.TrainingRepository;
import com.epam.gym.service.TrainingService;
import com.epam.gym.util.ActionType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final MessageProducer messageProducer;

    @Override
    @Transactional
    @CircuitBreaker(name = "create-summary", fallbackMethod = "failCreation")
    public void create(TrainingRequestDTO trainingRequestDTO) {
        var training = trainingMapper.toTraining(trainingRequestDTO);
        var trainee = traineeRepository.findById(trainingRequestDTO.getTraineeId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainee.class, trainingRequestDTO.getTraineeId()));
        var trainer = trainerRepository.findById(trainingRequestDTO.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException(Trainer.class, trainingRequestDTO.getTrainerId()));
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        trainingRepository.save(training);
        messageProducer.sendMessage(
                toTrainerSummaryDto(training, ActionType.ADD));
    }

    @Override
    public void delete(Training training) {
        messageProducer.sendMessage(
                toTrainerSummaryDto(training, ActionType.DELETE));
    }

    private TrainerSummaryDto toTrainerSummaryDto(Training training, ActionType actionType) {
        return TrainerSummaryDto.builder()
                .username(training.getTrainer().getUser().getUsername())
                .firstName(training.getTrainer().getUser().getFirstName())
                .lastName(training.getTrainer().getUser().getLastName())
                .isActive(training.getTrainer().getUser().getIsActive())
                .duration(training.getDuration())
                .actionType(actionType)
                .date(training.getDate())
                .build();
    }

    private void failCreation(Throwable e) {
        throw new ServerErrorException("Oops! Our service is experiencing technical issues");
    }
}
