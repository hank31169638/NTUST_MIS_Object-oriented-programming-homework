package HRSystem;

public class GeneralStaff {

    private String name;
    private String password;
    private int department_number;

    private String work_number;

    public GeneralStaff(String name, String password, int department_number, String work_number) {
        this.name = name;
        this.password = password;
        this.department_number = department_number;
        this.work_number = work_number;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getDepartment_number() {
        return department_number;
    }

    // 複寫 showNameInfo
    public void showNameInfo() {
        System.out.println("一般員工：" + this.name);
    }

    // 複寫 showAllInfo
    public void showAllInfo() {
        System.out.println("姓名：" + this.name);
        System.out.println("部門：" + switch_numberTo_departmentString(this.department_number));
        System.out.println("工號：" + this.work_number.substring(3));
    }

    public String switch_numberTo_departmentString(int department_number) {
        switch (department_number) {
            case 1:
                return "IT資訊部門";
            case 2:
                return "HR人力資源";
            case 3:
                return "MA行銷部門";
        }
        // 應該是用不到
        System.out.println("部門編號錯誤!");
        return "";
    }

}
