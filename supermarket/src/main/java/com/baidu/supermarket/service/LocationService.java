package com.baidu.supermarket.service;

import com.baidu.supermarket.pojo.Location;
import com.baidu.supermarket.util.PageResult;

public interface LocationService {
    //添加收货地址
    public int addAddress(Location location);

    //添加地址信息，并返回地址主键
    public int addLocation(Location location);

    //根据订单主键查询收货地址信息
    public Location getAddressByOid(Integer oid);

    //根据aid查询收货地址信息
    public Location getAddressByAid(Integer aid);

    //根据主键修改指定的用户地址信息
    public int updateLocation(Location location);

    //查询所有的用户地址信息
    public PageResult<Location> getAlllocation(String realName,Integer currentPage,Integer pageSize);

    //根据uid查询对应的用户地址信息
    public PageResult<Location> getAlllocationByUid(Integer uid, Integer currentPage, Integer pageSize);

    //根据主键aid删除指定的用户地址信息
    public int deleteLocationByAid(int aid);

    //根据主键aid批量删除用户地址信息
    public int batchDelLocation(Integer ids[]);

}
