package com.epam.gym.messaging.producer;


import com.epam.gym.model.dto.request.TrainerSummaryDto;
import com.epam.gym.service.TrainerSummaryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageConsumer {
    private final TrainerSummaryService trainerSummaryService;

    @JmsListener(destination = "training-summary-queue")
    public void receiveMessage(TrainerSummaryDto trainerSummaryDto, @Header("jms_correlationId") String correlationId) {
        MDC.put("transactionId", correlationId);
        trainerSummaryService.summary(trainerSummaryDto);
        MDC.clear();
    }
}
