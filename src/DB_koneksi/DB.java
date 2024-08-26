    package DB_koneksi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DB {

    private static Connection DB_koneksi;

    public static Connection getConnection() throws SQLException {
        if (DB_koneksi == null) {

            DB_koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalfix", "root", "");
        }
        return DB_koneksi;
    }

}
