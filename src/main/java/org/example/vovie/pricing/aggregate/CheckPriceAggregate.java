package org.example.vovie.pricing.aggregate;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.vovie.pricing.command.MakePaymentCommand;
import org.example.vovie.pricing.event.MakePaymentEvent;
import org.springframework.beans.BeanUtils;

@Slf4j
@Aggregate
@NoArgsConstructor
public class CheckPriceAggregate {

    @AggregateIdentifier
    private String uniqueId;

   @CommandHandler
    public void handleCalculateMakePayment(MakePaymentCommand makePaymentCommand) {
        MakePaymentEvent makePaymentEvent = MakePaymentEvent.of();
        BeanUtils.copyProperties(makePaymentCommand, makePaymentEvent);
        AggregateLifecycle.apply(makePaymentEvent);
    }

    @EventSourcingHandler
    public void on(MakePaymentEvent makePaymentEvent) {
        log.info("ReserveSeatEvent Sourcing Done");
        this.uniqueId = makePaymentEvent.getUniqueId();
    }
}
