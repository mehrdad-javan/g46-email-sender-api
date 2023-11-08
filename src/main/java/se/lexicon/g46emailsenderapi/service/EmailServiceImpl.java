package se.lexicon.g46emailsenderapi.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import se.lexicon.g46emailsenderapi.config.EmailProperties;
import se.lexicon.g46emailsenderapi.domain.dto.EmailDTO;
import se.lexicon.g46emailsenderapi.domain.entity.Email;
import se.lexicon.g46emailsenderapi.exception.EmailException;
import se.lexicon.g46emailsenderapi.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {

  private final EmailRepository emailRepository;
  private final JavaMailSender javaMailSender;

  private final EmailProperties emailProperties;

  @Autowired
  public EmailServiceImpl(
          EmailRepository emailRepository,
          JavaMailSender javaMailSender,
          EmailProperties emailProperties
  ) {
    this.emailRepository = emailRepository;
    this.javaMailSender = javaMailSender;
    this.emailProperties = emailProperties;
  }

  @Override
  public void sendEmail(EmailDTO dto) {
    if (dto == null) throw new IllegalArgumentException("Email is null");


    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

      mimeMessage.setContent(dto.getHtml(), "text/html");
      helper.setTo(dto.getTo());
      helper.setFrom(emailProperties.getUsername());
      helper.setSubject(dto.getSubject());


      Email emailEntity = Email.builder()
              .to(dto.getTo())
              .from(emailProperties.getUsername())
              .subject(dto.getSubject())
              .content(dto.getHtml())
              .type(dto.getType())
              .build();

      emailRepository.save(emailEntity);

      javaMailSender.send(mimeMessage);

    } catch (MessagingException e) {
      throw new EmailException("Error sending email: " + e.getMessage(), e);
    }


  }


}
