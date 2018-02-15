package pl.lukk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.lukk.entity.Offer;
import pl.lukk.entity.User;

public interface OfferService
{
    /**
     * Give Offer with given owner email and ID.
     * 
     * @param ownerEmail    Email of offer owner.
     * @param id            ID of offer.
     * @return
     */
    Offer findByUserAndId(String ownerEmail, Long id);

    /**
     * Give Offer with given ID.
     * 
     * @param id    Offer ID
     * @return      Return one Offer.
     */
    Offer findOneById(Long id);

    /**
     * Save edited offer.
     * 
     * @param offer     Offer to edit.
     */
    void saveEditOffer(Offer offer);

    /**
     * Give Offers in which user is same as given.
     * 
     * @param userEmail     Email of user.
     * @return              Return List of Offers(List<Offer>)
     */
    List<Offer> findByUserId(String userEmail);

    /**
     * Delete Offer with given ID,
     * if given email is it's owner's email.
     * 
     * @param offerId       ID of Offer to delete.
     * @param ownerEmail    Email Offer owner.
     */
    void deleteOffer(Long offerId, String ownerEmail);

    /**
     * Save photo name of Offer with given ID,
     * if given email is it's owner's email.
     * 
     * @param offerId           ID of Offer.
     * @param ownerEmail        Offer owner email.
     * @param photoFilename     Photo name.
     */
    void addPhoto(Long offerId, String ownerEmail, String photoFilename);

    /**
     * Save new Offer to database.
     * Set it's owner with given email.
     * 
     * @param offer         Offer to save.
     * @param ownerEmail    Offer owner email.
     */
    void saveAddOffer(Offer offer, String ownerEmail);

    /**
     * Give list of Offers which have same attributes as given one.
     * 
     * @param offer     Offer by which attributes search will start.
     * @return          Return list of Offers (List<Offer>).
     */
    List<Offer> search(Offer offer);

    /**
     * Give list of Offer which user have same email as given.
     * 
     * @param email     Email of User.
     * @return          Return list of Offers (List<Offer>).
     */
    List<Offer> findByBookedDatesUser(String email);

    /**
     * Give list of all Offers in given pageable scheme.
     * 
     * @param pageable      Paging scheme.
     * @return              Return paged list of Offers (Page<Offer>).
     */
    Page<Offer> findALL(Pageable pageable);

    /**
     * Give number of offers booked by user with mail same as given.
     * 
     * @param userEmail     Email of user.
     * @return              Return list of booked Offers (List<Offer>).
     */
    Integer userOfferNum(String userEmail);

    /**
     * Give number of offers owned by owner with mail same as given.
     * 
     * @param ownerEmail    Email of owner.
     * @return              Return list of booked Offers (List<Offer>).
     */
    Integer ownerOfferNum(String ownerEmail);

    /**
     * Give five booked Offers of user with mail same as given.
     * 
     * @param userEmail     Email of user.
     * @return              Return list of five booked Offers (List<Offer>).
     */
    List<Offer> findTop5ByUser(String userEmail);

    /**
     * Give five Offers owned by owner with mail same as given.
     * 
     * @param ownerEmail    Email of owner.
     * @return              Return list of five booked Offers (List<Offer>).
     */
    List<Offer> findTop5ByOwner(String ownerEmail);

    /**
     * Give Offer of given ID without authorization (for Admin only)
     * 
     * @param id        ID of Offer.
     * @return          Return one Offer.
     */
    Offer adminFindOfferById(Long id);

}
