package com.pahanasystem.test;

import com.pahanasystem.util.DBUtil;

import java.sql.Connection;

public class DBConnectionTest {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.INSTANCE.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }
    }
}
