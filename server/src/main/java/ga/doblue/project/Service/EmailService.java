package ga.doblue.project.Service;

import ga.doblue.project.model.Email;
import org.springframework.stereotype.Service;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * Created by SungHere on 2017-06-28.
 */
@Service
public class EmailService {

    public ClientResponse sendMail(Email email) {

        Client client = ClientBuilder.newClient();
        client.register(HttpAuthenticationFeature.basic(
                "api",
                "key-774874982400a67d8a7d511c1f3266da"
        ));

        WebTarget mgRoot = client.target("https://api.mailgun.net/v3");

        Form reqData = new Form();
        reqData.param("from", email.getEmail());
        reqData.param("to", "doblue@doblue.ga");
        reqData.param("subject", email.getTitle());
        reqData.param("text", email.getContent());

        return mgRoot
                .path("{domain}/messages")
                .resolveTemplate("domain", "doblue.ga")
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .buildPost(Entity.entity(reqData, MediaType.APPLICATION_FORM_URLENCODED))
                .invoke(ClientResponse.class);
    }

}
