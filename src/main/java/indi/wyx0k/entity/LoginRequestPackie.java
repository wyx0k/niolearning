package indi.wyx0k.entity;

public class LoginRequestPackie extends Packie {
    private Integer userID;
    private String username;
    private String password;
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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
}
