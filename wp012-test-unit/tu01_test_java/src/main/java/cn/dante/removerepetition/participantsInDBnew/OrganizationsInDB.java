package cn.dante.removerepetition.participantsInDBnew;

import cn.dante.removerepetition.organization.Organization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class OrganizationsInDB {
    Connection db;

    OrganizationsInDB() throws Exception {
        db =   ConnectionUtil.getConnection();
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