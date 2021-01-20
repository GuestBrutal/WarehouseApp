/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkom.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmbrps
 */
public class DBHelper {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB = "sisgudang";
    private static final String MYCONN = "jdbc:mysql://localhost/" + DB;
    private static final String SQCONN = "E:\\Kuliah\\PBO\\sqLite\\SisGudang.sqlite";

    public static Connection getConnection(String driver) throws SQLException {
        Connection conn = null;
        switch (driver) {
            case "SQLITE": {
                try {
                    Class.forName("org.sqlite.JDBC");
                    conn = DriverManager.getConnection(SQCONN);
                    createTable(conn, driver);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Librari tidak ada");
                    Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "MYSQL": {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(MYCONN, USER, PASSWORD);
                    createTable(conn, driver);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Librari tidak ada");
                    Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }

        return conn;
    }

    public static void createTable(Connection conn, String driver) throws SQLException {
        String sqlCreate = "";
        switch (driver) {
            case "MYSQL": {
                sqlCreate = "CREATE TABLE IF NOT EXISTS `sistem_gudang` ("
                        + "  `gudang_id` int(10) NOT NULL,"
                        + "  `nama` varchar(100) DEFAULT NULL,"
                        + "  `alamat` varchar(100) DEFAULT NULL,"
                        + "  PRIMARY KEY (`gudang_id`)"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                        + "CREATE TABLE IF NOT EXISTS `akun` ("
                        + "  `no_member` int(10) NOT NULL,"
                        + "  `barang` varchar(100) DEFAULT NULL,"
                        + "  `gudang_id` int(10) DEFAULT NULL,"
                        + "  PRIMARY KEY (`nik`),"
                        + "  KEY `gudang_id` (`gudang_id`),"
                        + "  FOREIGN KEY (`gudang_id`) REFERENCES `sistem_gudang` (`gudang_id`) ON UPDATE CASCADE"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                        + "CREATE TABLE IF NOT EXISTS `corporate_holder` ("
                        + "  `gudang_id` int(10) NOT NULL,"
                        + "  `contact` varchar(100) DEFAULT NULL,"
                        + "  PRIMARY KEY (`gudang_id`),"
                        + "  FOREIGN KEY (`gudang_id`) REFERENCES `sistem_gudang` (`gudang_id`) ON UPDATE CASCADE"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
                        + "CREATE TABLE IF NOT EXISTS `individual_holder` ("
                        + "  `gudang_id` int(10) NOT NULL,"
                        + "  `nik` varchar(100) DEFAULT NULL,"
                        + "  `birthdate` date DEFAULT NULL,"
                        + "  PRIMARY KEY (`gudang_id`),"
                        + "  FOREIGN KEY (`gudang_id`) REFERENCES `sistem_gudang` (`gudang_id`) ON UPDATE CASCADE"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
                break;
            }
            case "SQLITE": {
                sqlCreate = "CREATE TABLE IF NOT EXISTS  sistem_gudang ("
                        + "    gudang_id INT (10)      PRIMARY KEY,"
                        + "    nama      VARCHAR (100),"
                        + "    alamat   VARCHAR (100) "
                        + ");"
                        + "CREATE TABLE IF NOT EXISTS account ("
                        + "    no_member INT (10)       PRIMARY KEY,"
                        + "    barang    VARCHAR (100),"
                        + "    gudang_id  INT (10)       REFERENCES sistem_gudang (gudang_id) ON DELETE RESTRICT"
                        + "                                                                    ON UPDATE CASCADE"
                        + ");"
                        + "CREATE TABLE IF NOT EXISTS corporate_holder ("
                        + "    gudang_id INT (10)      PRIMARY KEY"
                        + "                            REFERENCES sistem_gudang (gudang_id) ON DELETE RESTRICT"
                        + "                                                                  ON UPDATE CASCADE,"
                        + "    contact   VARCHAR (100) "
                        + ");"
                        + "CREATE TABLE IF NOT EXISTS  individual_holder ("
                        + "    gudang_id INT (10)     PRIMARY KEY"
                        + "                           REFERENCES sistem_gudang (gudang_id) ON DELETE RESTRICT"
                        + "                                                                 ON UPDATE CASCADE,"
                        + "    nik    VARCHAR (100),"
                        + "    birthdate DATE"
                        + ");";
                break;
            }
        }
        String sqls[] = sqlCreate.split(";");
        for (String sql : sqls) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        }
    }
}
