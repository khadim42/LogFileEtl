package com.kh.dao;

import com.kh.model.AlertLog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlertLogDao {
    static Connection hsqlConnection = null;

    static {

        try {
            //Registering the HSQLDB JDBC driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            //Creating the connection with HSQLDB
            hsqlConnection = DriverManager.getConnection("jdbc:hsqldb:file:hsqldb/data", "SA", "");
            if (hsqlConnection != null) {
                Logger.getGlobal().log(Level.INFO, "Connection created successfully");
                createTable();

            } else {
                Logger.getGlobal().log(Level.SEVERE, "Problem with creating connection");
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void save(AlertLog alertLog) {

        try {
            String query = "INSERT INTO alert_log (event_id,event_duration,type,host,alert) VALUES (" +
                    "'" +
                    alertLog.getEventId() + "'," +
                    alertLog.getEventDuration() + ",'" + alertLog.getType() + "','" + alertLog.getHost() + "'," + alertLog.isAlert()
                    + ")";
            hsqlConnection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void createTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS alert_log " +
                "" +
                " (event_id VARCHAR(80)," +
                "    event_duration int," +
                "    type VARCHAR(80)," +
                "    host VARCHAR(80)," +
                "    alert BOOLEAN " +
                ");";

        Statement stmt = hsqlConnection.createStatement();
        stmt.execute(sqlCreate);
    }
}
