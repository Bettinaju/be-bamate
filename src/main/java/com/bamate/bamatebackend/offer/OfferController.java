package com.bamate.bamatebackend.offer;

import com.bamate.bamatebackend.offer.models.Offer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferRepository repository;

    OfferController(OfferRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Offer> allOffers() {
        return repository.findAll();
    }

    @PostMapping
    Offer newOffer(@RequestBody Offer newOffer) {
        return repository.save(newOffer);
    }

    // Single item

    @GetMapping("/{id}")
    Offer one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
