package bc.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Users", schema = "dbo", catalog = "PersonalFinanceManagementWebapp")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserId")
    private int userId;
    @Basic
    @Column(name = "FullName")
    private String fullName;
    @Basic
    @Column(name = "Username")
    private String username;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "Email")
    private String email;

    public UsersEntity() {
    }

    public UsersEntity(int userId, String fullName, String username, String password, String email) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UsersEntity(String fullName, String username, String password, String email) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return userId == that.userId && Objects.equals(fullName, that.fullName) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, fullName, username, password, email);
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
