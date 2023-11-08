package se.lexicon.g46emailsenderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g46emailsenderapi.domain.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {

}
