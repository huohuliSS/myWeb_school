package com.qhw.dao;

import com.qhw.pojo.FriendLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by asus on 2020/4/30  9:40
 */
public interface FriendLinkRepository extends JpaRepository<FriendLink, Integer>, JpaSpecificationExecutor<FriendLink> {
    FriendLink findFriendLinkById(Integer id);
}
