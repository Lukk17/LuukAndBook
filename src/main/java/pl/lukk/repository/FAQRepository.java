package pl.lukk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lukk.entity.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long>
{
    
}
