package com.epam.gym.feign;

import com.epam.gym.config.ReportClientConfiguration;
import com.epam.gym.model.dto.request.TrainerSummaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "report-service", path = "/api/v1/trainer-summary", configuration = ReportClientConfiguration.class)
public interface ReportService {
    @PostMapping
    void summary(@RequestBody TrainerSummaryDto trainerSummaryDto);
}
