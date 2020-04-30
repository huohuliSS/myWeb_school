package com.qhw.controller.api;

import com.qhw.common.Result;
import com.qhw.common.ResultT;
import com.qhw.pojo.Context;
import com.qhw.service.ContextService;
import com.qhw.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by asus on 2020/3/8  15:39
 */
@RestController
@RequestMapping("/api/context")
public class ApiContextController {

    @Autowired
    private ContextService contextService;

    @Value("${FILE_SERVER_URL}")
    public String FILE_SERVER_URL;

    @RequestMapping("/findAllByModelIdAndPage")
    public ResultT<Context> findAllByModelId(Context context,
                                             @RequestParam("pageStart") Integer pageStart,
                                             @RequestParam("pageSize") Integer pageSize) {
        if (pageStart == null) { pageStart = 0; }
        if (pageSize == null) { pageSize = 5; }
        int pageNum = pageStart / pageSize;
        Page<Context> contextList = contextService.findAllByModelIdAndPage(context, pageNum, pageSize);
        ResultT<Context> resultT = new ResultT<>(pageStart, pageSize);
        if (contextList != null) {
            resultT.setStatus(true);
            resultT.setList(contextList.getContent());  // 数据
            resultT.setTotalPage(contextList.getTotalPages());  // 总页数
            resultT.setCurrentIndex(pageStart); // 当前开始索引
            resultT.setAllElementNum((int) contextList.getTotalElements());
            resultT.setMsg("查询文章成功");
        } else {
            resultT.setStatus(false);
            resultT.setMsg("查询文章失败");
        }
        return resultT;
    }

    @RequestMapping("/findAllByModelId")
    public Result<Context> findAllByModelId(@RequestParam("modelId") Integer modelId) {
        List<Context> contextList = contextService.findContextByModel(modelId);
        if (contextList != null) {
            return new Result<>(true, "查找文章成功", contextList);
        } else {
            return new Result<>(false, "查找文章失败");
        }
    }

    @RequestMapping("/findOneById/{id}")
    public Result<Context> findOneById(@PathVariable("id") Integer id) {
        Context context = contextService.getOne(id);
        if (context != null) {
            return new Result<>(true, "根据id查询成功", context);
        } else {
            return new Result<>(false, "根据id查询失败");
        }
    }

    @RequestMapping("/createHtmlAndSave")
    public Result createHtmlAndSave(Context context) {
        try {
            contextService.createHtmlAndSave(context);
            return new Result(true, "添加文章成功!");
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "添加文章失败!");
        }
    }

    @RequestMapping("/updateHtmlAndSave")
    public Result updateHtmlAndSave(Context context) {
        if (context.getId() == null) {
            return new Result(false, "当前文章id为null");
        }
        try {
            contextService.updateHtmlAndSave(context);
            return new Result(true, "修改文章成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改文章失败!");
        }
    }

    @RequestMapping("/updateFlag")
    public Result updateFlag(Context context) {
        if (context.getId() == null) {
            return new Result(false, "获取当前context的id失败");
        }
        Context contextT = contextService.getOne(context.getId());
        if (contextT != null) {
            contextT.setFlag(context.getFlag());
            contextService.save(contextT);
            return new Result(true, "操作成功!");
        } else {
            return new Result(false, "操作失败!");
        }
    }

    @RequestMapping("/addViewCount")
    public Result addViewCount(Context context){
        try {
            contextService.updateViewCount(context);
            return new Result(true,"浏览文章数+1");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改viewcount失败");
        }
    }


    @RequestMapping("/downloadAttactment")
    public ResponseEntity<byte[]> downloadAttactment(@RequestParam("sourceUrl") String sourceUrl,
                                                     @RequestParam("title") String title,
                                                     HttpServletRequest request) {
        try {
            String path = sourceUrl.substring(FILE_SERVER_URL.length());
//            String filename = new String(sourceUrl.substring(sourceUrl.lastIndexOf("/") + 1).getBytes(), "iso-8859-1");
            String filename = new String(title.getBytes(), "iso-8859-1");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment", filename);

            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");

            byte[] bytes = fastDFSClient.downLoad(path);
            return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
