package ga.doblue.project.Service;

import ga.doblue.project.model.Email;
import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.MailBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by SungHere on 2017-06-28.
 */
@Service
public class EmailService {

    public void sendMail(Email email) {
        Configuration configuration = new Configuration()
                .domain("doblue.ga")
                .apiKey("key-774874982400a67d8a7d511c1f3266da")
                .from(email.getName(), email.getEmail());

        MailBuilder.using(configuration)
                .to("Doblue Team", "doblue@doblue.ga")
                .subject(email.getTitle())
                .text(email.getContent())
                .build()
                .send();


    }
}