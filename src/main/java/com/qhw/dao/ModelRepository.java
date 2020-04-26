package com.qhw.dao;

import com.qhw.pojo.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by asus on 2020/3/8  12:30
 */
public interface ModelRepository extends JpaRepository<Model, Integer>, JpaSpecificationExecutor<Model> {


    Model getModelById(Integer id);

    List<Model> findAllByModelNameContaining(String modelName);

}
