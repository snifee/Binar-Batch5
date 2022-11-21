package com.challenge4.FilmApp.service.jasper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class Test {
    @Autowired
    public  ReportServices reportService;

    @org.junit.Test
    public  void test() throws SQLException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uname", "user1");
        String pathUrl = "C:\\Users\\razya\\JaspersoftWorkspace\\ch4";
        reportService.generatePdf(parameters);

    }
}
