package com.epam.gym.service;

import com.epam.gym.mapper.TrainerSummaryMapper;
import com.epam.gym.model.dto.TrainerSummaryDto;
import com.epam.gym.model.entity.TrainerSummary;
import com.epam.gym.repository.TrainerSummaryRepo;
import com.epam.gym.service.impl.TrainerSummaryServiceImpl;
import com.epam.gym.util.constants.ActionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TrainerSummaryServiceImplTest {

    @Mock
    private TrainerSummaryRepo trainerSummaryRepo;
    @Mock
    private TrainerSummaryMapper trainerSummaryMapper;
    @InjectMocks
    private TrainerSummaryServiceImpl trainerSummaryService;

    @Test
    void testUpdateSummary() {
        TrainerSummaryDto trainerSummaryDto = new TrainerSummaryDto();
        trainerSummaryDto.setUsername("testUser");
        trainerSummaryDto.setActionType(ActionType.ADD);
        trainerSummaryDto.setDuration(5);
        trainerSummaryDto.setDate(LocalDate.of(2022, 1, 10));
        Map<Integer, Map<Integer, Integer>> year = new HashMap<>();
        Map<Integer, Integer> month = new HashMap<>();
        month.put(1, 10);
        year.put(2022, month);
        TrainerSummary existingTrainer = TrainerSummary.builder()
                .username("testUser")
                .duration(year)
                .build();

        when(trainerSummaryRepo.findByUsername("testUser")).thenReturn(existingTrainer);
        when(trainerSummaryMapper.toTrainerSummary(trainerSummaryDto)).thenReturn(existingTrainer);
        when(trainerSummaryRepo.save(any(TrainerSummary.class))).thenReturn(existingTrainer);
        trainerSummaryService.updateSummary(trainerSummaryDto);
        assertEquals(15, existingTrainer.getDuration().get(2022).get(1));
    }

    @Test
    void testGetByFirstnameAndLastname() {
        String firstname = "John";
        String lastname = "Doe";
        when(trainerSummaryRepo.findByFirstNameAndLastName(firstname, lastname))
                .thenReturn(Collections.singletonList(TrainerSummary.builder().build()));
        List<TrainerSummary> result = trainerSummaryService.getByFirstnameAndLastname(firstname, lastname);
        assertEquals(1, result.size());
    }
}
