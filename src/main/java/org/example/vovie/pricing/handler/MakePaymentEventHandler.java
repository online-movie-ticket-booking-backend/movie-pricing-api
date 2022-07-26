package org.example.vovie.pricing.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.example.movie.core.common.booking.BookingInformation;
import org.example.vovie.pricing.event.MakePaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@Slf4j
@Component
public class MakePaymentEventHandler {

    @Value("${kafka.movieBookingApi.makePayment.topic.request}")
    private String makePaymentTopicName;

    private KafkaTemplate<String, BookingInformation> kafkaTemplateBookingInformation;

    @Autowired
    public void setKafkaTemplateBookingInformation(
            KafkaTemplate<String, BookingInformation> kafkaTemplateBookingInformation){
        this.kafkaTemplateBookingInformation=kafkaTemplateBookingInformation;
    }

    @EventHandler
    public void on(MakePaymentEvent makePaymentEvent)
            throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplateBookingInformation.send(makePaymentTopicName,
                makePaymentEvent.getUniqueId(),
                makePaymentEvent.getBookingInformation());
    }
}
