package com.epam.gym.feign;

import com.epam.gym.config.ReportClientConfiguration;
import com.epam.gym.model.dto.request.TrainerSummaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "report-client", url = "http://localhost:8081/trainer-summary",configuration = ReportClientConfiguration.class)
public interface ReportClient {
    @PostMapping
    ResponseEntity<Void> summary(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody TrainerSummaryDto trainerSummaryDto);

    @GetMapping
    ResponseEntity<String>test(@RequestHeader(value = "Authorization") String token);
}
