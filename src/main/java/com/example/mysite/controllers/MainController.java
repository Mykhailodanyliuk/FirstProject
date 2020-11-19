package com.example.mysite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<String> lines=new ArrayList<String>();
        while(resultSet.next())
        {
            String text = resultSet.getString("text");
            lines.add(text);
        }

        model.addAttribute("lines" , lines);


        return "home";
    }
    @PostMapping("/")
    public String deletetext(@RequestParam String delete) throws Exception {
        try {
            String username = "root";
            String password = "root";
            String connectionurl = "jdbc:mysql://localhost:3307/mydata?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(connectionurl, username, password);
            String query1 = " delete from table1 where text=(?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(query1);
            preparedStmt1.setString(1, delete);
            preparedStmt1.execute();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/addtext")
    public String addtext(Model model){
        return "addtext";
    }

    @PostMapping("/addtext")
    public String addtextPost(@RequestParam String string) throws Exception {
        try {
            String username = "root";
            String password = "root";
            String connectionurl = "jdbc:mysql://localhost:3307/mydata?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(connectionurl, username, password);
            String query = " insert into table1 (text) " + " values (?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, string);
            preparedStmt.execute();
            con.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return "redirect:/";
    }

            }
