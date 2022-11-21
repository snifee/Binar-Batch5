package com.challenge4.FilmApp.service.jasper;


import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

@Service
public class ReportServices {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    public byte[] generatePdf(Map<String, Object >parameters) throws SQLException {
        try {

            File file = ResourceUtils.getFile("report/tiket.jrxml");

            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    report,
                    parameters,
                    jdbcTemplate.getDataSource().getConnection()
            );
            JasperExportManager.exportReportToPdfFile(jasperPrint, "cdn/out.pdf");

            byte[] result = JasperExportManager.exportReportToPdf(jasperPrint) ;
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            jdbcTemplate.getDataSource().getConnection().close();
        }
    }

    public void generateInvoice(Map<String, Object >parameters, OutputStream outputStream)throws SQLException {
        try {

            File file = ResourceUtils.getFile("report/tiket.jrxml");

            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    report,
                    parameters,
                    jdbcTemplate.getDataSource().getConnection()
            );
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbcTemplate.getDataSource().getConnection().close();
        }
    }
}
