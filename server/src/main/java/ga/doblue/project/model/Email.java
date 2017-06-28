package ga.doblue.project.model;

/**
 * Created by SungHere on 2017-06-28.
 */


public class Email {
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
}
