package com.bamate.bamatebackend.offer;

public class OfferNotFoundException extends RuntimeException{
    OfferNotFoundException(Long id) {
        super("Could not find offer " + id);
    }
}
