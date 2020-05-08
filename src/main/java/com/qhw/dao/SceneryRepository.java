package com.qhw.dao;

import com.qhw.pojo.Scenery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by asus on 2020/5/5  9:58
 */
public interface SceneryRepository extends JpaRepository<Scenery, Integer>, JpaSpecificationExecutor<Scenery> {

}
