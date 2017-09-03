import java.io.IOException;

/**
 * Created by leader on 17/8/23.
 */
public class SessionTest {
    public static void main(String[] args) throws IOException{
        //SessionServer server = new SessionServer();
        //server.start(8080);
        SessionClient client = new SessionClient();
        client.connect("localhost", 8088);
        String response = client.sendMessage("hello server");
        System.out.println("hello client".equals(response));
    }
}
