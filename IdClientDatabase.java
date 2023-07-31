package com.chatApplication;

import java.sql.*;

public class IdClientDatabase {
    public Connection conn;

    IdClientDatabase() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/client", "root", "password");
    }

    public void addMessageDB(int id,String sender,String message) throws Exception{
        PreparedStatement pst=conn.prepareStatement("insert into message values(?,?,?)");
        pst.setInt(1,id);
        pst.setString(2,sender);
        pst.setString(3,message);
        pst.executeUpdate();
    }

    public void fetchMessageDB(int id, MyFrame frame) throws Exception{
            Statement st = conn.createStatement();

            ResultSet result = st.executeQuery("select * from message");

            while (result.next()) {
                if(result.getInt("msgid")==id){
                    String sender=result.getString("sender");
                    String msg=result.getString("msg");

                    frame.updateMessageBox("\n"+sender+" :"+msg);
                }
            }
    }

}
