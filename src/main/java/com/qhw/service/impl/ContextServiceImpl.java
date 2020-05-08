package com.qhw.service.impl;

import com.qhw.dao.UserRepository;
import com.qhw.pojo.User;
import com.qhw.util.ThymeleafUtils;
import com.qhw.dao.ContextRepository;
import com.qhw.pojo.Context;
import com.qhw.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by asus on 2020/3/8  15:34
 */
@Service
public class ContextServiceImpl implements ContextService {

    @Value("${CONTEXT_OUT_DIR}")
    private String CONTEXT_OUT_DIR;

    @Autowired
    private ContextRepository contextRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Context> findAll() {
        return contextRepository.findAll();
    }

    @Override
    public List<Context> findContextByModel(Integer modelId) {
        return contextRepository.findAllByModelId(modelId);
    }

    @Override
    public Page<Context> findAllByModelIdAndPage(Context context, Integer pageNum, Integer pageSize) {
//        Pageable pageable = new PageRequest(pageNum, pageSize, new Sort(Sort.Direction.ASC, "id"));
        String sortName = context.getViewCount() == null? "createTime" : "viewCount";
        Pageable pageable = PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, sortName));
        Specification<Context> spec = new Specification<Context>() {
            @Override
            public Predicate toPredicate(Root<Context> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (context != null && context.getModelId() != null) {
//                    根据modelId条件查询
                    list.add(criteriaBuilder.equal(root.get("modelId").as(Integer.class), context.getModelId()));
                }
                if (context != null && context.getTitle() != null) {
//                    根据title模糊查询
                    list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + context.getTitle() + "%"));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return contextRepository.findAll(spec, pageable);
    }

    @Override
    public Context getOne(Integer id) {
        return contextRepository.getOne(id);
    }

    @Override
    public void updateViewCount(Context context) {
        if (context.getId() == null) {
            throw new RuntimeException("获取当前文章的id失败");
        }
        Context one = getOne(context.getId());
        one.setViewCount(one.getViewCount() + 1);
        save(one);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createHtmlAndSave(Context context) throws IOException {
        TemplateEngine templateEngine = ThymeleafUtils.getTemplateEngine();
//        创建字符输出流并且自定义输出文件的位置和文件名
        String newContentHtml = UUID.randomUUID().toString().replaceAll("-", "") + ".html";
//            获取文件存储的路径
        String newHtmlPath = CONTEXT_OUT_DIR + context.getModelId() + "\\" + newContentHtml;
//            判断文件是否存在
        File file = new File(newHtmlPath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(newHtmlPath);
//            创建Context对象(存放Model)
        org.thymeleaf.context.Context thContext = new org.thymeleaf.context.Context();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            放入数据
        thContext.setVariable("content", context.getContent());
        thContext.setVariable("title", context.getTitle());
//        获取当前登录的用户名
        thContext.setVariable("author", "齐宏武");
        thContext.setVariable("createDate",formatter.format(currentTime));

//            创建静态文件，"text"是模板html名字
        templateEngine.process("contextTem", thContext, fileWriter);
//        释放文件流资源
        fileWriter.close();
        System.err.println("创建" + newHtmlPath + "成功");

        context.setCreateTime(currentTime);
        context.setViewCount(0);
//            如果创建静态页面成功
        context.setContextPath(newHtmlPath);
//        获取当前登录的用户id，并设置    ***************************************
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = userRepository.findUserByUsername(username);
        context.setUserId(userByUsername.getId());

        contextRepository.save(context);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHtmlAndSave(Context context) {
        FileWriter fileWriter = null;
        Context repositoryOne = contextRepository.getOne(context.getId());
//        第一步，先删除原来的静态页面
        try {
            String contextPath = repositoryOne.getContextPath();
            File html = new File(contextPath);
            if (html.exists()) {
                html.delete();  // 如果静态页面存在，删除
                System.err.println("删除" + contextPath + "成功");
            }
            TemplateEngine templateEngine = ThymeleafUtils.getTemplateEngine();
            //        创建字符输出流并且自定义输出文件的位置和文件名
            String newContentHtml = UUID.randomUUID().toString().replaceAll("-", "") + ".html";
//            获取文件存储的路径
            String newHtmlPath = CONTEXT_OUT_DIR + context.getModelId() + "\\" + newContentHtml;
//            判断文件是否存在
            File file = new File(newHtmlPath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.createNewFile();
            fileWriter = new FileWriter(newHtmlPath);
//      根据id查询要修改哪一个context
            repositoryOne.setUpdateTime(new Date());  // 设置修改时间
            repositoryOne.setContextPath(newHtmlPath);
            repositoryOne.setContent(context.getContent());
            repositoryOne.setFlag(context.getFlag());
            repositoryOne.setModelId(context.getModelId());
            repositoryOne.setRemark(context.getRemark());
            repositoryOne.setTitle(context.getTitle());
//            获取当前登录用户的id     **************************************
//            repositoryOne.setUserId(2);

//            创建Context对象(存放Model)
            org.thymeleaf.context.Context thContext = new org.thymeleaf.context.Context();
//            放入数据
            thContext.setVariable("content", repositoryOne.getContent());
            thContext.setVariable("title", repositoryOne.getTitle());
//            创建静态文件，"text"是模板html名字
            templateEngine.process("contextTem", thContext, fileWriter);
            System.err.println("修改生成" + newHtmlPath + "成功");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void save(Context obj) {
        contextRepository.save(obj);
    }

    @Override
    public void update(Context obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
