package com.baidu.supermarket.mapper;

import com.baidu.supermarket.pojo.Location;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LocationMapper {

    //添加收货地址
    @Insert("insert into location(realName,phone,identity,address,oid,uid)" +
            " values(#{realName},#{phone},#{identity},#{address},#{oid},#{uid})")
    public int addAddress(Location location);

    //添加地址信息，并返回地址主键
    @Insert("insert into location(realName,phone,identity,address,oid,uid)" +
            " values(#{realName},#{phone},#{identity},#{address},#{oid},#{uid})")
    @Options(useGeneratedKeys = true,keyColumn = "aid",keyProperty = "aid")
    public int addLocation(Location location);

    //根据订单主键查询收货地址信息
    @Select("select* from location where oid=#{oid}")
    public Location getAddressByOid(Integer oid);

    //查询所有的用户地址信息
    public List<Location> getAlllocation(@Param("realName") String realName, @Param("start") Integer start, @Param("pageSize")Integer pageSize);

    //根据uid查询对应的用户地址信息
    public List<Location> getAlllocationByUid(@Param("uid") Integer uid, @Param("start") Integer start, @Param("pageSize")Integer pageSize);

    //查询总记录数
    public int count(String realName);

    //根据uid查询总记录数
    public int count2(Integer uid);

    //根据主键修改指定的用户地址信息
    @Update("update location set realName=#{realName},phone=#{phone},identity=#{identity},address=#{address}"
            +" where aid=#{aid}")
    public int updateLocation(Location location);

    //根据aid查询收货地址信息
    @Select("select* from location where aid=#{aid}")
    public Location getAddressByAid(Integer aid);

    //根据主键aid删除指定的用户地址信息
    @Delete("delete from location where aid=#{aid}")
    public int deleteLocationByAid(int aid);

    //根据主键aid批量删除用户地址信息
    public int batchDelLocation(Integer ids[]);
}
