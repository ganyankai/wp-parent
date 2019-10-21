package cn.dante.removerepetition.participantsInDB;

import cn.dante.removerepetition.organization.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class ParticipantsInDB3 {
    Connection db;

    void addParticipant(Participant part) throws Exception{
        PreparedStatement st = db.prepareStatement("INSERT INTO participants VALUES (?,?,?,?,?,?,?)");
        try {
            st.setString(1, part.getId());

            st.executeUpdate();
        } finally {
            st.close();
        }
    }

    void deleteAllParticipants() throws Exception {
        PreparedStatement st = db.prepareStatement("DELETE FROM participants");
        try {
            st.executeUpdate();
        } finally {
            st.close();
        }
    }

    int getCount()  throws Exception{
        PreparedStatement st = db.prepareStatement("SELECT COUNT(*) FROM participants");
        try {
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getInt(1);
        } finally {
            st.close();
        }
    }
}