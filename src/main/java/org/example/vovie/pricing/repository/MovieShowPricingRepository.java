package org.example.vovie.pricing.repository;

import org.example.vovie.pricing.entity.MovieShowPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieShowPricingRepository extends JpaRepository<MovieShowPricing, Integer> {
    public Optional<MovieShowPricing> getMovieShowPricingByShowScheduleUniqueId(String showScheduleUniqueId);
}
