package com.epam.reportservice.controller;

import com.epam.reportservice.model.dto.TrainerSummaryDto;
import com.epam.reportservice.service.TrainerSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trainer-summary")
@RequiredArgsConstructor
public class TrainerSummaryController {
    private final TrainerSummaryService trainerSummaryService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TrainerSummaryDto trainerSummaryDto) {
        trainerSummaryService.create(trainerSummaryDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
