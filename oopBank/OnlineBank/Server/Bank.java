package javaLvl2.oopBank.OnlineBank.Server;

import java.util.TreeMap;

public final class Bank {
    private SQL_BD sqlBd;
    private Client client;
    private Schot schot;
    private static Bank Instance;
    private int namberATM;
    private TreeMap<Integer,Schot> treeSchot = new TreeMap<>();
    private Bank() {
        sqlBd = SQL_BD.getInstance();
        client = new Client("");
    }

    public boolean getNickByLoginPass(String namberShot, int pin)  {
      return sqlBd.getNickByLoginPass(namberShot,pin,this);
    }
    public String getNameClient(){
        return client.getName();
    }
    public void setManeClient(String name){
        client.setName(name);
    }
    public String checkSchotAdress(String schotAdress) {
        return sqlBd.checkLogin(schotAdress);
    }
    public String getHistory (Integer namberATM){
        String operationsHistory = "Доступно последние 10 операций \n"+sqlBd.getHistoryCashInflow(treeSchot.get(namberATM).getSchot())+sqlBd.getHistoryMoneyTransfer(treeSchot.get(namberATM).getSchot());
       return operationsHistory;
    }
    public  void disconnect() {
        sqlBd.sqlDisconnect();
    }
    public String withdrawMoney (int money,Integer namberATM){
        return treeSchot.get(namberATM).withdrawMoney(money);
    }
    public String putMoney (int many, Integer namberATM){
        return treeSchot.get(namberATM).putMoney(many);
    }
    public String transferMoney(String schotAdress, int money,Integer namberATM){
        return treeSchot.get(namberATM).transferMoney(schotAdress,money);
    }
    public String getSchot() {
        return schot.getSchot();
    }
    public int getMoney() {
        return schot.getMoney();
    }
    public void setMoney(int money) {
        schot.setMoney(money);;
    }
    public void setSchot(String schot) {
        this.schot.setSchot(schot);
    }

    public static synchronized Bank getInstance (){
        if (Instance == null){
            Instance = new Bank();
        }
        return Instance;
    }

    public void setNamberATM(int namberATM) {
        this.namberATM = namberATM;
    }

    public void newSchot(String newSchot, int money){
        treeSchot.put(namberATM,schot= new Schot(sqlBd,newSchot,money));
    }
}
