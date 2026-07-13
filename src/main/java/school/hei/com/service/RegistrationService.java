package school.hei.com.service;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.com.entity.JCourse;
import school.hei.com.entity.JUser;
import school.hei.com.mail.Email;
import school.hei.com.mail.Mailer;
import school.hei.com.repository.JCourseRepository;
import school.hei.com.repository.JUserRepository;

@Service
@AllArgsConstructor
public class RegistrationService {
  private final JUserRepository userRepository;
  private final JCourseRepository courseRepository;
  private final Mailer mailer;

  public void register(UUID userId, UUID courseId) {
    JUser user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    JCourse course =
        courseRepository
            .findById(courseId)
            .orElseThrow(() -> new RuntimeException("Cours non trouvé"));

    try {
      // 1. Préparation de l'adresse principale
      InternetAddress to = new InternetAddress(user.getEmail());

      // 2. Création de l'objet Email avec le BON ORDRE des types
      Email emailDeConfirmation =
          new Email(
              to, // 1: InternetAddress (to)
              List.of(), // 2: List<InternetAddress> (cc) -> Était à la mauvaise place
              List.of(), // 3: List<InternetAddress> (bcc) -> Était à la mauvaise place
              "Confirmation : "
                  + course.getTitle(), // 4: String (subject) -> Java attendait un String ici !
              "Bonjour "
                  + user.getFirstName()
                  + ", inscription validée.", // 5: String (htmlBody) -> Java attendait un String
              // ici !
              List.of() // 6: List<File> (attachments)
              );

      // 3. Envoi via le Mailer de Poja
      mailer.accept(emailDeConfirmation);

    } catch (AddressException e) {
      throw new RuntimeException("Erreur d'adresse email", e);
    }
  }
}
