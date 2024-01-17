package com.epam.reportservice.controller;

import com.epam.reportservice.model.dto.TrainerSummaryDto;
import com.epam.reportservice.service.TrainerSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer-summary")
@RequiredArgsConstructor
public class TrainerSummaryController {
    private final TrainerSummaryService trainerSummaryService;

    @PostMapping
    public ResponseEntity<Void> summary(@RequestBody TrainerSummaryDto trainerSummaryDto) {
        trainerSummaryService.summary(trainerSummaryDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // fake endpoint for testing circuit breaker
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Ok");
    }

}
