import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import Product_management.Category;
import Product_management.DataRetriever;
import Product_management.Product;

public class Main {
    public static void main(String[] args) {
        List<Category> allCategories = new ArrayList<>();
        allCategories.add(new  Category(1, "Informatique"));
        allCategories.add(new Category(2, "Téléphonie"));
        allCategories.add(new Category(3, "Audio"));
        allCategories.add(new Category(4,"Accesoires"));
        allCategories.add(new Category(5, "Informatique"));
        allCategories.add(new Category(6, "Bureau"));
        allCategories.add(new Category(7, "Mobile"));

        DataRetriever dataRetriever = new DataRetriever();

        System.out.println(allCategories);
        System.out.println(dataRetriever.getAllCategory());

        List<Product> productList = new ArrayList<>(); 

        productList.add(new Product(1, "Laptop Dell XPS", Instant.parse("2024-01-15T09:30:00Z"), new Category(1,"Informatique")));
        productList.add(new Product(2, "IPhone 13", Instant.parse("2024-02-01T14:10:00Z"), new Category(2,"Téléphonie")));
        productList.add(new Product(3, "Casque Sony WH1000", Instant.parse("2024-02-10T16:45:00Z"), new Category(3,"Audio")));
        productList.add(new Product(4, "Clavier Logitech", Instant.parse("2024-03-05T11:20:00Z"), new Category(4,"Accesoires")));
        productList.add(new Product(5, "Ecran Samsung 27", Instant.parse("2024-03-18T08:00:00Z"), new Category(5,"Informatique")));
        productList.add(new Product(5, "Ecran Samsung 27", Instant.parse("2024-03-18T08:00:00Z"), new Category(6,"Bureau")));
        productList.add(new Product(2, "IPhone 13", Instant.parse("2024-02-01T14:10:00Z"), new Category(7,"Mobile")));
        System.out.println(productList);
        System.out.println(dataRetriever.getProductList(1, 10));

        productList.remove(6);
        productList.remove(5);
        System.out.println(productList);
        System.out.println(dataRetriever.getProductList(1, 5));

        productList.remove(4);
        productList.remove(3);
        System.out.println(productList);
        System.out.println(dataRetriever.getProductList(1, 3));

        productList.clear();
        productList.add(new Product(3, "Casque Sony WH1000", Instant.parse("2024-02-10T16:45:00Z"), new Category(3,"Audio")));
        productList.add(new Product(4, "Clavier Logitech", Instant.parse("2024-03-05T11:20:00Z"), new Category(4,"Accesoires")));
        System.out.println(productList);
        System.out.println(dataRetriever.getProductList(2, 2));

        productList.clear();
        
        productList.add(new Product(1, "Laptop Dell XPS", Instant.parse("2024-01-15T09:30:00Z"), new Category(1,"Informatique")));
        System.out.println(productList);
        System.out.println(dataRetriever.getProductsByCriteria("Dell", null, null, null));

        productList.clear();
        productList.add(new Product(1, "Laptop Dell XPS", Instant.parse("2024-01-15T09:30:00Z"), new Category(1,"Informatique")));
        System.out.println(dataRetriever.getProductsByCriteria(null, "info", null, null));

        productList.clear();
        productList.add(new Product(2, "IPhone 13", Instant.parse("2024-02-01T14:10:00Z"), new Category(7,"Mobile")));
        System.out.println(dataRetriever.getProductsByCriteria("iPhone", "mobile", null, null));

        productList.clear();

        productList.add(new Product(2, "IPhone 13", Instant.parse("2024-02-01T14:10:00Z"), new Category(7,"Mobile")));
        System.out.println(dataRetriever.getProductsByCriteria(null, null, Instant.parse("2024-02-01T00:00:00Z"), Instant.parse("2024-03-01T00:00:00Z")));
        System.out.println(dataRetriever.getProductsByCriteria("Samsung", "bureau", null, null));
        System.out.println(dataRetriever.getProductsByCriteria("Sony", "inforamtique", null, null));
        System.out.println(dataRetriever.getProductsByCriteria(null, "audio", Instant.parse("2024-01-01T00:00:00Z"), Instant.parse("2024-12-01T00:00:00Z")));

        System.out.println(dataRetriever.getProductsByCriteria(null, null, null, null, 1, 10));
        System.out.println(dataRetriever.getProductsByCriteria("Dell", null, null, null, 1, 5));
        System.out.println(dataRetriever.getProductsByCriteria(null, "informatique", null, null, 1, 10));


    }
}
