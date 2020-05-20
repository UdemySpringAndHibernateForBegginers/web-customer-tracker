package com.luv2code.testdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.luv2code")
@WebServlet("/testDbServlet")
public class TestDbServlet extends HttpServlet {

    //setup connection variables
    //@Value("${spring.datasource.driver-class-name}")
    private String driverClassName = "org.h2.Driver";

    //@Value("${spring.datasource.url}")
    private String url = "jdbc:h2:file:~/web_customer_tracker";

    //@Value("${spring.datasource.username}")
    private String username = "sa";

    //@Value("${spring.datasource.password}")
    private String password = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get connection to database
        try {

            PrintWriter out = resp.getWriter();

            out.println("Connecting to database: " + url);

            Class.forName(driverClassName);

            Connection connection = DriverManager.getConnection(url, username, password);

            out.println("Connection successful.");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
