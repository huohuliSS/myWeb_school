package com.qhw.controller;

import com.qhw.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by asus on 2020/3/9  19:08
 */
@RestController
@RequestMapping("/api/ueditor")
public class UeditorController {

    @Value("${FILE_SERVER_URL}")
    public String FILE_SERVER_URL;

    /**
     * 获取Ueditor的配置文件
     * @return
     */
    @RequestMapping("/config")
    public String getConfig() {
        return "{\n" +
                "        \"imageActionName\": \"uploadimage\",\n" +
                "            \"imageFieldName\": \"file\",\n" +
                "            \"imageMaxSize\": 4096000,\n" +
                "            \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "        \"imageCompressEnable\": true,\n" +
                "            \"imageCompressBorder\": 1600,\n" +
                "            \"imageInsertAlign\": \"none\",\n" +
                "            \"imageUrlPrefix\": \""+FILE_SERVER_URL+"\",\n" +
                "            \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "\n" +
                "        /* 上传文件配置 */\n" +
                "        \"fileActionName\": \"uploadfile\",\n" +
                "            \"fileFieldName\": \"file\",\n" +
                "            \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "            \"fileUrlPrefix\": \""+ FILE_SERVER_URL +"\",\n" +
                "            \"fileMaxSize\": 51200000,\n" +
                "            \"fileAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "                \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "                \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "                \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "                \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"]\n" +
                "    }";
    }


    /**
     * ueditor 设置上传图片
     */
    @RequestMapping("/uploadimage")
    @ResponseBody
    public String uploadImage(@RequestParam(name = "file") MultipartFile file) {
        String result = "";
        if(!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            // 这里写你的文件上传逻辑
            // String imgPath = fileUtil.uploadImg(file);
            String extName = filename.substring(filename.lastIndexOf(".") + 1);
            try {
                //2、创建一个 FastDFS 的客户端
                FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
//            3.执行上传处理
                String path = fastDFSClient.uploadFile(file.getBytes(), extName);
                //4、拼接返回的 url 和 ip 地址，拼装成完整的 url
                result = "{\n" +
                        "    \"state\": \"SUCCESS\",\n" +
                        "    \"url\": \"" + path + "\",\n" +
                        "    \"title\": \"" + filename + "\",\n" +
                        "    \"original\": \"" + filename + "\"\n" +
                        "}";
                String url = FILE_SERVER_URL + path;
                System.out.println(url + "-----上传成功");
            }catch (Exception e) {
                e.printStackTrace();
                System.err.println("ueditor上传失败");
            }
        }
        return result;
    }

}
