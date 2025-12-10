package Product_management;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataRetriever {
    public List<Category> getAllCategory(){
        List<Category> allCategories = new ArrayList<>();
        DBConnection dbconnection = new DBConnection();
        Connection connection = dbconnection.getDBConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name FROM \"Product_category\"");
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT product.id AS product_id, product.name AS product_name, product.creation_product, category.id AS category_id,category.name AS category_name FROM \"Product\" AS product JOIN \"Product_category\" AS category ON category.product_id = product.id LIMIT ? OFFSET ? ");
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

    public List<Product> getProductsByCriteria(String productName, String categoryName, Instant creationMin, Instant creationMax){
        List<Product> productList = new ArrayList<>();
        DBConnection dbconnection = new DBConnection();
        Connection connection = dbconnection.getDBConnection();

        try{
            //Could be change
            String condition = null;
            if (productName!=null) {
                condition = "WHERE product.name ILIKE ?"; //1
                 if (categoryName!=null) {
                    condition += " AND category.name ILIKE ?";//2
                }
            }else if(categoryName!=null){
                condition = "WHERE category.name ILIKE ?";//1
            }
            
            String sql = "SELECT product.id AS product_id, product.name AS product_name, product.creation_product, category.id AS category_id, category.name AS category_name FROM \"Product\" AS product JOIN \"Product_category\" AS category ON category.product_id = product.id";

            if (condition!=null) {
                sql += " %s";
                sql = sql.formatted(condition);
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Could be change
            if(productName!=null){
                preparedStatement.setString(1, "%"+productName+"%");
                if(categoryName!=null){
                    preparedStatement.setString(2, "%"+categoryName+"%");
                }
            }else if(categoryName!=null){
                preparedStatement.setString(1, "%"+categoryName+"%"); 
            }

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

            if (creationMin!=null) {
                productList
                    .stream()
                    .filter(product -> product.getCreationDateTime().isAfter(creationMin))
                    .collect(Collectors.toList());
            }
                
            if (creationMax!=null) {
                productList
                    .stream()
                    .filter(product -> product.getCreationDateTime().isBefore(creationMax))
                    .collect(Collectors.toList());
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

    public List<Product> getProductsByCriteria(String productName, String categoryName, Instant creationMin, Instant creationMax, int page, int size){
        List<Product> productList = new ArrayList<>();
        DBConnection dbconnection = new DBConnection();
        Connection connection = dbconnection.getDBConnection();

        try{
            //Could be change
            String condition = null;
            if (productName!=null) {
                condition = "WHERE product.name ILIKE ?"; //1
                 if (categoryName!=null) {
                    condition += " AND category.name ILIKE ?";//2
                }
            }else if(categoryName!=null){
                condition = "WHERE category.name ILIKE ?";//1
            }
            
            String sql = "SELECT product.id AS product_id, product.name AS product_name, product.creation_product, category.id AS category_id, category.name AS category_name FROM \"Product\" AS product JOIN \"Product_category\" AS category ON category.product_id = product.id LIMIT ? OFFSET ?";

            if (condition!=null) {
                sql += " %s";
                sql = sql.formatted(condition);
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, page);
            preparedStatement.setInt(2, (page-1)*size);
            //Could be change
            if(productName!=null){
                preparedStatement.setString(3, "%"+productName+"%");
                if(categoryName!=null){
                    preparedStatement.setString(4, "%"+categoryName+"%");
                }
            }else if(categoryName!=null){
                preparedStatement.setString(5, "%"+categoryName+"%"); 
            }

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

            if (creationMin!=null) {
                productList
                    .stream()
                    .filter(product -> product.getCreationDateTime().isAfter(creationMin))
                    .collect(Collectors.toList());
            }
                
            if (creationMax!=null) {
                productList
                    .stream()
                    .filter(product -> product.getCreationDateTime().isBefore(creationMax))
                    .collect(Collectors.toList());
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
