package com.qhw.service.impl;

import com.qhw.dao.FriendLinkRepository;
import com.qhw.pojo.FriendLink;
import com.qhw.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asus on 2020/4/30  9:42
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkRepository friendLinkRepository;

    @Override
    public List<FriendLink> findAll() {
        return friendLinkRepository.findAll();
    }

    @Override
    public FriendLink getOne(Integer id) {
        return friendLinkRepository.findFriendLinkById(id);
    }

    @Override
    public void updateStatus(FriendLink friendLink) {
        FriendLink one = getOne(friendLink.getId());
        one.setStatus(friendLink.getStatus());
        save(one);
    }

    @Override
    public void save(FriendLink obj) {
        friendLinkRepository.save(obj);
    }

    @Override
    public void update(FriendLink obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
