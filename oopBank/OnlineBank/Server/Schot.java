package javaLvl2.oopBank.OnlineBank.Server;

public class Schot {
    private String schot;
    private int money;
    private SQL_BD sqlBD;


    public Schot(SQL_BD sql_bd,String schot, int money) {
        this.schot = schot;
        this.money = money;
        this.sqlBD = sql_bd;
    }

    public String withdrawMoney (int money){ //Снять деньги
        this.money = sqlBD.checkMoney(this.schot);
        if (this.money>=money){
            sqlBD.sqlWithdrawMoney(schot,money);
            this.money = sqlBD.checkMoney(this.schot);
            return "Сняли "+money+"рублей\n Остаток: "+this.money+"руб";
        }else  return "На карте не достаточно средств для снятия\nБаланс: "+this.money+"руб";
    }
    public String putMoney (int money){
        this.money = sqlBD.checkMoney(this.schot);
        sqlBD.sqlTransferMoney(schot,money);
        this.money = sqlBD.checkMoney(this.schot);
        return "Деньги приняты\n Баланс: "+this.money+"руб";
    }
    public String transferMoney(String schotAdress, int money){
        String operations ="";
        this.money = sqlBD.checkMoney(this.schot);
        if (this.money >= money){
            if (!(this.schot.equals(schotAdress))){
            sqlBD.sqlTransferMoney(schotAdress,money,this.schot);
            operations = "Перевод успешно выполнен \n";
            sqlBD.addHistory(this.schot,schotAdress,money);
            this.money = sqlBD.checkMoney(this.schot);
           return operations = operations+"Баланс: "+this.money+"\n";
            }else {
                return "ERROR: Вы не можете перевести сами себе\n";
            }
        } return "Недостаточно средств на счету\n Баланс: "+this.money+"руб\n";
    }

    public String getSchot() {
        return schot;
    }

    public int getMoney() {
        this.money = sqlBD.checkMoney(this.schot);
        return money;
    }

    public void setSchot(String schot) {
        this.schot = schot;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
