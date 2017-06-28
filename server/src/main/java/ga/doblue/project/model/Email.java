package ga.doblue.project.model;

import java.io.Serializable;

/**
 * Created by SungHere on 2017-06-28.
 */


public class Email implements Serializable{
    private String title;
    private String name;
    private String email;
    private String content;

    public Email() {

    }

    public Email(String title, String name, String email, String content) {
        this.title = title;
        this.name = name;
        this.email = email;
        this.content = content;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
