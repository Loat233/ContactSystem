/**
 * 主程序类：用于测试各项功能是否符合基本要求
 */
void main() {
    // 1. 创建空白通讯录
    AddressBookList myBook = new AddressBookList();

    // 2. 添加联系人 (测试乱序输入，验证是否会自动按姓名排序)
    myBook.addContact("Charlie", "13800000003", "广州市", "charlie@email.com");
    myBook.addContact("Alice", "13800000001", "北京市", "alice@email.com");
    myBook.addContact("Bob", "13800000002", "上海市", "bob@email.com");

    myBook.displayAll(); // 打印出来应该会按 Alice, Bob, Charlie 的顺序排列

    // 3. 按姓名、手机号码查找
    myBook.searchByName("Bob");
    myBook.searchByPhone("13800000001");

    // 4. 修改联系人信息
    myBook.modifyContact("Bob", "19999999999", "深圳市", "bob_new@email.com");

    // 5. 删除联系人
    myBook.deleteContact("Charlie");

    // 最终展示
    myBook.displayAll();
}
