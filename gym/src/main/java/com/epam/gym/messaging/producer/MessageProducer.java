package com.epam.gym.messaging.producer;

import com.epam.gym.aspect.MdcInterceptor;
import com.epam.gym.model.dto.request.TrainerSummaryDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;

@Service
@RequiredArgsConstructor
public class MessageProducer {
    private final JmsTemplate jmsTemplate;

    public void sendMessage(TrainerSummaryDto trainerSummary) {
        jmsTemplate.send("training-summary-queue", session -> {
            var message = jmsTemplate.getMessageConverter().toMessage(trainerSummary, session);
            message.setJMSCorrelationID(MDC.get(MdcInterceptor.TRANSACTION_ID));
            return message;
        });
    }
}
