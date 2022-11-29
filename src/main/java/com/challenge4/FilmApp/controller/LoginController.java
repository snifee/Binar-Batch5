package com.challenge4.FilmApp.controller;

import com.challenge4.FilmApp.dao.request.LoginModel;
import com.challenge4.FilmApp.repository.oauth.UserRepository;
import com.challenge4.FilmApp.service.UserService;
import com.challenge4.FilmApp.utils.TemplateResponse;
import com.challenge4.FilmApp.utils.oauth.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/user-login/")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    Config config = new Config();
    @Autowired
    public UserService serviceReq;
    //    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
//    private int expiredToken;
    @Autowired
    public TemplateResponse templateCRUD;
    @PostMapping(value = {"/login"})
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> login(@Valid @RequestBody LoginModel objModel) {
        Map map = serviceReq.login(objModel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}

