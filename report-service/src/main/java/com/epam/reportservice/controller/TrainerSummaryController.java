package com.epam.reportservice.controller;

import com.epam.reportservice.model.dto.TrainerSummaryDto;
import com.epam.reportservice.model.entity.TrainerSummary;
import com.epam.reportservice.service.TrainerSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trainer-summary")
@RequiredArgsConstructor
public class TrainerSummaryController {
    private final TrainerSummaryService trainerSummaryService;

    @PostMapping
    public void summary(@RequestBody TrainerSummaryDto trainerSummaryDto) {
        trainerSummaryService.summary(trainerSummaryDto);
    }

    @GetMapping
    public ResponseEntity<TrainerSummary> getByUsername(@RequestParam String username) {
        return new ResponseEntity<>(trainerSummaryService.getByUsername(username), HttpStatus.OK);
    }
}
