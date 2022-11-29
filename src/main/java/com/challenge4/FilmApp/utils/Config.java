package com.challenge4.FilmApp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Config {


    public static  String ERROR_401 = "401";
    public static  String ERROR_500 = "500";
    public static  String ERROR_404 = "404";

    public static  String ERROR_403 = "403";

    public static  String SUKSES_200 = "200"; // sukses

    public String isRequired =" is Required";

    public String convertDateToString(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
