import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class AdminSys {
    private static final AddressBookList BL = new AddressBookList();

    public static void adminTable() {
        System.out.println("系统初始化...");
        BL.init();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("========= 联系人系统 =========");
            System.out.println("1. 查找联系人(名字)");
            System.out.println("2. 查找联系人(电话)");
            System.out.println("3. 显示所有联系人");
            System.out.println("4. 添加联系人");
            System.out.println("5. 删除联系人");
            System.out.println("6. 修改联系人信息");
            System.out.println("7. 返回");
            System.out.println("========================");
            if (userInput.hasNext()) {
                switch (parseInt(userInput.nextLine())) {
                    case 1 -> // todo;
                    case 2 -> // todo;
                    case 3 -> BL.displayAll();
                    case 4 -> {
                        while (true) {
                            System.out.println("请输入联系人名字: ");
                            String name = userInput.nextLine();
                            modifyJob(name, userInput);
                        }
                    }
                    case 5 -> {
                        return;
                    }
                }
            }
        }
    }

    private static boolean isNum(String str) {
        return str.matches("[0-9]+");
    }

    private static void checkJob() {
        db.printJobs();
    }

    private static void insertJob(Scanner input) {
        Job job = Job.builder()
                .setId(() -> db.getLastJobId() + 1)
                .setName(() -> {
                    System.out.print("岗位名称: ");
                    return input.nextLine();
                })
                .setCompany(() -> {
                    System.out.print("单位名称: ");
                    return input.nextLine();
                })
                .setSalary(() -> {
                    while (true) {
                        System.out.print("薪资数量: ");
                        String str = input.nextLine();
                        if (isNum(str)) {
                            return parseInt(str);
                        } else {
                            System.out.println("输入违法, 请重新输入");
                        }
                    }
                })
                .setWorkTime(() -> {
                    System.out.print("工作时间: ");
                    return input.nextLine();
                })
                .setContact(() -> {
                    System.out.print("联系人电话: ");
                    return input.nextLine();
                })
                .build();
        db.insertJob(job);
    }

    private static void deleteJob(int id) {
        boolean isRemoved = db.deleteJob(id);

        if (isRemoved) {
            System.out.println("id为" + id + "的岗位删除成功");
        } else {
            System.out.println("不存在id为" + id + "的岗位");
        }
    }

    private static void modifyJob(String name, Scanner input) {
        BL.modifyContact();

        job.setName(() -> {
                    System.out.print("岗位名称: ");
                    return input.nextLine();
                })
                .setCompany(() -> {
                    System.out.print("单位名称: ");
                    return input.nextLine();
                })
                .setSalary(() -> {
                    while (true) {
                        System.out.print("薪资数量: ");
                        String str = input.nextLine();
                        if (isNum(str)) {
                            return parseInt(str);
                        } else {
                            System.out.println("输入违法, 请重新输入");
                        }
                    }
                })
                .setWorkTime(() -> {
                    System.out.print("工作时间: ");
                    return input.nextLine();
                })
                .setContact(() -> {
                    System.out.print("联系人电话: ");
                    return input.nextLine();
                });
    }
}