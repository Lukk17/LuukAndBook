package pl.lukk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lukk.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);
    User findById(Long id);
}
