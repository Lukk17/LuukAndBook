package pl.lukk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.lukk.entity.User;

public interface UserService
{
    public void saveUser(User user);

    List<User> findAll();

    User findByUserId(Long id);

    void delete(Long id);

    boolean checkPassword(String newPassword, String password);

    void saveEditUser(User user);

    User findByUserEmail(String email);

    Page<User> findAll(Pageable pageable);
}
