package com.challenge4.FilmApp.service;

import com.challenge4.FilmApp.dao.request.LoginModel;
import com.challenge4.FilmApp.dao.request.RegisterModel;

import java.util.Map;

public interface UserService {
    Map registerManual(RegisterModel objModel) ;

    Map login(LoginModel loginModel);
}
