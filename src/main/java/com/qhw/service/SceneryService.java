package com.qhw.service;

import com.qhw.pojo.Scenery;

import java.util.List;

/**
 * Created by asus on 2020/5/5  10:00
 */
public interface SceneryService {

    //1.查询所有
    List<Scenery> findAll();

    //2.根据主键查询单个对象。
    Scenery getOne(Integer id);

    Scenery findSceneryBySceneryname(String username);

    //4.添加单个对象
    void save(Scenery obj);

    //5.修改单个对象
    void update(Scenery obj);

    //6.根据主键删除单个对象。
    void deleteById(Integer id);

}
