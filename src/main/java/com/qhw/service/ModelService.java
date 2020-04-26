package com.qhw.service;

import com.qhw.pojo.Model;

import java.util.List;

/**
 * Created by asus on 2020/3/8  12:31
 */
public interface ModelService {

    List<Model> findAll();

    //2.根据主键查询单个对象。
    Model getOne(Integer id);

    void updateStatus(Model model);

    List<Model> findByModelNames(String message);

    //3.查询分页记录。
//    Page<Model> findAll(Pageable pageable);

    //4.添加单个对象
    void save(Model obj);

    //5.修改单个对象
    void update(Model obj);

    //6.根据主键删除单个对象。
    void deleteById(Integer id);

}
