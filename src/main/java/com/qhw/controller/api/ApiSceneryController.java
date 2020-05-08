package com.qhw.controller.api;

import com.qhw.common.Result;
import com.qhw.pojo.Scenery;
import com.qhw.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asus on 2020/5/5  10:02
 */
@RestController
@RequestMapping("/api/scenery")
public class ApiSceneryController {

    @Autowired
    private SceneryService sceneryService;

    @RequestMapping("findAll")
    public Result<Scenery> findAll(){
        List<Scenery> sceneries = sceneryService.findAll();
        if (sceneries != null)
            return new Result<>(true, "查询校园图片成功", sceneries);
        return new Result<>(false,"查询校园图片失败");
    }

}
