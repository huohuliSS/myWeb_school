package com.qhw.common;

import lombok.Data;

/**
 * Created by asus on 2020/4/29  15:14
 */
@Data
public class VerifyCode {
    private String code;

    private byte[] imgBytes;

    private long expireTime;
}
