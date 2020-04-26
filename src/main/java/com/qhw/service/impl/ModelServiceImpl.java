package com.qhw.service.impl;

import com.qhw.dao.ModelRepository;
import com.qhw.pojo.Model;
import com.qhw.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by asus on 2020/3/8  12:35
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> findAll() {
        return modelRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @Override
    public Model getOne(Integer id) {
        return modelRepository.getModelById(id);
    }

    @Override
    public void updateStatus(Model model) {
        Model modelt = getOne(model.getId());
        modelt.setStatus(model.getStatus());
        modelRepository.save(modelt);
    }

    @Override
    public List<Model> findByModelNames(String message) {
        return modelRepository.findAllByModelNameContaining(message);
    }

    @Override
    public void save(Model obj) {
        modelRepository.save(obj);
    }

    @Override
    public void update(Model obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
