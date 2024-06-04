package com.baidu.supermarket.service.impl;

import com.baidu.supermarket.mapper.LocationMapper;
import com.baidu.supermarket.pojo.Location;
import com.baidu.supermarket.service.LocationService;
import com.baidu.supermarket.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired(required = false)
    private LocationMapper locationMapper;

    //添加收货地址
    public int addAddress(Location location){
        return locationMapper.addAddress(location);
    }

    //添加地址信息，并返回地址主键
    public int addLocation(Location location){
        return locationMapper.addLocation(location);
    }

    //根据aid查询收货地址信息
    public Location getAddressByAid(Integer aid){
        return locationMapper.getAddressByAid(aid);
    }

    //根据主键修改指定的用户地址信息
    public int updateLocation(Location location){
        return locationMapper.updateLocation(location);
    }

    //根据订单主键查询收货地址信息
    public Location getAddressByOid(Integer oid){
        return locationMapper.getAddressByOid(oid);
    }

    //查询所有的用户地址信息
    public PageResult<Location> getAlllocation(String realName,Integer currentPage,Integer pageSize){
        //查询所有地址信息
        int start=(currentPage-1)*pageSize;
        List<Location> list=locationMapper.getAlllocation(realName,start,pageSize);
        //查询总记录数
        int count=locationMapper.count(realName);
        //封装结果
        PageResult<Location> pageResult=new PageResult<>();

        pageResult.setCode(0);
        pageResult.setMsg("查询成功");
        pageResult.setCount(count);
        pageResult.setData(list);
        return pageResult;
    }

    //根据uid查询对应的用户地址信息
    public PageResult<Location> getAlllocationByUid(Integer uid, Integer currentPage, Integer pageSize){
        //查询所有地址信息
        int start=(currentPage-1)*pageSize;
        List<Location> list=locationMapper.getAlllocationByUid(uid,start,pageSize);

        //查询uid总记录数
        int count=locationMapper.count2(uid);

        //封装结果
        PageResult<Location> pageResult=new PageResult<>();

        pageResult.setCode(0);
        pageResult.setMsg("查询成功");
        pageResult.setCount(count);
        pageResult.setData(list);
        return pageResult;
    }

    //根据主键aid删除指定的用户地址信息
    public int deleteLocationByAid(int aid){
        return locationMapper.deleteLocationByAid(aid);
    }

    //根据主键aid批量删除用户地址信息
    public int batchDelLocation(Integer ids[]){
        return locationMapper.batchDelLocation(ids);
    }

}
