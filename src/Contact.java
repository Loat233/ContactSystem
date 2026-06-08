/**
 * 节点类：表示通讯录中的每一个联系人
 */
class Contact {
    String name;
    String phone;
    String address;
    String email;
    Contact next; // 指向下一个联系人的指针

    public Contact(String name, String phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.next = null;
    }

    @Override
    public String toString() {
        return "姓名: " + name + "\t手机: " + phone + "\t地址: " + address + "\t邮箱: " + email;
    }
}