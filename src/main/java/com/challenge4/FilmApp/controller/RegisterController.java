package com.challenge4.FilmApp.controller;


import com.challenge4.FilmApp.dao.request.RegisterModel;
import com.challenge4.FilmApp.entity.oauth.User;
import com.challenge4.FilmApp.repository.oauth.UserRepository;
import com.challenge4.FilmApp.service.UserService;
import com.challenge4.FilmApp.service.email.EmailSender;
import com.challenge4.FilmApp.utils.EmailTemplate;
import com.challenge4.FilmApp.utils.SimpleStringUtils;
import com.challenge4.FilmApp.utils.TemplateResponse;
import com.challenge4.FilmApp.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user-register/")
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
    Config config = new Config();

    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
    private int expiredToken;


    @Autowired
    UserService  userService;
    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("/register")
    public ResponseEntity<Map> registerManual(@RequestBody RegisterModel registerModel) throws RuntimeException{
        Map map = new HashMap();

        User user= userRepository.checkExistingEmail(registerModel.getEmail());

        if (null != user){
            return new ResponseEntity<Map>(templateResponse.notFound("User Telah Ada"), HttpStatus.OK);

        }

        map = userService.registerManual(registerModel);

        Map sendOTP = sendEmailegister(registerModel);

        Map sendOTPURL = sendEmailegisterTymeleaf(registerModel);

        return new ResponseEntity<Map>(map, HttpStatus.OK);

    }

    @Autowired
    public EmailTemplate emailTemplate;

    @Autowired
    public EmailSender emailSender;


    @PostMapping("/send-otp")//send OTP
    public Map sendEmailegister(@RequestBody RegisterModel user) {
        String message = "Thanks, please check your email for activation.";

        if (user.getEmail() == null) return templateResponse.templateEror("No email provided");
        User found = userRepository.findOneByUsername(user.getEmail());
        if (found == null) return templateResponse.notFound("Email not found"); //throw new BadRequest("Email not found");

        String template = emailTemplate.getRegisterTemplate();
        if (StringUtils.isEmpty(found.getOtp())) {
            User search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername1() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getFullname() == null ? found.getUsername1() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Register", template);
        return templateResponse.templateSukses(message);
    }

    @GetMapping("/register-confirm-otp/{token}")
    public ResponseEntity<Map> saveRegisterManual(@PathVariable(value = "token") String tokenOtp) throws RuntimeException {



        User user = userRepository.findOneByOTP(tokenOtp);
        if (null == user) {
            return new ResponseEntity<Map>(templateResponse.templateEror("OTP tidak ditemukan"), HttpStatus.OK);
        }

        if(user.isEnabled()){
            return new ResponseEntity<Map>(templateResponse.templateSukses("Akun Anda sudah aktif, Silahkan melakukan login"), HttpStatus.OK);
        }
        String today = config.convertDateToString(new Date());

        String dateToken = config.convertDateToString(user.getOtpExpiredDate());
        if(Long.parseLong(today) > Long.parseLong(dateToken)){
            return new ResponseEntity<Map>(templateResponse.templateEror("Your token is expired. Please Get token again."), HttpStatus.OK);
        }
        //update user
        user.setEnabled(true);
        userRepository.save(user);

        return new ResponseEntity<Map>(templateResponse.templateSukses("Sukses, Silahkan Melakukan Login"), HttpStatus.OK);
    }


    @Value("${BASEURL:}")//FILE_SHOW_RUL
    private String BASEURL;
    @PostMapping("/send-otp-tymeleaf")//send OTP
    public Map sendEmailegisterTymeleaf(@RequestBody RegisterModel user)
    {
        String message = "Thanks, please check your email for activation.";

        if (user.getEmail() == null) {
            return templateResponse.templateEror("No email provided");
        }
        User found = userRepository.findOneByUsername(user.getEmail());

        if (found == null) {
            return templateResponse.notFound("Email not found"); //throw new BadRequest("Email not found");
        }
        String template = emailTemplate.getRegisterTemplate();
        if (StringUtils.isEmpty(found.getOtp())) {
            User search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();
            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{USERNAME}}",(found.getFullname() == null ? found.getUsername() :found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",
                    BASEURL + "user-register/web/index/"+ otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}",
                    (found.getFullname() == null ? found.getUsername() : found.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",
                    BASEURL + "user-register/web/index/"+ found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Register", template);

        return templateResponse.templateSukses(message);
    }


}
