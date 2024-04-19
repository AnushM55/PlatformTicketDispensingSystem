package com.ptds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String strUrl = "jdbc:postgresql://localhost:5432/SampleDatabase";
        String strUsr = "newadmin";
        String PWD = "";
        
        try {
            Connection conn = DriverManager.getConnection(strUrl, strUsr, PWD);
            Statement stat = conn.createStatement();
            ResultSet recst = stat.executeQuery("select * from SampleTable;");
            System.out.println(recst.getMetaData());
            conn.close();

            
        }
    catch(Exception e){
        System.err.println("error : "+e.getLocalizedMessage());
    }
}
}
