package com.javarush.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTest {

    private static final String URL = "jdbc:mysql://localhost:3307/world";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String query = "SELECT id, name, population FROM city LIMIT 5";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int population = resultSet.getInt("population");
                System.out.println(id + " " + name + " " + population);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
