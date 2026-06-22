package com.itheima.mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据访问对象（DAO - Data Access Object）
 *
 * DAO 模式：把数据库操作封装到单独的类中，
 *           上层代码只调用方法，不关心具体 SQL。
 */
public class UserDao {

    /** 新增用户，返回受影响行数 */
    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO tb_user(name, age, gender) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getGender());

            return ps.executeUpdate();
        }
    }

    /** 根据 id 删除用户 */
    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM tb_user WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    /** 修改用户信息 */
    public int update(User user) throws SQLException {
        String sql = "UPDATE tb_user SET name = ?, age = ?, gender = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getGender());
            ps.setInt(4, user.getId());

            return ps.executeUpdate();
        }
    }

    /** 根据 id 查询用户 */
    public User findById(int id) throws SQLException {
        String sql = "SELECT id, name, age, gender FROM tb_user WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    /** 查询所有用户 */
    public List<User> findAll() throws SQLException {
        String sql = "SELECT id, name, age, gender FROM tb_user";
        List<User> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    /** 把 ResultSet 当前行映射为 User 对象 */
    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender")
        );
    }
}
