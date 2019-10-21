package cn.dante.removerepetition.participantsInDB;

import cn.dante.removerepetition.organization.Participant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class ParticipantsInDB {
    Connection db;

    ParticipantsInDB() throws Exception {
        Class.forName("org.postgresql.Driver");
        db =
                DriverManager.getConnection(
                        "jdbc:postgresql://myhost/ConferenceDB",
                        "PaulChan",
                        "myP@ssword");
    }

    //Participant 如果没有public修饰会引用不到
    void addParticipant(Participant part) throws Exception {
        PreparedStatement st = db.prepareStatement("INSERT INTO participants VALUES (?,?,?,?,?,?,?)");
        try {
            st.setString(1, part.getId());
            st.setString(2, part.getEName());
            st.setString(3, part.getCName());
            st.executeUpdate();
        } finally {
            st.close();
        }
    }
}

