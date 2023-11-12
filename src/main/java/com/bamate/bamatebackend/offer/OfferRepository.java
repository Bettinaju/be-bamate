package com.bamate.bamatebackend.offer;

import com.bamate.bamatebackend.offer.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
