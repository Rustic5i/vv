package javaLvl2.oopBank.OnlineBank.Server;

import java.sql.*;
import java.util.Date;

public class SQL_BD{
    private static Connection conn;
    private static Statement stm;
    private static SQL_BD Instance;
    private static String ThePathToTheFile = "jdbc:sqlite:C:\\program\\BANK_SQL.db";


    private SQL_BD() {
        connect();
    }
    public static synchronized SQL_BD getInstance (){
        if(Instance == null) return Instance =  new SQL_BD();
        else return Instance;
    }

    public static void connect (){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(ThePathToTheFile);
            stm = conn.createStatement();
        }catch (Exception e){
            System.out.println("Ошибка загрузки драйверов JDBC");
        }

    }
    public boolean getNickByLoginPass(String namberShot, int pin, Bank bank)  {
        try {
            ResultSet rs = stm.executeQuery("SELECT * FROM users WHERE Schot = '"+namberShot+"' AND Pin= '"+pin+"'");
            if (rs.next()){
                bank.newSchot(rs.getString(1),rs.getInt(3));
               // bank.setSchot(rs.getString(1));
                bank.setManeClient(rs.getString(2));
              //  bank.setMoney(rs.getInt(3));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void sqlTransferMoney (String schotAdress, int tranferMoney, String schot){
        try {
           conn.setAutoCommit(false);
           stm.executeUpdate("UPDATE `users` SET `Money` = `Money`+'"+tranferMoney+"' WHERE (`Schot` = '"+schotAdress+"')");
           stm.executeUpdate("UPDATE `users` SET `Money` = `Money`-'"+tranferMoney+"' WHERE (`Schot` = '"+schot+"')");
           conn.commit();
           conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void sqlTransferMoney (String schotAdress, int tranferMoney){
        try {
            stm.executeUpdate("UPDATE `users` SET `Money` = `Money`+'"+tranferMoney+"' WHERE (`Schot` = '"+schotAdress+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void sqlWithdrawMoney (String schotAdress, int tranferMoney){
        try {
            stm.executeUpdate("UPDATE `users` SET `Money` = `Money`-'"+tranferMoney+"' WHERE (`Schot` = '"+schotAdress+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public String checkLogin(String schotAdress) {
        try {
            ResultSet rs = stm.executeQuery("SELECT Name FROM users WHERE Schot = '"+schotAdress+"'");
            return rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e);
        }
       return null;
    }

    public  void sqlDisconnect() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void listUsers (){
        try {
            ResultSet rs = stm.executeQuery("SELECT * FROM users");
            while (rs.next()){
                System.out.println("Счет: "+rs.getString(1)+" Имя "+rs.getString(2)+" Сумма "+rs.getString(3)+"руб pin "+rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addHistory (String senderSchot, String schot,int money){
        Date date = new Date();
        try {
             stm.executeUpdate("insert into history (Date,Sender_Schot,SchotName,MoneyTransfer) values ('"+date+"','"+senderSchot+"','"+schot+"','"+money+"')");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public String getHistoryMoneyTransfer (String schot){
        String history = "";
        try {

            int i =0;
            ResultSet rs = stm.executeQuery("SELECT  Date, Name, MoneyTransfer, id FROM history inner join users on history.SchotName = users.Schot WHERE Sender_Schot = '"+schot+"'ORDER BY id DESC");
            while (rs.next() &&  (i < 6)  ){
               history = history+"-----------------------------------------------\n";
               history = history+rs.getString(1)+"\n";
               history = history+"Перевод "+rs.getString(2)+" "+rs.getInt(3)+"руб\n";
               i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("КАКАЯ-ТА ОШИБКА");
            history = "КАКАЯ-ТА ОШИБКА";
        }
        return history;
    }
    public String getHistoryCashInflow (String schot){ // Поступление денежных средств
        String history = "";
        try {
            int i =0;
            ResultSet rs = stm.executeQuery("SELECT  Date, Name, MoneyTransfer, id FROM history INNER JOIN users ON history.Sender_Schot = users.Schot WHERE SchotName = '"+schot+"' ORDER BY id DESC");
            while (rs.next() &&  (i < 6)  ){
                history = history+"-----------------------------------------------\n";
                history = history+rs.getString(1)+"\n";
                history = history+"Поступления от: "+rs.getString(2)+" "+rs.getInt(3)+"руб\n";
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
    public int checkMoney(String schotAdress) {
        try {
            ResultSet rs = stm.executeQuery("SELECT Money FROM users WHERE Schot = '"+schotAdress+"'");
            System.out.println(rs.getString(1));
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("ERROR_SQL_checkMoney....");
        return 0;
    }
}

