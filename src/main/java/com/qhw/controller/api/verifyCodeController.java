package com.qhw.controller.api;

import com.qhw.common.Result;
import com.qhw.common.VerifyCode;
import com.qhw.dao.IVerifyCodeGen;
import com.qhw.service.impl.SimpleCharVerifyCodeGenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 2020/4/29  15:21
 */
@Controller
@RequestMapping("/api")
public class verifyCodeController {

    @Autowired
    private IVerifyCodeGen iVerifyCodeGen;

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
//        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
//            LOGGER.info(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
//            LOGGER.info("", e);
        }
    }

    @RequestMapping("/VerifyCodeIsEquals")
    @ResponseBody
    public Result VerifyCodeIsEquals(String code, HttpServletRequest request){
        String verifyCode = (String) request.getSession().getAttribute("VerifyCode");
        System.out.println(code.toUpperCase());
        if (code != null && code.equalsIgnoreCase(verifyCode)) {
            return new Result(true, "验证码正确");
        }
        return new Result(false, "验证码错误");
    }

}
