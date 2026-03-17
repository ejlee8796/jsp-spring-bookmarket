/*
 * Decompiled with CFR 0.152.
 */
package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private static Connection conn;

    public DBManager() {
        conn = null;
    }

    public static Connection getInstance() {
        return conn;
    }

    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adnerwin?useSSL=false", "{DB_USER}", "{DB_PASSWORD}");
            if (conn == null) {
                System.out.println("DATABASE 연결 ERROR");
            } else {
                System.out.println("DATABASE 연결 SUCCESS");
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return conn;
    }
}
