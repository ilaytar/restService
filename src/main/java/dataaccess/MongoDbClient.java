package dataaccess;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.apache.log4j.Logger;

public class MongoDbClient {

    private static Logger log = Logger.getLogger(MongoDbClient.class);
    private static MongoClient client;

    public MongoDbClient() {
        ServerAddress serverAddress = new ServerAddress();
        client = new MongoClient(serverAddress);

        client.getDatabase("service");

        log.info("Connected to {service} database successfully");
    }

    public MongoClient getClient() {
        return client;
    }
}