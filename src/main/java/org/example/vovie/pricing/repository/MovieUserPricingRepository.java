package org.example.vovie.pricing.repository;

import org.example.vovie.pricing.entity.MovieShowPricing;
import org.example.vovie.pricing.entity.MovieUserPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieUserPricingRepository extends JpaRepository<MovieUserPricing, Integer> {
    public List<MovieUserPricing> getAllByUserIdentificationAndMoviePriceUniqueId(
            String userIdentification,String moviePriceUniqueId
    );
}
