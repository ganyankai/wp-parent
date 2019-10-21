package cn.dante.removerepetition.participantsInDB;

import cn.dante.removerepetition.organization.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class ParticipantsInDB3new {
    Connection db;
    static final String tableName="participants";

    PreparedStatement makeStatement(String sql) throws Exception{
        return db.prepareStatement(sql);
    }

    void addParticipant(Participant part) throws Exception{
//        PreparedStatement st = makeStatement("INSERT INTO participants VALUES (?,?,?,?,?,?,?)");
        PreparedStatement st = makeStatement("INSERT INTO "+tableName+" VALUES (?,?,?,?,?,?,?)");
        try {
            st.setString(1, part.getId());

            st.executeUpdate();
        } finally {
            st.close();
        }
    }

    void deleteAllParticipants() throws Exception {
//        PreparedStatement st = makeStatement("DELETE FROM participants");
        PreparedStatement st = makeStatement("DELETE FROM "+tableName);
        try {
            st.executeUpdate();
        } finally {
            st.close();
        }
    }

    int getCount()  throws Exception{
        PreparedStatement st = makeStatement("SELECT COUNT(*) FROM "+tableName);
        try {
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getInt(1);
        } finally {
            st.close();
        }
    }
}