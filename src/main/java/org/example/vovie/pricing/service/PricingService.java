package org.example.vovie.pricing.service;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.movie.core.common.booking.BookingInformation;
import org.example.movie.core.common.booking.PricingRequest;
import org.example.vovie.pricing.command.MakePaymentCommand;
import org.example.vovie.pricing.entity.MovieShowPricing;
import org.example.vovie.pricing.entity.MovieUserPricing;
import org.example.vovie.pricing.repository.MovieShowPricingRepository;
import org.example.vovie.pricing.repository.MovieUserPricingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PricingService {

    private final MovieShowPricingRepository movieShowPricingRepository;
    private final MovieUserPricingRepository movieUserPricingRepository;
    private final CommandGateway commandGateway;

    public void calculatePrice(String uniqueId, BookingInformation bookingInformation){
        MovieShowPricing movieShowPricing = movieShowPricingRepository
                .getMovieShowPricingByShowScheduleUniqueId(bookingInformation.getScheduleUniqueId())
                .orElseGet(MovieShowPricing::new);

        if(movieShowPricing.getMspId()>=0){
            float discount=0;
            if(Optional
                 .ofNullable(movieUserPricingRepository
                    .getAllByUserIdentificationAndMoviePriceUniqueId(
                            bookingInformation.getUserUniqueIdentification(),
                            movieShowPricing.getUniquePricingId()))
                 .orElseGet(ArrayList::new)
                 .size()==2){
                discount=50;
            }
            MovieUserPricing movieUserPricing = new MovieUserPricing();
            movieUserPricing.setMoviePriceUniqueId(movieShowPricing.getUniquePricingId());
            movieUserPricing.setUserUnitPrice(movieShowPricing.getUnitPrice());
            movieUserPricing.setDiscount(discount);
            movieUserPricing.setTotalPrice(
                    bookingInformation.getTotalNoOfSeat()*movieShowPricing.getUnitPrice()
                    *(1-(discount/100))
            );
            movieUserPricing.setUserIdentification(bookingInformation.getUserUniqueIdentification());
            movieUserPricing.setUserPriceUniqueId(UUID.randomUUID().toString());
            movieUserPricing.setPaymentStatus("P");
            movieUserPricingRepository.save(movieUserPricing);
            bookingInformation.setPricingRequest(
                    PricingRequest.of()
                            .setPricingUniqueId(movieUserPricing.getUserPriceUniqueId())
                            .setTotalPrice(movieUserPricing.getTotalPrice())
            );
            commandGateway.send(MakePaymentCommand
                    .of()
                    .setUniqueId(uniqueId)
                    .setBookingInformation(bookingInformation));
        }
    }

}
