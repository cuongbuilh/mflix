package DAO;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import model.Comment;
import model.Movie;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.Collection;
import java.util.Date;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public abstract class AbsDAO {

    static MongoDatabase db;

    MongoDatabase getDB() {
        if (db == null) {
            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//            String conString = "mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false";
//            String conString = "mongodb+srv://root:root@cluster0.lh5rj.mongodb.net";
            String conString = "mongodb+srv://buicuong:buicuong@cluster0.6cnkq.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
            ConnectionString connectionString = new ConnectionString(conString);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .codecRegistry(pojoCodecRegistry)
                    .applyConnectionString(connectionString)
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            db = mongoClient.getDatabase("sample_mflix");
            System.out.println("Connect to DB");
        }
        return db;
    }

}