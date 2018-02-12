package pl.lukk.service;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilityService
{

    List<LocalDateTime> findAvailableDatesByOffersId(Long id);

}
