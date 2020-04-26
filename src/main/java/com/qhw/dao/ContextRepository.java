package com.qhw.dao;

import com.qhw.pojo.Context;
import com.qhw.pojo.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by asus on 2020/3/8  15:32
 */
public interface ContextRepository extends JpaRepository<Context, Integer>, JpaSpecificationExecutor<Context> {

    List<Context> findAllByModelId(Integer modelId);

//    Page<Context> findAllByModelIdAndPage(Pageable pageable);

}
