package pl.lukk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.lukk.entity.BookedDate;

public interface BookedDateService
{

    /**
     * Give all of BookedDate entity entries from database 
     * 
     * @return      method return list of BookedDate (List<BookedDate>)
     */
    List<BookedDate> findAll();

    /**
     * Give all of BookedDate entity entries from database,
     * where offer ID is as given
     * 
     * @param offerId   ID of offer which dates will be find
     * @return          method return list of BookedDate (List<BookedDate>)
     */
    List<BookedDate> findAllBookedDatesByOffer(Long offerId);

    /**
     * Save dates from datesToBookList to offer, which ID was given,
     * this dates in this offer will become unavailable to book.
     * Dates will be booked by user, which userEmail was given.
     * 
     * @param datesToBookList   list of dates which will be saved to database
     * @param offerId           offer ID, which will be booked
     * @param userEmail         email of user who is booking
     */
    void bookDates(List<String> datesToBookList, Long offerId, String userEmail);

    /**
     * Give all available dates of given offer.
     * Divide result on pages, number of page must be send to method as Integer.
     * 
     * 
     * @param offerId       ID of offer which dates will be given.
     * @param page          Number of page, which will be given.
     * @return
     */
    Page<String> findAvailableDatesByOffersId(Long offerId, Integer page);

}
