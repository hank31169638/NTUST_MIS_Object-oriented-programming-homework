import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class IDchecker {

    static String[] City = {"台北市", "臺中市", "基隆市", "台南市", "高雄市", "台北縣", "宜蘭縣", "桃園縣", "嘉義市", "新竹縣", "苗栗縣", "臺中縣", "南投縣", "彰化縣", "新竹市", "雲林縣", "嘉義縣", "台南縣", "高雄縣", "屏東縣", "花蓮縣", "台東縣", "金門縣", "澎湖縣", "陽明山管理局", "連江縣"};

    public static boolean regexLetter(String id) {
        String regex_az = "^[A-Z].*";
        return !id.substring(0, 1).matches(regex_az);
    }

    public static String regexGender(String id) {
        if (id.matches("^.2.*")) {
            return "女姓";
        } else if (id.matches("^.1.*")) {
            return "男姓";
        }
        return "-1";
    }

    public static String GetCity(int BornCity) {
        return City[BornCity - 10];
    }

    public static int Id_to_Int(String id) {
        int BornCity;
        // switch if id is W Z I O these special string
        switch (id.substring(0, 1)) {
            case "W":
                BornCity = 32;
                break;
            case "Z":
                BornCity = 33;
                break;
            case "I":
                BornCity = 34;
                break;
            case "O":
                BornCity = 35;
                break;
//            logic process
            default:
                BornCity = id.charAt(0) - 55;
                if (BornCity > 17 && BornCity <= 23) {
                    BornCity--;
                } else if (BornCity > 23 && BornCity <= 31) {
                    BornCity -= 2;
                } else if (BornCity > 32) {
                    BornCity -= 3;
                }
        }
        return BornCity;
    }

    public static void verify(String id) {

        int sum = 0;
        String BornCity;
        String gender = regexGender(id);


        if (regexLetter(id) || gender.equals("-1") || id.length() != 10) {
            System.out.println("格式不符");
            return;
        }

        BornCity = Integer.toString(Id_to_Int(id));
        // 城市的頭

        sum += Integer.parseInt(BornCity.substring(0, 1));
        sum += Integer.parseInt(BornCity.substring(1, 2)) * 9;

        // 中間8碼
        for (int i = 0; i < 8; i++) {
            sum += (8 - i) * Integer.parseInt(id.substring(i + 1, i + 2));
        }

        // 加上檢核碼
        sum += Integer.parseInt(id.substring(9, 10));

        if (sum % 10 == 0) {
            System.out.println("身分證字號正確！");
            String City = GetCity(Integer.parseInt(BornCity));
            System.out.printf("是位出生在%s的%s朋友呢\n", City, gender);
        } else {
            System.out.println("身分證字號錯誤");
        }
    }

    public static void generator(String city, String gender) {
        // 判斷city有沒有在city list內
        boolean Cityexists = Arrays.asList(City).contains(city);
        // 助教您的圖表內寫陽明山管理局，所以我的City 初始化為陽明山管理局
        if (!Cityexists) {
            System.out.println("縣市錯誤");
            return;
        }
        if (!gender.matches("^[男女]")) {
            System.out.println("性別錯誤");
            return;
        }

        Random random = new Random();
        String generate = "";
        String BornCity;
        int sum = 0;
        // 加入對應的字母
        for (int i = 0; i < City.length; i++) {
            if (City[i].equals(city)) {
                // 變成英文
                generate += String.valueOf((char) (i + 65));
                break;
            }
        }

        generate += gender.equals("男") ? "1" : "2";
        // 第三位數的權重

        for (int i = 0; i < 7; i++) {
            int temp = random.nextInt(9);
            generate += String.valueOf(temp);
            sum += (7 - i) * temp;
        }
        // 增加性別運算
        sum += gender.equals("男") ? 8 : 16;
        // 計算縣市權重
        BornCity = Integer.toString(Id_to_Int(generate));

        // 程式權重
        sum += Integer.parseInt(BornCity.substring(0, 1));
        sum += Integer.parseInt(BornCity.substring(1, 2)) * 9;

        // 加入檢核碼
        generate += 10 - sum % 10;

        System.out.printf("產生的身分證字號為：" + generate + "\n");
    }

    public static void showMenu() {
        System.out.println("* * * * * * * Menu * * * * * * *");
        System.out.println("1) 驗證身分證字號");
        System.out.println("2) 產生身分證字號");
        System.out.println("0) 離開");
        System.out.println("* * * * * * * * * * * * * * * * *");
        System.out.print("請選擇：");
    }

    // 主程式
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            String chose = sc.nextLine();
            switch (chose) {
                case "1":
                    System.out.print("請輸入身分證字號：");
                    String id = sc.next();
                    verify(id);
                    break;
                case "2":
                    System.out.print("請輸入縣市：");
                    String city = sc.nextLine();

                    System.out.print("請輸入性別(男/女)：");
                    String gender = sc.nextLine();

                    generator(city, gender);
                    break;
                case "0":
                    System.exit(0);
                    sc.close();
                    break;
                default:
                    System.out.println("無此選項");
            }
        }

    }
}
