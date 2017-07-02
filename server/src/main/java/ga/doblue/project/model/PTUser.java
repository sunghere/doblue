package ga.doblue.project.model;

/**
 * Created by SungHere on 2017-06-14.
 */
public class PTUser {

    private String id;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public PTUser() {
    }

    @Override
    public String toString() {
        return "PTUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public PTUser(String id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }
}
