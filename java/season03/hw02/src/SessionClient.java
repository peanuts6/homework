import java.io.*;
import java.net.*;

/**
 * Created by leader on 17/8/16.
 * java -cp ./ SessionClient
 * login username md5password
 * send message
 */
public class SessionClient {
    String token = "";
    String str;
    Socket socket;
    BufferedReader in;
    BufferedWriter out;
    public void connect(String host,int port) throws IOException {

        socket = new Socket(host, port);
        in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        System.out.println("connect to server:"+host+":"+String.valueOf(port));
        System.out.println("type usename & password ");

        while((str = in.readLine())!=null){
            out.write(str);
            out.flush();
            System.out.println(str+"");
        }

    }
    public String sendMessage(String msg) throws IOException {
        out.write(msg);
        String resp = in.readLine();
        return resp;
    }

    public static void main(String[] args) throws IOException {
        SessionClient client = new SessionClient();
        client.connect("localhost",8088);
    }
}
