package com.qhw.service;

import com.qhw.pojo.FriendLink;

import java.util.List;

/**
 * Created by asus on 2020/4/30  9:41
 */
public interface FriendLinkService {

    //1.查询所有
    List<FriendLink> findAll();

    //2.根据主键查询单个对象。
    FriendLink getOne(Integer id);

    void updateStatus(FriendLink friendLink);

    //4.添加单个对象
    void save(FriendLink obj);

    //5.修改单个对象
    void update(FriendLink obj);

    //6.根据主键删除单个对象。
    void deleteById(Integer id);
}
