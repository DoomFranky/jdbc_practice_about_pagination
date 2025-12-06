package Product_management;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public List<Category> getAllCategory(){
        List<Category> allCategories = new ArrayList<>();
        DBConnection dbconnection = new DBConnection();
        Connection connection = dbconnection.getDBConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("""
                SELECT id,name FROM "Product_category"
                    """);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Category category =  new Category(rs.getInt("id"), rs.getString("name"));
                allCategories.add(category);
            }
            connection.close();
        }catch (SQLException e){
            try{
                connection.close();
            }catch(SQLException sqlE){
                sqlE.printStackTrace();
            }
            e.printStackTrace();
        }
        return allCategories;
    }

}
