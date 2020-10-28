package com.example.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MysiteApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        SpringApplication.run(MysiteApplication.class, args);

    }

}
