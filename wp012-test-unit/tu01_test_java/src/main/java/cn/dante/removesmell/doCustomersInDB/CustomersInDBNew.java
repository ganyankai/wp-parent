package cn.dante.removesmell.doCustomersInDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class CustomersInDBNew {
    Connection conn;

    Customer getCustomer(String IDNumber) throws Exception {
        PreparedStatement st = conn.prepareStatement(
                "select * from customer where ID=?");
        try {
            st.setString(1,
                    getIDReplace(IDNumber));
            ResultSet rs = st.executeQuery();
        } finally {
            st.close();
        }
        return null;
    }

    private String getIDReplace(String IDNumber) {

        String charArr = "-()/";
        for (int i=0;i<charArr.length();i++){
            IDNumber.replace(charArr.charAt(i),' ');
        }

        return IDNumber;
    }

    void addCustomer(Customer customer) throws Exception {
        PreparedStatement st = conn.prepareStatement(
                "insert into customer values(?,?,?,?)");
        try {
            st.setString(1,
                    getIDReplace(customer.getIDNumber()));
            st.setString(2, customer.getName());
            st.executeUpdate();
        } finally {
            st.close();
        }
    }
}