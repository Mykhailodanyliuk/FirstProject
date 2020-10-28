package com.example.mysite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @RequestMapping("/")
    public String home(Model model) throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "root";
        String connectionurl = "jdbc:mysql://localhost:3307/mydata?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(connectionurl,username,password);
        Statement statement = con.createStatement();
        statement.executeUpdate("drop table table1");
        statement.executeUpdate("create table table1(text varchar(20))");
        statement.executeUpdate("insert into table1 (text) values ('hello world')");
        statement.executeUpdate("insert into table1 (text) values ('Привіт світ')");
        ResultSet resultSet = statement.executeQuery("select * from table1");
        List<String> al1=new ArrayList<String>();
        while(resultSet.next())
        {
            String text = resultSet.getString("text");
            al1.add(text);}
        model.addAttribute("al1" , al1);

        return "home";
    }

}
