//package com.challenge4.FilmApp.controller;
//
//
//import com.challenge4.FilmApp.service.jasper.ReportServices;
//import net.sf.jasperreports.engine.JRException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/v1/report")
//public class ReportController {
//
//    @Autowired
//    ReportServices reportServices;
//
//
//    @GetMapping(value = {"/invoice/","/invoice"})
//    public void generateInvoice(@RequestParam("uname")String uname, HttpServletResponse response)throws IOException, JRException, SQLException {
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("uname",uname);
//
//        response.setContentType("application/x-download");
//        response.setHeader("Content-Disposition",String.format("attachment; filename=\"inv.pdf\""));
//        OutputStream outputStream = response.getOutputStream();
//
//        reportServices.generateInvoice(data,outputStream);
//
//    }
//
//}
