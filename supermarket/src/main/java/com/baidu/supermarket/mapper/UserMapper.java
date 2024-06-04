package com.baidu.supermarket.mapper;

import com.baidu.supermarket.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //用户登录
    @Select("select uid,name,password,telephone,createTime,role_id as roleId from user where name=#{name} and password=#{password} and role_id=#{roleId}")
    public User login(User user);

    //购买时用户登录
    @Select("select uid,name,password,telephone,createTime,role_id as roleId from user where name=#{name} and password=#{password}")
    public User userLogin(User user);

    //查询所有用户信息以及用户角色
    public List<User> getAllUser(@Param(value="name") String name,@Param(value = "start") int start,@Param(value = "pageSize") int pageSize);

    //查询总记录数
    public int count(String name);

    //根据主键批量删除用户信息
    public int batchDelUser(Integer ids[]);

    //添加用户信息
    @Insert("insert into user(name,password,telephone,createTime,role_id)" +
            " values(#{name},#{password},#{telephone},#{createTime},#{roleId})")
    public int addUser(User user);

    //根据id查询指定的用户信息
    @Select("select uid,name,password,telephone,createTime,role_id as roleId from user where uid=#{uid}")
    public User getUserById(int uid);


    //根据主键修改指定的用户信息
    @Update("update user set name=#{name},password=#{password},telephone=#{telephone},createTime=#{createTime},role_id=#{roleId}"
    +" where uid=#{uid}")
    public int updateUser(User user);

    //根据主键删除指定的用户信息
    @Delete("delete from user where uid=#{uid}")
    public int deleteUserByUid(int uid);
}
