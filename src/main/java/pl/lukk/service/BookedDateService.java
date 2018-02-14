package pl.lukk.service;

import java.util.List;

import pl.lukk.entity.BookedDate;

public interface BookedDateService
{

    List<BookedDate> findAll();

    List<String> findAvailableDatesByOffersId(Long offerId);

    List<BookedDate> findAllBookedDatesByOffer(Long offerId);

    void bookDates(List<String> datesToBookList, Long offerId, String userEmail);

}
