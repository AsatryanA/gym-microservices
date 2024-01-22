package com.epam.gym.service;

import com.epam.gym.model.Training;
import com.epam.gym.model.dto.request.TrainingRequestDTO;

public interface TrainingService {
    void create(TrainingRequestDTO trainingRequestDTO);

    void delete(Training training);
}
