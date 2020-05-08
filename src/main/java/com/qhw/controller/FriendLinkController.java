package com.qhw.controller;

import com.qhw.common.Result;
import com.qhw.pojo.FriendLink;
import com.qhw.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asus on 2020/4/30  9:43
 */
@RestController
@RequestMapping("/api/friendLink")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @RequestMapping("findAll")
    public Result<FriendLink> findAll(){
        List<FriendLink> friendLinks = friendLinkService.findAll();
        if (friendLinks != null) {
            return new Result<>(true, "查询友情链接成功",friendLinks);
        }
        return new Result<>(false, "查询友情链接失败");
    }

    @RequestMapping("/findFriendLinkById/{id}")
    public Result<FriendLink> findFriendLinkById(@PathVariable Integer id) {
        FriendLink friendLink = friendLinkService.getOne(id);
        if (friendLink != null) {
            return new Result<>(true, "根据id查询friendlink成功", friendLink);
        }
        return new Result<>(false, "查询失败");
    }

    @RequestMapping("addFriendLink")
    public Result addFriendLink(FriendLink friendLink) {
        try {
            friendLinkService.save(friendLink);
            return new Result(true, "模块保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加模块失败！");
        }
    }

    @RequestMapping("/updateStatus")
    public Result updateStatus(FriendLink friendLink){
        try {
            if (friendLink != null && friendLink.getId() != null) {
                friendLinkService.updateStatus(friendLink);
                return new Result<>(true, "修改状态成功");
            }
            return new Result<>(false, "修改失败,当前id获取为空");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(false, "修改状态失败！");
        }
    }

}
