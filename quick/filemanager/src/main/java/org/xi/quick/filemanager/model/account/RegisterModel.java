package org.xi.quick.filemanager.model.account;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class RegisterModel {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名必须在4-20位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 50, message = "密码长度必须在6-20位")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 6, max = 50, message = "确认密码长度必须在6-20位")
    private String confirmPassword;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$", message = "手机号码格式不正确")
    private String phone;

    public RegisterModel() {
        super();
    }

    public RegisterModel(String username, String password, String email, String phone) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
