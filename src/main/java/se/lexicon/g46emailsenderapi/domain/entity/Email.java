package se.lexicon.g46emailsenderapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Email {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;
  @Column(name = "_from" ,nullable = false)
  private String from;
  @Column(name = "_to" ,nullable = false)
  private  String to;
  private String subject;
  @Lob
  @Column(length = 65000)
  private String content;
  @Lob
  private List<String> attachments;
  private LocalDateTime dateTime;
  private Integer type;
  // add more fields as needed...

  @PrePersist
  public void initialData(){
    dateTime = LocalDateTime.now();
  }

}
