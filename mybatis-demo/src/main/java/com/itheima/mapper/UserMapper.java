package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 应用程序在运行时，会自动为该接口创建一个实现类对象（代理对象）,并且会自动将该实现类对象存入IOC容器中
public interface UserMapper {
    /**
     * 查询所有用户
     */
    @Select("select * from user") // @Update @Delete @Insert 等
    public List<User> findAll();

    /**
     * 查询用户-select
     */
    // 需求：根据用户和密码查询用户信息
    // @Param 注解的作用是为借口的方法形參起名字的。
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据id删除用户
     * @param id 用户id
     */
    @Delete("delete from user where id = #{id} ")
    public void deleteById(Integer id);

    /*
        添加用户
     */

    @Insert("insert into user(username, password, name, age) values (#{username}, #{password}, #{name}, #{age})")
    public void inertUser(User user);

    /**
     * 修改用户 update
     */
    @Update("update user set username = #{username}, password = #{password}, name = #{name}, age = #{age} where id = #{id}")
    public void updateUser(User user);
}
