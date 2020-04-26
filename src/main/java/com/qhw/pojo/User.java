package com.qhw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by asus on 2020/2/26  9:57
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;  // 用户名

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;  // 姓名

    @Column(name = "phone")
    private String phone;

    @Column(name = "remark")
    private String remark;


}
