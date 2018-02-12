package pl.lukk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.lukk.entity.Offer;

public interface AvailabilityService
{


    List<LocalDateTime> findAvailableDatesByOffersId(Long id);
    
}
