package cn.dante.removerepetition.participantsInDB;

import cn.dante.removerepetition.organization.Organization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class OrganizationsInDB {
    Connection db;

    OrganizationsInDB() throws Exception {
        Class.forName("org.postgresql.Driver");
        db =
                DriverManager.getConnection(
                        "jdbc:postgresql://myhost/ConferenceDB",
                        "PaulChan",
                        "myP@ssword");
    }

    void addOrganization(Organization o) throws Exception {
        PreparedStatement st =
                db.prepareStatement(
                        "INSERT INTO organizations VALUES (?,?,?,?,?,?,?,?,?,?,)");
        try {
            st.setString(1, o.getId());
            st.setString(2, o.getEName());
            st.setString(3, o.getCName());
            st.executeUpdate();
        } finally {
            st.close();
        }
    }
}