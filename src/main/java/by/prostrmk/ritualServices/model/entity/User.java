package by.prostrmk.ritualServices.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue
    private String id;
    private String username;
    private String mobilePhone;
    private String mail;
    private String message;
    private Date date;


    public User() {
    }

    public User(String username, String mobilePhone, String mail, String message, Date date) {
        this.username = username;
        this.mobilePhone = mobilePhone;
        this.mail = mail;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(mobilePhone, user.mobilePhone) &&
                Objects.equals(mail, user.mail) &&
                Objects.equals(message, user.message) &&
                Objects.equals(date, user.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, mobilePhone, mail, message, date);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", mail='" + mail + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
