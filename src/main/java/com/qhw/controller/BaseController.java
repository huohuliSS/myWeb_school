package com.qhw.controller;

import com.qhw.util.FastDFSClient;
import com.qhw.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by asus on 2020/3/7  20:10
 */
@Controller
public class BaseController {

    @Value("${FILE_SERVER_URL}")
    public String FILE_SERVER_URL;

    @RequestMapping("/danye-list")
    public String danyelist(){
        return "danye-list";
    }

    @RequestMapping("/danye-detail")
    public String danyedetail(){
        return "danye-detail";
    }

    @RequestMapping("/danye-update")
    public String danyeUpdate(){
        return "danye-update";
    }

    @RequestMapping("/article-list")
    public String articlelist(){
        return "article-list";
    }

    @RequestMapping("/article-add")
    public String articleadd(){
        return "article-add";
    }

    @RequestMapping("/lunbo")
    public String lunbo(){
        return "lunbo";
    }

    @RequestMapping("/menu2")
    public String menu2(){
        return "menu2";
    }

    @RequestMapping("/menu-add2")
    public String menuAdd2(){
        return "menu-add2";
    }

    @RequestMapping("/admin-info")
    public String adminInfo(){
        return "admin-info";
    }

    @RequestMapping("/email")
    public String email(){
        return "email";
    }

    @RequestMapping("/email-write")
    public String emailWrite(){
        return "email-write";
    }

    @RequestMapping("/system")
    public String system(){
        return "system";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Result uploadFile(MultipartFile file) {
//        1.获取文件名
        String filename = file.getOriginalFilename();
//        2.获取文件的扩展名
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            //2、创建一个 FastDFS 的客户端
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
//            3.执行上传处理
            String path = fastDFSClient.uploadFile(file.getBytes(), extName);
            //4、拼接返回的 url 和 ip 地址，拼装成完整的 url
            String url = FILE_SERVER_URL + path;
            System.out.println(url + "-----上传成功");
            return new Result(true, url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "上传失败");
        }
    }


}
