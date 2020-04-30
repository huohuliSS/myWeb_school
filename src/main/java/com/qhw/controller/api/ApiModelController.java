package com.qhw.controller.api;

import com.qhw.common.Result;
import com.qhw.pojo.Model;
import com.qhw.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asus on 2020/3/8  10:06
 */
@RestController
@RequestMapping("/api/model")
public class ApiModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping("findAllModel")
    public Result<Model> findAllModel() {
        List<Model> models = modelService.findAll();
        if (models != null) {
            return new Result<>(true, "查询modelList成功", models);
        }
        return new Result<>(false, "查询信息不存在");

    }

    @RequestMapping("addModel")
    public Result addModel(Model model) {
        try {
            modelService.save(model);
            return new Result(true, "模块保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加模块失败!");
        }
    }

    @RequestMapping("/findModelById/{id}")
    public Result<Model> findModelById(@PathVariable Integer id) {
        Model model = modelService.getOne(id);
        if (model != null) {
            return new Result<>(true, "根据id查询model成功!", model);
        }
        return new Result<>(false, "查询失败");

    }

    @RequestMapping("/findByModelNames")
    public Result<Model> findByModelNames(String message) {
        List<Model> models = modelService.findByModelNames(message);
        if (models != null) {
            return new Result<>(true, "查询成功", models);
        }
        return new Result<>(false, "查询失败");

    }


    @RequestMapping("/updateStatus")
    public Result<Model> updateStatus(Model model) {
        try {
            if (model != null && model.getId() != null) {
                modelService.updateStatus(model);
                return new Result<>(true, "修改状态成功!");
            } else {
                return new Result<>(false, "修改失败,当前id获取为空!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(false, "修改状态失败!");
        }
    }


}
