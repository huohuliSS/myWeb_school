package com.qhw.service;

import com.qhw.pojo.Context;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

/**
 * Created by asus on 2020/3/8  15:33
 */
public interface ContextService {
    //1.查询所有
    List<Context> findAll();

//    根据模块类型选择文章
    List<Context> findContextByModel(Integer modelId);

    Page<Context> findAllByModelIdAndPage(Context context, Integer curr, Integer limit);

    //2.根据主键查询单个对象。
    Context getOne(Integer id);

    void updateViewCount(Context context);

    void createHtmlAndSave(Context context) throws IOException;

    void updateHtmlAndSave(Context context);

    //4.添加单个对象
    void save(Context obj);

    //5.修改单个对象
    void update(Context obj);

    //6.根据主键删除单个对象。
    void deleteById(Integer id);
}
