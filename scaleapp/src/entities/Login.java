
package entities;

import java.util.Objects;

public class Login {
    private static Integer userid = 0;
    private static Integer accisability;
    private static String password;
    private String username;
    private static String fullName;
    private static String accupation;

    public Login() {
    }

    {

    }

    public Login(Integer userid) {
        userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        userid = userid;
    }

    public Integer getAccisability() {
        return accisability;
    }

    public void setAccisability(Integer accisability) {
        accisability = accisability;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        fullName = fullName;
    }

    public static String getAccupation() {
        return accupation;
    }

    public static void setAccupation(String accupation) {
        accupation = accupation;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Login login = (Login)o;
            return Objects.equals(userid, userid) && Objects.equals(accisability, accisability) && Objects.equals(password, password) && Objects.equals(this.username, login.username);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(userid, accisability, password, this.username);
    }

    public String toString() {
        return "Login{userid=" + userid + ", accisability=" + accisability + ", password='" + password + '\'' + ", username='" + this.username + '\'' + '}';
    }
}

