package model.save;

/**
 * Created by jerome on 21/01/2017.
 */
public class FactoryBDD {
    public BDD getBDD(String type){
        if(type == null){
            return null;
        }
        if (type.equals("MariaDB")) {
            return new MariaDB();
        }
        if (type.equals("MongoDB")) {
            return new MongoDB();
        }
        if (type.equals("File")) {
            return new FileSave();
        }
        return null;
    }
}
