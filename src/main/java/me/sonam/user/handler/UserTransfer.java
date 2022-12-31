package me.sonam.user.handler;

/**
 * this is for parsing the data from serverrequest body to this object
 */
public class UserTransfer {
    private String firstName;
    private String lastName;
    private String email;
    private String authenticationId;
    private String password;

    public UserTransfer() {

    }
    public UserTransfer(String firstName, String lastName, String email, String authenticationId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authenticationId = authenticationId;
        this.password = password;
    }
    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
