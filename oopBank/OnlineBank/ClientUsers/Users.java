package javaLvl2.oopBank.OnlineBank.ClientUsers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Users {
    private Socket socket;
    private DataOutputStream out;
    private Scanner scInput;
    private Scanner scUsers = new Scanner(System.in);

    public Users() {
        try {
            socket = new Socket("localhost", 8189);
            scInput = new Scanner(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Клиент Подключился (не авторизован)");
            outThread();
            inputThread();
        } catch (IOException e) {
           System.out.println("ERORR: подключение к серверу");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void outThread (){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String str = scUsers.nextLine();
                    try {
                        out.writeUTF(str);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private void inputThread(){
        try {
            while (true) {
                String str = scInput.nextLine();
                System.out.println(str);
            }
        }catch (Exception e){
        }
    }

}
