package javaLvl2.oopBank.OnlineBank.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
       private ServerSocket server = null;
       private Vector<ATM_Server> vectorATM = new Vector<>();
       private Integer namberATM=1;
    public Server() {
        try {
            server = new ServerSocket(8189);
            System.out.println("Cервер запущен, ожидаем подключения.....");
            while (true){
                Socket socket = server.accept();
                new ATM_Server(this,socket,namberATM);
                System.out.println("Кто то подключился");
                namberATM++;
            }
        } catch (IOException e) {
            System.out.println("ERORR: ошибка сервера");
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void addUsers (ATM_Server o){
        vectorATM.add(o);
    }
    public void removeUsers(ATM_Server o){
        vectorATM.remove(o);
    }
    public boolean checkOnlineUser(String ClientSchot){
        for (ATM_Server o: vectorATM) {
            if (o.getClientSchot().equals(ClientSchot)) return false;
        }
        return true;
    }
    public void msgOperationUser(String schot,int money){
        for (ATM_Server o :vectorATM) {
            if (o.getClientSchot().equals(schot)){
                o.outCommand("Перевод "+money+"руб"+" от ", schot);
            }
        }
    }
    }

