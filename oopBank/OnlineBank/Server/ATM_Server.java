package javaLvl2.oopBank.OnlineBank.Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ATM_Server {
    private Bank bank;
    private Server server;
    private Socket socket;
    private PrintWriter out;
    private DataInputStream in;
    private String clientSchot;
    private Integer namberATM;

    public ATM_Server(Server server, Socket socket,Integer namberATM) {
        this.server = server;
        this.socket = socket;
       this.bank = Bank.getInstance();
       this.namberATM = namberATM;
       bank.setNamberATM(namberATM);
        try {
            out =  new PrintWriter (socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("МЫ В АТМ");
                outCommand("МЫ В АТМ");

                try {
                    menyAut();
                    mainMeny();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void menyAut (){
        while (true){
            try {
                outCommand("Введите номер счета");
                String stringSchot = in.readUTF();
                if (server.checkOnlineUser(stringSchot)) {
                    outCommand("Введите пин код **** от счета " + stringSchot);
                    int pin = Integer.parseInt(in.readUTF());
                    if (bank.getNickByLoginPass(stringSchot, pin)) {
                        clientSchot = bank.getSchot();
                        outCommand("Добро пожаловать " + bank.getNameClient());
                        outCommand("СЧЕТ : " + bank.getSchot() + " Баланс: " + bank.getMoney() + "руб");
                        server.addUsers(this);
                        break;
                    } else {
                        outCommand("Неверный номер счета или pin ");
                    }
                }else outCommand("такой клиент уже подключился к ATM");
            }catch (NumberFormatException | IOException E){
                outCommand("ERROR: Некорректные данные");
            }
        }
    }
    public void mainMeny() throws IOException {
        while (true) {
            try {
                outCommand("Список команд:" +
                        "\n(1) Положить деньги " +
                        "\n(2) Снять деньги " +
                        "\n(3) Перевести деньги " +
                        "\n(4) Посмотреть историю переводов" +
                        "\n(5) Выйти");
                int sumMoney;
                int command = Integer.parseInt(in.readUTF());
                switch (command){
                    case 1:
                        outCommand("Введите сумму сколько хотите положить денег");
                        sumMoney = Integer.parseInt(in.readUTF());
                        outCommand(bank.putMoney(sumMoney,namberATM));
                        break;
                    case 2:
                        outCommand("Введите сумму снятие наличных");
                        sumMoney = Integer.parseInt(in.readUTF());
                        outCommand(bank.withdrawMoney(sumMoney,namberATM));
                        break;
                    case 3:
                        menuTransfer();
                        break;
                    case 4: outCommand(bank.getHistory(namberATM));;
                        break;
                    case 5:
                        outCommand("Всего доброго =)");
                        in.close();
                        out.close();
                        server.removeUsers(this);
                        System.out.println("Всего доброго =)");
                    default: System.out.println("ERROR : Некорректная команда");
                        outCommand("ERROR : Некорректная команда");
                }
            }catch (NumberFormatException e){
                outCommand("ERROR: Некорректная команда");
            }

        }
    }

    public void menuTransfer () throws IOException {
        while (true) {
            outCommand("Введите номер счета получателя");
            String namberShot = in.readUTF();
            try {
                if (!bank.checkSchotAdress(namberShot).equals(null)) {
                    outCommand("Имя получателя: " + bank.checkSchotAdress(namberShot));
                    outCommand("(1) Далее.");
                    outCommand("(2) Назад.");
                    int command = Integer.parseInt(in.readUTF());
                    if (command == 1) {
                        outCommand("Введите сумму перевода");
                        int sumMoney3 = Integer.parseInt(in.readUTF());
                        outCommand(bank.transferMoney(namberShot, sumMoney3,namberATM));
                        server.msgOperationUser(namberShot,sumMoney3);
                        break;}
                }else outCommand("Неверная команда");

            } catch (NullPointerException e) {
                outCommand("ERROR: Счет получателя не найден");
            }
        }
    }
    public void outCommand (String command){
        out.println(command);
        out.flush();
    }
    public void outCommand (String command,String schot){
        out.println(command+bank.getNameClient());
        out.flush();
    }
    public String getClientSchot() {
        return clientSchot;
    }
}
