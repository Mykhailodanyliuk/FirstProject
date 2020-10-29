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
        ResultSet resultSet = statement.executeQuery("select * from table1");
        List<String> al1=new ArrayList<String>();
        while(resultSet.next())
        {
            String text = resultSet.getString("text");
            al1.add(text);
        }

        model.addAttribute("al1" , al1);


        return "home";
    }

}
