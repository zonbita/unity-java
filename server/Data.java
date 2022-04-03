import java.util.Iterator;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import java.net.UnknownHostException;
import org.bson.Document;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.MongoClientOptions;
import java.util.logging.Logger;
import java.util.logging.Level;
public class Data {

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        MongoClientOptions options = new MongoClientOptions.Builder().socketKeepAlive(true).build();
        MongoClient mongo = null;
        mongo = new MongoClient("localhost", options);

        try {
            // Creating Credentials
            //        MongoCredential credential;
            //        credential = MongoCredential.createCredential("", "farm_game",
            //                "".toCharArray());
            DB database = mongo.getDB("farm_game");
            DBCollection collection = database.getCollection("data");
            System.out.println("Collection successfully");

            selectAllRecordsFromACollection(collection);
            //selectOneForString(collection,"name","zon");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mongo.close();
            } catch (Exception e2) {
            }
        }
    }



    private static void selectFirstRecordInCollection(DBCollection collection) {
        DBObject dbObject = collection.findOne();
        System.out.println(dbObject);
    }
    private static void selectOneForString(DBCollection collection, String Field, String Value) {
        DBObject dbObject = collection.findOne();

        //System.out.println(dbObject);
    }

    private static void selectAllRecordsFromACollection(DBCollection collection) {
        DBCursor cursor = collection.find();

        while(cursor.hasNext()) {
            DBObject d = cursor.next();
            System.out.println(cursor.next());

        }
    }

    private static void selectSingleRecordAndFieldByRecordNumber(DBCollection collection) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("employeeId", 5);
        BasicDBObject fields = new BasicDBObject();
        fields.put("employeeId", 1);

        DBCursor cursor = collection.find(whereQuery, fields);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void selectAllRecordByRecordNumber(DBCollection collection) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("employeeId", 5);
        DBCursor cursor = collection.find(whereQuery);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void in_Example(DBCollection collection) {
        BasicDBObject inQuery = new BasicDBObject();
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(4);
        list.add(5);
        inQuery.put("employeeId", new BasicDBObject("$in", list));
        DBCursor cursor = collection.find(inQuery);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void lessThan_GreaterThan_Example(
            DBCollection collection) {
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("employeeId", new BasicDBObject("$gt", 2).append("$lt", 5));
        DBCursor cursor = collection.find(gtQuery);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void negation_Example(DBCollection collection) {
        BasicDBObject neQuery = new BasicDBObject();
        neQuery.put("employeeId", new BasicDBObject("$ne", 4));
        DBCursor cursor = collection.find(neQuery);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void andLogicalComparison_Example(DBCollection collection) {
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("employeeId", 2));
        obj.add(new BasicDBObject("employeeName", "TestEmployee_2"));
        andQuery.put("$and", obj);

        System.out.println(andQuery.toString());

        DBCursor cursor = collection.find(andQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void regex_Example(DBCollection collection) {
        BasicDBObject regexQuery = new BasicDBObject();
        regexQuery.put("employeeName",
                new BasicDBObject("$regex", "TestEmployee_[3]")
                        .append("$options", "i"));

        System.out.println(regexQuery.toString());

        DBCursor cursor = collection.find(regexQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

}