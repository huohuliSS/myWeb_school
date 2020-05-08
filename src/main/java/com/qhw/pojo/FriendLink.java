package com.qhw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by asus on 2020/4/30  9:31
 */
@Data
@Entity
@Table(name = "friend_link")
@NoArgsConstructor
@AllArgsConstructor
public class FriendLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "link_name")
    private String linkName;

    @Column(name = "url_str")
    private String urlStr;

    @Column(name = "img")
    private String img;

    @Column(name = "status")
    private Integer status;

    @Column(name = "remark")
    private String remark;

}
