package dataaccess;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MorphiaDatastore {

    private final Datastore datastore;

    public MorphiaDatastore() {
        MongoDbClient mongoDbClient = new MongoDbClient();

        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("dataobject");

        // create the Datastore connecting to the default port on the local host
        datastore = morphia.createDatastore(mongoDbClient.getClient(), "service");
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return datastore;
    }

}
