package org.xi.quick.filemanager.model.account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginModel {

    @NotNull
    @Size(min = 4, max = 20, message = "用户名必须在4-20位")
    private String username;
    
    @NotNull
    @Size(min = 6, max = 50, message = "密码必须大于6位")
    private String password;
    
    private boolean remember;

    public LoginModel() {
        super();
    }

    public LoginModel(String username, String password) {
        this(username, password, false);
    }

    public LoginModel(String username, String password, boolean remember) {
        super();
        this.username = username;
        this.password = password;
        this.remember = remember;
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

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
