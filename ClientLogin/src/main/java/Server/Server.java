package Server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
public class Server {
    public static final Integer port = 6565;
    private static DataOutputStream out;
    private static DataInputStream input;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket((port), 1000);
        if (serverSocket.isClosed()) serverSocket.setSoTimeout(10);
        while (true) {
            Socket socket = serverSocket.accept();
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            int length = input.readUnsignedByte();
            byte[] bytes = new byte[length];
            input.readFully(bytes);
            String email = new String(bytes, StandardCharsets.UTF_8);

            int length2 = input.readUnsignedByte();
            byte[] bytes2 = new byte[length2];
            input.readFully(bytes2);
            String password = new String(bytes2, StandardCharsets.UTF_8);

            if(!email.isBlank() && !password.isBlank()) {
                String olResult = email.replaceAll("\\s+", randomChars());
                String doResult = password.replaceAll("\\s+", randomChars());
                OutputClient(olResult, doResult);
            } else {
                OutputClient(email, password);
            }
        }
    }
    public static void OutputClient(String email, String password) {
        Configuration configuration = new Configuration().addAnnotatedClass(RoleAdmin.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            RoleAdmin role = new RoleAdmin(email, password);
            session.persist(role);

            session.beginTransaction().commit();
        }
        long millis = System.currentTimeMillis();
        if(millis >= 100000) {
            try {
                out.writeByte(100);
                out.flush();
            } catch (IOException exception) {
                throw new RuntimeException("Error flush: " + exception.getMessage());
            }
        }
    }
    public static String randomChars() {
      StringBuilder stringBuilder = new StringBuilder();
      var token = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
      for(int i = 0; i < 1; i++) {
          stringBuilder.append(token.charAt((int)Math.floor(Math.random() * token.length())));
      }
      return stringBuilder.toString();
    }
}