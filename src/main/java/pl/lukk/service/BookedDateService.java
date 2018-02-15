package pl.lukk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.lukk.entity.BookedDate;

public interface BookedDateService
{

    List<BookedDate> findAll();

    List<BookedDate> findAllBookedDatesByOffer(Long offerId);

    void bookDates(List<String> datesToBookList, Long offerId, String userEmail);

    Page<String> findAvailableDatesByOffersId(Long offerId, Integer page);

}
