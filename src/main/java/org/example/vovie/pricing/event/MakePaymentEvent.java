package org.example.vovie.pricing.event;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.movie.core.common.booking.BookingInformation;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class MakePaymentEvent {
    private String uniqueId;
    private BookingInformation bookingInformation;
}
