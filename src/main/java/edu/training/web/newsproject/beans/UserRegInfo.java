package edu.training.web.newsproject.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegInfo that = (UserRegInfo) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, confirmPassword);
    }

    @Override
    public String toString() {
        return "UserRegInfo{" + "userId=" + userId + ", username='" + username + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", confirmPassword='" + confirmPassword + '\'' + '}';
    }
}
