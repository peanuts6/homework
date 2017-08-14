/**
 * Created by xqy on 17/8/12.
 */
import java.io.*;

interface Persistence{
    public String persist(Object object);

    public Object findById(String objectId);

    public void deleteById(String id);
}

class PersistenceImp implements Persistence {
    private PersistenceImplementor implementor = null;

    public PersistenceImp(PersistenceImplementor imp) {
        this.implementor = imp;
    }

    @Override
    public void deleteById(String id) {
        implementor.deleteObject(Long.parseLong(id));
    }

    @Override
    public Object findById(String objectId) {
        return implementor.getObject(Long.parseLong(objectId));
    }

    @Override
    public String persist(Object object) {
        return Long.toString(implementor.saveObject(object));
    }
}

//
interface PersistenceImplementor {
    public long saveObject(Object object);

    public void deleteObject(long objectId);

    public Object getObject(long objectId);
}

class FilePersistenceImplementor implements PersistenceImplementor{
    @Override
    public void deleteObject(long objectId) {
        File f = new File("./persistence/"+Long.toString(objectId));
        System.out.println("delete file:"+f.getPath());
        return;
    }

    @Override
    public Object getObject(long objectId) {
        File f = new File("./persistence/"+Long.toString(objectId));
        return readObjectFromFile(f);
    }

    private Object readObjectFromFile(File f) {
        System.out.println("open file:"+f.getPath()+". and load object. return the object");
        return null;
    }

    @Override
    public long saveObject(Object object) {
        long fileId = System.currentTimeMillis();

        File f = new File("./persistence/"+Long.toString(fileId));

        writeObjectToFile(f,object);
        return fileId;
    }

    private void writeObjectToFile(File f, Object object) {
        System.out.println("serialize object:"+" and write it to file:");
    }
}

class DabatasePersistenceImplementor implements PersistenceImplementor{
    public DabatasePersistenceImplementor() {
        System.out.println("load database driver");
    }

    @Override
    public void deleteObject(long objectId) {
        System.out.println("open database connection. remove record with id:"+String.valueOf(objectId));
    }

    @Override
    public Object getObject(long objectId) {
        System.out.println("open database connection. read records. create object from record with id:"+String.valueOf(objectId));
        return null;
    }

    @Override
    public long saveObject(Object object) {
        System.out.println("open database connection. create records for fields inside the object:"+object.toString());
        return 0;
    }
}



public class BridgeDemo {
    public static void main(String[] args){
        PersistenceImplementor implementor = null;
        if(databaseDriverExists()){
            implementor = new DabatasePersistenceImplementor();
        } else {
            implementor = new FilePersistenceImplementor();
        }
        Persistence persistenceAPI = new PersistenceImp(implementor);
        Object o = persistenceAPI.findById("12343755");
        //更改对象，存储
        persistenceAPI.persist(o);

        persistenceAPI = new PersistenceImp(new DabatasePersistenceImplementor());
        persistenceAPI.deleteById("2323");
    }
    private static boolean databaseDriverExists() {
        return false;
    }
}
