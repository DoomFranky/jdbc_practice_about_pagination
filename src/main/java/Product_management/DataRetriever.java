package Product_management;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public List<Category> getAllCategory(){
        List<Category> allCategories = new ArrayList<>();
        DBConnection dbconnection = new DBConnection();
        Connection connection = dbconnection.getDBConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("""
                SELECT id,name FROM "Product_category"
                    """);
            ResultSet rs = preparedStatement.executeQuery();
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

    public List<Product> getProductList(int page, int size){
        List<Product> productList = new ArrayList<>();
        DBConnection dbconnection = new DBConnection();
        Connection connection = dbconnection.getDBConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT product.id AS product_id,
                        product.name AS product_name, 
                        product.creation_product, 
                        category.id AS category_id,
                        category.name AS category_name
                    FROM "Product" AS product
                    JOIN "Product_category" AS category
                    ON category.product_id = product.id 
                    LIMIT ? OFFSET ?
                    """);
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (page-1)*size);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Product product =  new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    LocalDateTime
                        .parse(rs.getString("creation_product"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        .atZone(ZoneId.of("UTC"))
                        .toInstant()
                    ,new Category(
                        rs.getInt("category_id"), 
                        rs.getString("category_name")
                    )
                );
                productList.add(product);
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
        return productList;
    }
}
