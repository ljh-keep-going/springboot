package com.baidu.supermarket.mapper;

import com.baidu.supermarket.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    //查询所有角色
    @Select("select* from role")
    public List<Role> getAllRoles();

    //分页以及模糊分页查询所有角色
    @Select("select* from role"
            +" where roleName like concat('%',#{roleName},'%')"
            +" limit #{start},#{pageSize}")
    public List<Role> getAllRolesByPage(@Param("roleName") String roleName,
                                        @Param("start") int start, @Param("pageSize") int pageSize);

    //查询总记录数
    @Select("select count(rid) from role where roleName like concat('%',#{roleName},'%')")
    public int count(String roleName);

    //添加角色
    @Insert("insert into role(roleName) values(#{roleName})")
    public int addRole(Role role);

    //批量删除角色
    public int batchDelRole(Integer rids[]);

    //根据主键查询指定的角色
    @Select("select* from role where rid=#{rid}")
    public Role getRoleByRid(int rid);

    //根据主键修改指定角色
    @Update("update role set roleName=#{roleName} where rid=#{rid}")
    public int updateRole(Role role);

    //根据主键删除角色
    @Delete("delete from role where rid=#{rid}")
    public int deleteRoleByRid(int rid);
}
