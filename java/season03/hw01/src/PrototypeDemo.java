/**
 * Created by leader on 17/8/11.
 */
import java.util.*;

interface ClientSession{
    ClientSession clone();
    String getSessionId();
    void execute();
}
class AppClient implements ClientSession{
    private String sessionId = "App";

    @Override
    public ClientSession clone(){
        return new AppClient();
    }
    @Override
    public String getSessionId(){
        return sessionId;
    }
    @Override
    public void execute(){
        System.out.println("session:"+sessionId +" does something of app");
    }
}
class WebClient implements ClientSession{
    private String sessionId = "Web";

    @Override
    public ClientSession clone(){
        return new WebClient();
    }
    @Override
    public String getSessionId(){
        return sessionId;
    }
    @Override
    public void execute(){
        System.out.println("session:"+sessionId +" does something of web");
    }
}

class SessionModule{
    private static List<ClientSession> clientSessions = new ArrayList<>();

    public static void addSession(ClientSession cs){
        clientSessions.add(cs);
    }
    public static ClientSession createSession(String sessionId){
        for (ClientSession cs : clientSessions) {
            if(cs.getSessionId().equals(sessionId)){
                return cs.clone();
            }
        }
        System.out.println("session id: "+sessionId +" is not exists");
        return null;
    }
}



public class PrototypeDemo {
    public static void main(String[] args){
        String[] a = {"App","Native","Web"};
        SessionModule.addSession(new AppClient());
        SessionModule.addSession(new WebClient());
        List<ClientSession> css = new ArrayList<>();
        for(String c:a){
            ClientSession cs = SessionModule.createSession(c);
            if(cs != null){
                css.add(cs);
            }
        }

        for(ClientSession cs: css){
            cs.execute();
        }
    }
}
