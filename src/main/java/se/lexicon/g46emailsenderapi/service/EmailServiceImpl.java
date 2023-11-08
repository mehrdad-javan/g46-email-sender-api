package se.lexicon.g46emailsenderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g46emailsenderapi.domain.dto.EmailDTO;
import se.lexicon.g46emailsenderapi.domain.entity.Email;
import se.lexicon.g46emailsenderapi.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {

  private final EmailRepository emailRepository;

  @Autowired
  public EmailServiceImpl(EmailRepository emailRepository) {
    this.emailRepository = emailRepository;
  }

  @Override
  public String sendEmail(EmailDTO dto) {
    if (dto == null) throw new IllegalArgumentException("Email is null");

    Email emailEntity = Email.builder()
            .to(dto.getTo())
            .from("test@test.se")
            .subject(dto.getSubject())
            .content(dto.getHtml())
            .type(dto.getType())
            .build();


    Email savedEmail = emailRepository.save(emailEntity);

    // todo: implement a logic to send email to user

    return savedEmail.getId();
  }


}
