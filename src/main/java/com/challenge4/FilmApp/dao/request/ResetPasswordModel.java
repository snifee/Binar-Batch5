package com.challenge4.FilmApp.dao.request;

import lombok.Data;
@Data
public class ResetPasswordModel {
    public String email;
    public String otp;
    public String newPassword;
}
