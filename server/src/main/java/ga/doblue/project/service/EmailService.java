package ga.doblue.project.service;

import ga.doblue.project.model.Email;
import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.MailBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by SungHere on 2017-06-28.
 */
@Service
public class EmailService {
    @Value("${mail.key}")
    private String key;
    public void sendMail(Email email) {
        Configuration configuration = new Configuration()
                .domain("doblue.ga")
                .apiKey(key)
                .from(email.getName(), email.getEmail());

        MailBuilder.using(configuration)
                .to("Doblue Team", "doblue@doblue.ga")
                .subject(email.getTitle())
                .text(email.getContent())
                .build()
                .send();


    }
}