package javaLvl2.oopBank.OnlineBank.Server;

public class Client  {
    private String name;
    private int schot;
    public Client(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getSchot() {
        return schot;
    }
    public void setSchot(int schot) {
        this.schot = schot;
    }
}
