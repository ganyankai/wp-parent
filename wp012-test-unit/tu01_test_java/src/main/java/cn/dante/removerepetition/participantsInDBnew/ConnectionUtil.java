package cn.dante.removerepetition.participantsInDBnew;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    public static Connection getConnection() throws Exception{
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(
                        "jdbc:postgresql://myhost/ConferenceDB",
                        "PaulChan",
                        "myP@ssword");
    }

}
