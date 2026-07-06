package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapperXML {
    /**
     * 查询所有用户
     */
    public List<User> findAll();
}
