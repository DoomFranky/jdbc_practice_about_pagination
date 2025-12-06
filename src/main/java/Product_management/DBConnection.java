package Product_management;

import java.sql.*;
public class DBConnection {
    String url = "jdbc:postgresql://localhost:5432/product_management_db";
    String user = "product_manager_user";
    String password = "123546";
    Connection con;

    public DBConnection(){
        try{
            this.con = DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public Connection getDBConnection(){
        return this.con;
    }
}