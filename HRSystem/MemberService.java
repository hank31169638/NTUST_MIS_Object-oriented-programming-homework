package HRSystem;

import java.util.Map;
import java.util.Scanner;

public class MemberService {
    public static final Scanner sc = new Scanner(System.in);

    Database database = new Database();

    private final Map<String, GeneralStaff> Members = database.getMembers();

    // index 0 -> IT ; 1-> HR ; 2-> MKT
    private final int[] manager_member = {1, 1, 1};
    private final int[] general_member = {1, 1, 1};

    public void signin() {
        System.out.print("請輸入工號(部門-號碼)：");
        String work_number = sc.next();

        if (Members.containsKey(work_number)) {
            GeneralStaff members = Members.get(work_number);

            System.out.print("請輸入密碼：");
            String password = sc.next();

            if (members.getPassword().equals(password)) {

                // 先顯示他的基本資料
                members.showAllInfo();
                if (work_number.charAt(3) == '1' || work_number.charAt(4) =='1'){
                    // 如果是管理人員 IT,HR 使用(3)
                    show_general_member(work_number,members.getDepartment_number());
                }

            } else {
                System.out.println("密碼錯誤！");
            }
        } else {
            System.out.println("查無此員工！");
        }
    }

    public void signup() {
        System.out.print("請輸入姓名：");
        String name = sc.next();

        System.out.print("請輸入密碼：");
        String password = sc.next();

        System.out.println("\n1) 資訊部門");
        System.out.println("2) 人力資源");
        System.out.println("3) 行銷部門");
        System.out.print("請選擇部門：");
        int which_department = sc.nextInt();
        System.out.println("是否為管理人員 [Y/n]?");
        boolean is_manager = sc.next().equalsIgnoreCase("y");

        String work_number = generate_work_number(which_department,is_manager);

        if (is_manager) {
            database.putMembers(work_number, new ManagerStaff(name, password, which_department,work_number));
        } else {
            database.putMembers(work_number, new GeneralStaff(name, password, which_department,work_number));
        }
        System.out.println("註冊成功!");
    }

    public String generate_work_number(int which_department, boolean is_manager) {

        String department = switch_numberTo_departmentString(which_department);
        String work_number;

        // 分別建立管理者和非管理者之工號
        if (is_manager) {
            work_number = department + "-10000" + manager_member[which_department - 1];
            manager_member[which_department - 1]++;
        } else {
            work_number = department + "-00000" + general_member[which_department - 1];
            general_member[which_department - 1]++;
        }
        return work_number;
    }


    public void show_general_member(String work_number,int which_department){
        // 需得到存取員工數量的變量
        String department;
        if (work_number.length() == 9){
            department = work_number.substring(0,2);
        }else {
            department = work_number.substring(0,3);
        }

        // 得到 1 或 2 或 3 轉換成陣列中的index對照
        int department_number = --which_department;

        System.out.println(department+" 部門名單：");
        // 先顯示主管
        for (int i = 1; i < manager_member[department_number]; i++) {
            // 如果是登入的人的話就跳過不顯示
            GeneralStaff Gs = Members.get(department+"-10000"+i);
            Gs.showNameInfo();
        }
        // 再顯示員工
        for (int i = 1; i < general_member[department_number]; i++) {
            GeneralStaff Gs = Members.get(department+"-00000"+i);
            Gs.showNameInfo();
        }
    }

    public String switch_numberTo_departmentString(int department_number){
        switch (department_number) {
            case 1:
                return  "IT";
            case 2:
                return  "HR";
            case 3:
                return  "MA";
        }
        // 沒提到要例外判斷，不探討錯誤
        System.out.println("部門編號錯誤!");
        return "";
    }
}
