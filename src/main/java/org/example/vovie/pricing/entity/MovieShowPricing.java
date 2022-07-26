package org.example.vovie.pricing.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "movie_show_pricing")
public class MovieShowPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mspId;

    @Column(name = "show_schedule_unique_id")
    private String showScheduleUniqueId;

    @Column(name = "unit_price")
    private int unitPrice;

    @Column(name = "unique_price_id")
    private String uniquePricingId;
}
