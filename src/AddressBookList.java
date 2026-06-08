import java.util.function.Supplier;

/**
 * 链表管理类：实现通讯录的各项功能
 */
public class AddressBookList {
    // 使用虚拟头节点，可以极大地方便插入和删除操作，不需要单独处理头节点为空的情况
    private Contact head;

    // (1) 创建一个空白通讯录
    public AddressBookList() {
        head = new Contact("", "", "", "");
    }

    // (2) 实现联系人添加，并按姓名排序插入到正确位置
    public void addContact(String name, String phone, String address, String email) {
        Contact newContact = new Contact(name, phone, address, email);
        Contact current = head;

        // 遍历链表，找到第一个名字字典序大于当前新联系人名字的节点的前一个位置
        // 使用 compareTo 方法进行字符串比较
        while (current.next != null && current.next.name.compareTo(name) < 0) {
            current = current.next;
        }

        // 插入节点
        newContact.next = current.next;
        current.next = newContact;
        System.out.println("成功添加联系人并排序: " + name);
    }

    // (3) 实现删除联系人功能 (按姓名删除)
    public void deleteContact(String name) {
        Contact current = head;

        while (current.next != null) {
            if (current.next.name.equals(name)) {
                current.next = current.next.next; // 跳过要删除的节点
                System.out.println("成功删除联系人: " + name);
                return;
            }
            current = current.next;
        }
        System.out.println("未找到联系人: " + name + "，删除失败。");
    }

    // (4) 实现按姓名快速查找
    public void searchByName(String name) {
        Contact current = head.next;
        while (current != null) {
            if (current.name.equals(name)) {
                System.out.println("查找到联系人(按姓名): " + current);
                return;
            }
            current = current.next;
        }
        System.out.println("未查找到姓名为 " + name + " 的联系人。");
    }

    // (4) 实现按手机号码快速查找
    public void searchByPhone(String phone) {
        Contact current = head.next;
        while (current != null) {
            if (current.phone.equals(phone)) {
                System.out.println("查找到联系人(按手机号): " + current.toString());
                return;
            }
            current = current.next;
        }
        System.out.println("未查找到手机号为 " + phone + " 的联系人。");
    }

    // (5) 修改联系人的基本信息
    public void modifyContact(String name, String newPhone, String newAddress, String newEmail) {
        Contact current = head.next;
        while (current != null) {
            if (current.name.equals(name)) {
                current.phone = newPhone;
                current.address = newAddress;
                current.email = newEmail;
                System.out.println("成功修改联系人信息: " + current.toString());
                return;
            }
            current = current.next;
        }
        System.out.println("未找到联系人: " + name + "，修改失败。");
    }

    public void  modifyContact(Supplier<> )

    // 辅助方法：打印通讯录中所有联系人，方便测试观察排序效果
    public void displayAll() {
        Contact current = head.next;
        if (current == null) {
            System.out.println("通讯录为空。");
            return;
        }
        System.out.println("--- 当前通讯录列表 ---");
        while (current != null) {
            System.out.println(current.toString());
            current = current.next;
        }
        System.out.println("--------------------------");
    }
}
