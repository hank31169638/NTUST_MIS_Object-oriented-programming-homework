package HRSystem;

public class ManagerStaff extends GeneralStaff {

    public ManagerStaff(String name, String password, int department_number, String work_number) {
        // 傳入 註冊資料
        super(name, password, department_number, work_number);
    }

    public void showNameInfo() {
        System.out.println("主管：" + getName());
    }
}
