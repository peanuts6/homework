import leader.bean.*;
import leader.service.UserService;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.*;

/**
 * Created by leader on 17/8/20.
 */
public class SessionServer {
    String str,requestUrl,method;
    Socket socket;
    LineNumberReader in;
    OutputStream out;
    ServerSocket server;
    ExecutorService executor = null;
    UserService service;
    public void start(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("start service with port: "+String.valueOf(port)+". waiting for clients...");

            while(true){
                socket = server.accept();
                System.out.println("accept new client ...");
                in = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
                out = socket.getOutputStream();
                while(true) {
                    str = in.readLine();
                    System.out.println(str);
                    if(in.getLineNumber() == 1) {
                        requestUrl = str.substring(str.indexOf('/') + 1, str.lastIndexOf(' '));
                        method = str.substring(0,str.indexOf(' '));
                    }else{
                        if(str.isEmpty()){
                            System.out.println(" Header finished -------- ");
                            break;
                        }
                    }
                }
                System.out.println("next line is empty");
                String payload = "";
                //read body
                str = in.readLine();
                str = in.readLine();

                System.out.println("start request body...");
                while((str = in.readLine())!=null) {
                    //str = in.readLine();
                    System.out.println(str+" ------- ");
                    if(str.isEmpty()){
                        System.out.println("Package finished!");
                        break;
                    }
                    payload +=str;
                }
                System.out.println(payload+" ---");
                handleRequest(requestUrl,method,payload,socket);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {

        }
    }
    public void handleRequest(String requestUrl, String method, String param, Socket socket) throws IOException {
        String msg = "";
        String response = method + " /"+requestUrl+" HTTP/1.1 200 OK\r\n";
        response +="Content-Type: text/html; Charset=utf-8";
        System.out.println(response);
        String subfix = requestUrl.substring(requestUrl.indexOf('/')+1,requestUrl.length());
        long userId;
        boolean complete;
        String token = "";
        //post param
        String[] p, u, m;
        String userName = "";
        String pwd = "";
        if (!param.isEmpty()) {
            p = param.split("&");
            if (p.length > 0) {
                u = p[0].split("=");
                if (u.length > 1) {
                    userName = u[1];
                }
            }
            if (p.length > 1) {
                m = p[1].split("=");
                if (m.length > 1) {
                    pwd = m[1];
                }
            }
        }

        //user controller
        if(requestUrl.startsWith("user")) {
            switch (method) {
                case "GET":
                    //userList
                    Map<String, User> users = service.queryUsers("le", true);
                    for (Map.Entry<String, User> obj : users.entrySet()) {
                        msg += obj.getValue().toString();
                    }
                    break;
                case "POST":
                    //boolean
                    User user = new User();
                    user.setUserId((long) Math.random() * 10000);

                    user.setUserName(userName);
                    user.setPassword(pwd);
                    complete = service.createUser(user);
                    msg += complete ? "create user(" + user.getUserId() + ") successful" : "create user error";
                    break;
                case "PUT":
                    //boolean
                    userId = Long.valueOf(subfix);
                    complete = service.disableUser(userId);
                    msg += complete ? "disabled user(" + String.valueOf(userId) + ") successful" : "disabled user(" + String.valueOf(userId) + ") error";
                    break;
                case "DELETE":
                    //boolean
                    userId = Long.valueOf(subfix);
                    complete = service.deleteUser(userId);
                    msg += complete ? "delete user(" + String.valueOf(userId) + ") successful" : "delete user(" + String.valueOf(userId) + ") error";
                    break;
                default:
                    msg = "";
                    break;
            }
        }else if(requestUrl.startsWith("login")){
            if(method.equals("POST")) {
                UserSession us = service.login(userName,pwd);
                token = us.getSessionId();
            }
        }else{
            msg = "welcome to leader service";
        }
        response +="Content-Length: "+(msg.length())+"\r\n";
        response +="Connection: keep-alive\r\n";
        if(!token.isEmpty()){
            response += "Cookie: "+token +"\r\n";
        }
        response +="\r\n";
        response += msg;
        System.out.println(response);
        out.write(response.getBytes());
        out.flush();
    }

    public void stop() throws IOException{
        in.close();
        out.close();
        socket.close();
        server.close();
    }

    public static void main(String[] args){
        SessionServer s = new SessionServer();
        s.start(8088);
    }
}


