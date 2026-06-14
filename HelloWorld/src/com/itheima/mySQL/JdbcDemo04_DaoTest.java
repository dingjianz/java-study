package com.itheima.mySQL;

import java.sql.SQLException;
import java.util.List;

/**
 * JDBC 示例 4：使用 DAO 模式完成 CRUD
 *
 * 演示了：
 *  1. 新增 (Create)
 *  2. 查询 (Read)
 *  3. 修改 (Update)
 *  4. 删除 (Delete)
 */
public class JdbcDemo04_DaoTest {

    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();

        // 1. 新增
        System.out.println("===== 新增 =====");
        int rows = dao.insert(new User("钱七", 30, "男"));
        System.out.println("新增成功，受影响行数：" + rows);

        // 2. 查询所有
        System.out.println("\n===== 查询所有 =====");
        List<User> users = dao.findAll();
        users.forEach(System.out::println);

        // 3. 取最后一个用户的 id（刚插入的钱七）
        if (!users.isEmpty()) {
            User last = users.get(users.size() - 1);

            // 4. 根据 id 查询
            System.out.println("\n===== 根据 id 查询 =====");
            User found = dao.findById(last.getId());
            System.out.println("找到用户：" + found);

            // 5. 修改
            System.out.println("\n===== 修改 =====");
            found.setAge(31);
            int updateRows = dao.update(found);
            System.out.println("修改成功，受影响行数：" + updateRows);
            System.out.println("修改后：" + dao.findById(found.getId()));

            // 6. 删除
            System.out.println("\n===== 删除 =====");
            int deleteRows = dao.deleteById(found.getId());
            System.out.println("删除成功，受影响行数：" + deleteRows);
        }

        System.out.println("\n===== 最终列表 =====");
        dao.findAll().forEach(System.out::println);
    }
}
