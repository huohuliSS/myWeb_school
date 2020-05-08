package com.qhw.service.impl;

import com.qhw.dao.SceneryRepository;
import com.qhw.pojo.Scenery;
import com.qhw.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asus on 2020/5/5  10:01
 */
@Service
public class SceneryServiceImpl implements SceneryService {

    @Autowired
    private SceneryRepository sceneryRepository;

    @Override
    public List<Scenery> findAll() {
        return sceneryRepository.findAll();
    }

    @Override
    public Scenery getOne(Integer id) {
        return null;
    }

    @Override
    public Scenery findSceneryBySceneryname(String username) {
        return null;
    }

    @Override
    public void save(Scenery obj) {

    }

    @Override
    public void update(Scenery obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
