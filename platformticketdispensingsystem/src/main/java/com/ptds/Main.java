package com.ptds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
      
      DBConn connection = new DBConn();
      connection.ExecuteQuery("", false);
      connection.CloseConnection();  
                
}
}
class DBConn{
    Connection conn;
    Statement stat;
    ResultSet res;
    String[] ConnectionInfo;
    void SetConnectionInfo(String url ,String username,String password ){
        this.ConnectionInfo = new String[]{url,username,password};
    }   
    DBConn(){
        SetConnectionInfo("jdbc:postgresql://localhost:5432/SampleDatabase",
        "newadmin", "Platform#36");
        try{
        conn = DriverManager.getConnection(ConnectionInfo[0],
                                            ConnectionInfo[1],
                                            ConnectionInfo[2]);
        stat = conn.createStatement();

    }
    catch(Exception e){
            System.out.println(e.getLocalizedMessage());
    }
}
    void CloseConnection(){
        try{
        conn.close();
        } catch (Exception e ){
            System.err.println(e.getLocalizedMessage());
        }
    }
    void ExecuteQuery(String query, boolean returnResult ){
        try {
        if (returnResult == true ){
           
            res = stat.executeQuery(query);
        }else{
            stat.executeQuery(query);
        }

    }
        catch(Exception e ){
            System.out.println(e.getLocalizedMessage());
        }
    }
    }
    

