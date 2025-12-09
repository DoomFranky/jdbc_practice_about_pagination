package Product_management;

import java.time.Instant;

public class Product {
    private final int id;
    private final String name;
    private final Instant creationDateTime;
    private final Category category;

    public Product(int id, String name, Instant creationDateTime,Category category){
        this.id = id;
        this.name = name;
        this.creationDateTime = creationDateTime;
        this.category = category;
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Instant getCreationDateTime() {
        return this.creationDateTime;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getCategoryName(){
        return category.getName();
    }
}
