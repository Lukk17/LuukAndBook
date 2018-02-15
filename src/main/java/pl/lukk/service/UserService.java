package pl.lukk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import pl.lukk.entity.User;

public interface UserService
{
    /**
     * Save new, given User.
     * Encode password with BCryptPasswordEncoder.
     * Set Role to ROLE_USER.
     * Set default photo.
     * 
     * @param user      User which will be saved.
     */
    public void saveUser(User user);

    /**
     * Give list of all Users.
     * 
     * @return  Return list of all Users(List<User>).
     */
    List<User> findAll();

    /**
     * Give user with ID same as given.
     * 
     * @param id    ID of user.
     * @return      Return one User.
     */
    User findByUserId(Long id);

    /**
     * Delete User with given ID.
     * 
     * @param id    ID of user to delete.
     */
    void delete(Long id);

    /**
     * Check if given newPassword is same as saved in database.
     * Used in login.
     * 
     * @param newPassword   Password from login.
     * @param password      Password from database.
     * @return              Return true if passwords are same.
     */
    boolean checkPassword(String newPassword, String password);

    /**
     * Give User which email is same as given.
     * 
     * @param email     Email of user.
     * @return          Return one User.
     */
    User findByUserEmail(String email);

    /**
     * Give all users formated in pages.
     * 
     * @param pageable  Scheme of paging.
     * @return          Return paged list of Users (Page<User>).
     */
    Page<User> findAll(Pageable pageable);

    /**
     * Change Role to one with ID as given of user with ID as given.
     * 
     * @param userId        ID of User.
     * @param rolesId       ID of Role.
     */
    void roleChange(Long userId, List<Long> rolesId);

    /**
     * Save profile photo of user with email same as given.
     * 
     * @param email     Email of user.
     * @param photo     Photo to be saved.
     */
    void savePhoto(String email, MultipartFile photo);

    /**
     * Save edited User to database.
     * 
     * @param email         Email of edited user.
     * @param userChanges   Attributes of User which will be changed.
     */
    void saveEditUser(String email, User userChanges);

    /**
     * Save edited User to database.
     * Email and ID can be changed here. (Only for Admin)
     * 
     * @param userChanges   Attributes of User which will be changed.
     */
    void adminEditUser(User userChanges);

}
