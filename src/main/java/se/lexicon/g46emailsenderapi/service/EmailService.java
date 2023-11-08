package se.lexicon.g46emailsenderapi.service;

import se.lexicon.g46emailsenderapi.domain.dto.EmailDTO;

public interface EmailService {

  String sendEmail(EmailDTO dto);

}
