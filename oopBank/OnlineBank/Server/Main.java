package javaLvl2.oopBank.OnlineBank.Server;

public class Main {
    public static void main(String[] args) {
        SQL_BD.connect();
        SQL_BD.listUsers();
        Server server = new Server();
    }

}
