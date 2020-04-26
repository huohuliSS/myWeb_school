package com.qhw.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by asus on 2020/2/26  8:54
 */
@Entity
@Table(name = "context")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Context {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "context_path",nullable = false)
    private String contextPath;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @Column(name = "image")
    private String image;

    //    发布用户
    @Column(name = "user_id",nullable = false)
    private Integer userId;

    //    对应模块
    @Column(name = "model_id",nullable = false)
    private Integer modelId;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    //    是否有效 （1 有效， 0失效）
    @Column(name = "flag", nullable = false, columnDefinition = "int default 0")
    private Integer flag;

    //    观看数量
    @Column(name = "view_count", nullable = false, columnDefinition = "int default 0")
    private Integer viewCount;

//    预留字段
    @Column(name = "remark")
    private String remark;

}
