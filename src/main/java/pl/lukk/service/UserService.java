package pl.lukk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import pl.lukk.entity.User;

public interface UserService
{
    public void saveUser(User user);

    List<User> findAll();

    User findByUserId(Long id);

    void delete(Long id);

    boolean checkPassword(String newPassword, String password);

    User findByUserEmail(String email);

    Page<User> findAll(Pageable pageable);

    void roleChange(Long userId, List<Long> rolesId);

    void savePhoto(String email, MultipartFile photo);

    void saveEditUser(String email, User userChanges);

    void adminEditUser(User userChanges);

}
