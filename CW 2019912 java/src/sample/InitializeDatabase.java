package sample;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class InitializeDatabase {
    public static DB database;
    public static MongoClient mongoClient = new MongoClient("localhost", 27017);

    public static void initCategory() {
        database = mongoClient.getDB("Inventory_Test");
        database.createCollection("Category_Table", null);
        database.createCollection("Products_Table", null);
        database.createCollection("Stock_Table", null);

    }
}
