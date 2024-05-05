package com.ptds;

import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*public class Main {
    public static void main(String[] args) {
  

      DBConn connection = new DBConn("traindatabase");
      connection.ExecuteQuery("", false);
      connection.CloseConnection();
    }
}*/
class DBConn{
   
    static String[] ConnectionInfo;
    private static  void SetConnectionInfo(String url ,String username,String password ){
        ConnectionInfo = new String[]{url,username,password};
    }   
    public static Connection connect()  throws SQLException{
        SetConnectionInfo("jdbc:postgresql://localhost:5432/traindatabase","newadmin", "Platform#36");
        return DriverManager.getConnection(ConnectionInfo[0],
                                           ConnectionInfo[1],
                                           ConnectionInfo[2]);
       /*  try{
       conn = DriverManager.getConnection(ConnectionInfo[0],
                                            ConnectionInfo[1],
                                            ConnectionInfo[2]);
        stat = conn.createStatement();

    }
    catch(Exception e){
            System.out.println("SQL CONNECTION ERROR "+e.getLocalizedMessage());
    }*/
}
  
    

    }
    

