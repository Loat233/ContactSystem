import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class UserSys {
    private static AddressBookList BL;

    public static void main(String[] args) {
        System.out.println("系统初始化...");
        BL = loadFile();
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
            System.out.println("7. 退出系统");
            System.out.println("========================");
            if (userInput.hasNext()) {
                switch (parseInt(userInput.nextLine())) {
                    case 1 -> {
                        System.out.println("请输入联系人名字: ");
                        BL.searchByName(userInput.nextLine());
                    }
                    case 2 -> {
                        while (true) {
                            System.out.println("请输入联系人电话: ");
                            String phoneNum = userInput.nextLine();
                            if (phoneNum.matches("[0-9]+")) {
                                BL.searchByPhone(phoneNum);
                                break;
                            }
                            else {
                                System.out.println("电话号码格式错误");
                            }
                        }
                    }
                    case 3 -> BL.printAll();
                    case 4 -> {
                        if (BL.addContact(
                                () -> {
                                    System.out.println("联系人名字: ");
                                    return userInput.nextLine();
                                },
                                () -> {
                                    while (true) {
                                        System.out.println("电话号码: ");
                                        String phoneNum = userInput.nextLine();
                                        if (phoneNum.matches("[0-9]+")) {
                                            return phoneNum;
                                        }
                                        else {
                                            System.out.println("电话号码格式错误");
                                        }
                                    }
                                },
                                () -> {
                                    System.out.println("地址: ");
                                    return userInput.nextLine();
                                },
                                () -> {
                                    while (true) {
                                        System.out.println("邮箱: ");
                                        String email = userInput.nextLine();
                                        if (email.matches("[0-9|A-z]+@[0-9|A-z]+\\.[0-9|A-z]+")) {
                                            return email;
                                        }
                                        else {
                                            System.out.println("邮箱格式错误");
                                        }
                                    }
                                })) {
                            System.out.println("添加联系人成功");
                            saveFile(BL);
                        }
                        else {
                            System.out.println("添加联系人失败");
                        }
                    }
                    case 5 -> {
                        System.out.println("请输入联系人名字: ");
                        String name = userInput.nextLine();
                        if (BL.deleteContact(name)) {
                            System.out.println("成功删除联系人: " + name);
                            saveFile(BL);
                        }
                        else {
                            System.out.println("未找到联系人: " + name + "，删除失败");
                        }
                    }
                    case 6 -> {
                        BL.modifyContact(
                            () -> {
                                System.out.println("联系人名字: ");
                                return userInput.nextLine();
                            },
                            () -> {
                                while (true) {
                                    System.out.println("电话号码: ");
                                    String phoneNum = userInput.nextLine();
                                    if (phoneNum.matches("[0-9]+")) {
                                        return phoneNum;
                                    }
                                    else {
                                        System.out.println("电话号码格式错误");
                                    }
                                }
                            },
                            () -> {
                                System.out.println("地址: ");
                                return userInput.nextLine();
                            },
                            () -> {
                                while (true) {
                                    System.out.println("邮箱: ");
                                    String email = userInput.nextLine();
                                    if (email.matches("[0-9|A-z]+@[0-9|A-z]+\\.[0-9|A-z]+")) {
                                        return email;
                                    }
                                    else {
                                        System.out.println("邮箱格式错误");
                                    }
                                }
                            });
                        saveFile(BL);
                    }
                    case 7 -> {
                        System.out.println("系统即将关闭，再见");
                        return;
                    }
                }
            }
        }
    }

    public static AddressBookList loadFile() {
        File file = new File("address_book");
        if (!file.exists()) {
            System.out.println("检测到首次运行，本地数据文件不存在, 创建空白通讯录");
            return new AddressBookList();
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("address_book"))) {
            System.out.println("数据文件读取成功");
            return (AddressBookList) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("数据文件损坏, 重置为空白通讯录");
            return new AddressBookList();
        }
    }

    public static void saveFile(AddressBookList bl) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("address_book"))) {
            out.writeObject(bl);
            System.out.println("文件保存成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件保存失败");
        }
    }
}