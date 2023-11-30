/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_koneksi;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author 
 */
public class DB {

    private static Connection DB_koneksi;

    public static Connection getConnection() throws SQLException {
        if (DB_koneksi == null) {

            DB_koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/autorental", "root", "");
        }
        return DB_koneksi;
    }

}
