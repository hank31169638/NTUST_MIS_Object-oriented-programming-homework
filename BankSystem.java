import java.util.Scanner;

public class BankSystem {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Money money = new Money();

        while (true){
            showMenu();
            if (!getInformation(sc,money)){
                break;
            }
        }
    }


    static void showMenu(){
        System.out.println("***********Menu***********");
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Show balance");
        System.out.println("0) Logout");
        System.out.println("**************************");

    }

    static Boolean getInformation(Scanner sc,Money money){
        System.out.print("Please enter a number [0,1,2,3]：");
        int chose = sc.nextInt();
        switch (chose){
            case 0:
                System.out.println("Bye Bye!");
                return false;
            case 1:
                System.out.println("Please enter a amount you want to deposit：");
                int dePosit = sc.nextInt();
                money.setMoney(money.getMoney() + dePosit);
                break;
            case 2:
                System.out.println("Please entry a amount you want to withdraw");
                int withdraw = sc.nextInt();
                if (withdraw > money.getMoney()){
                    System.out.println("You don't have enough money.");
                }
                else {
                    money.setMoney(money.getMoney()-withdraw);
                }
                break;
            case 3:
                System.out.println(money.getMoney());



        }
        return true;
    }

}

class Money{
    private int money = 0;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
