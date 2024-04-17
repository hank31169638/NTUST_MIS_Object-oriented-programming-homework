package HRSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CompanySystem {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MemberService memberService = new MemberService();

        int which = 0;
        while (true) {
            showMenu();

            while (true) {
                try {
                    which = sc.nextInt();
                    if (which < 0 || which > 2) {
                        System.out.print("請輸入正確的選擇!![1,2,0]：");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("請輸入正確的選擇!![1,2,0]：");
                    // 清除錯誤的輸入
                    sc.next();
                }
            }

            switch (which) {
                case 1:
                    memberService.signin();
                    break;
                case 2:
                    memberService.signup();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public static void showMenu() {
        System.out.println("****歡迎進入公司系統****");
        System.out.println("1) 登入");
        System.out.println("2) 註冊");
        System.out.println("0) 離開");
        System.out.print("請輸入您的選擇 [1,2,0]：");
    }
}