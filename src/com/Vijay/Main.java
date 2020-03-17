package com.Vijay;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "vijayNewDatabase.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Vijay\\Desktop\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_EMAIL = "email";
    public static final String PRIMARY_KEY = "_id";


    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement();) {
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    TABLE_CONTACTS + " (" +
                    PRIMARY_KEY + " INTEGER PRIMARY KEY ," +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_NUMBER + " INTEGER, " +
                    COLUMN_EMAIL + " TEXT " + ")");

            insertContact(statement, "vijay1", 76546, "apple1@mail.com");
            insertContact(statement, "vijay2", 76547, "apple2@mail.com");
            insertContact(statement, "vijay3", 76548, "apple3@mail.com");
            insertContact(statement, "vijay4", 76549, "apple4@mail.com");

            deleteContact(statement, "vijay2");
            updateContact(statement, "vijay3", 94136741);

            // =======================================================================================================//
            System.out.println("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text, " +
                    COLUMN_NUMBER + " integer, " +
                    COLUMN_EMAIL + " text " + ")   ");
            // ======================================================================================================//

            ResultSet results = statement.executeQuery(" SELECT * FROM " + TABLE_CONTACTS);
            while (results.next()) {
                System.out.println(results.getInt(PRIMARY_KEY) + " " +
                        results.getString(COLUMN_NAME) + " " +
                        results.getInt(COLUMN_NUMBER) + " " +
                        results.getString(COLUMN_EMAIL));
            }
            results.close();
            statement.close();
            conn.close();


        } catch (SQLException e) {
            System.out.println("Problem is " + e);
            e.printStackTrace();
        }


    }

    private static void insertContact(Statement statement, String name, int number, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + "," +
                COLUMN_NUMBER + "," +
                COLUMN_EMAIL + " ) " +
                "VALUES( '" + name + "'" + "," + number + "," + "'" + email + "')");

        System.out.println("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + "," +
                COLUMN_NUMBER + "," +
                COLUMN_EMAIL + " ) " +
                "VALUES( '" + name + "'" + "," + number + "," + "'" + email + "')");
    }

    private static void updateContact(Statement statement, String name, int number) throws SQLException {
        statement.execute("UPDATE " + TABLE_CONTACTS +
                " SET " + COLUMN_NUMBER + " = " + number +
                " WHERE " + COLUMN_NAME + " = '" + name + "'");
        System.out.println("UPDATE " + TABLE_CONTACTS + " SET " + COLUMN_NUMBER + " = " + number + " WHERE " + COLUMN_NAME + " = '" + name + "'");
    }

    private static void deleteContact(Statement statement, String name) throws SQLException {
        statement.execute("DELETE FROM " + TABLE_CONTACTS +
                " WHERE " + COLUMN_NAME + " = '" + name + "'");
        System.out.println("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + " = '" + name + "'");
    }


}
