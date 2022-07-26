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
@Table(name = "movie_user_pricing")
public class MovieUserPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mup_id")
    private int mup_id;

    @Column(name = "user_unit_price")
    private int userUnitPrice;

    @Column(name = "seat_count")
    private int seatCount;

    @Column(name = "discount")
    private float discount;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "unit_price_unique_id")
    private String userPriceUniqueId;

    @Column(name = "movie_price_unique_id")
    private String moviePriceUniqueId;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name="user_identification")
    private String userIdentification;
}
