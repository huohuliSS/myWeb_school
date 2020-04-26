package com.qhw.common;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileWriter;
import java.util.UUID;

/**
 * Created by asus on 2020/3/18  16:12
 */
public class ThymeleafUtils {

    private static ClassLoaderTemplateResolver resolver;
    private static TemplateEngine templateEngine;

    public static ClassLoaderTemplateResolver getTemplateResolver(){
        ////        创建模板加载器
//        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
//        resolver.setPrefix("templates/temp/"); //        设置文件的所在目录
//        resolver.setSuffix(".html");           //   模板文件的后缀
////        创建模板引擎
//        TemplateEngine templateEngine = new TemplateEngine();
////        将加载器放入模板引擎
//        templateEngine.setTemplateResolver(resolver);

        //        创建模板加载器
        resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/temp/"); //        设置文件的所在目录
        resolver.setSuffix(".html");           //   模板文件的后缀
        return resolver;
    }


    public static TemplateEngine getTemplateEngine(){
        ClassLoaderTemplateResolver resolver = getTemplateResolver();
        templateEngine = new TemplateEngine();
//        将加载器放入模板引擎
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

}
