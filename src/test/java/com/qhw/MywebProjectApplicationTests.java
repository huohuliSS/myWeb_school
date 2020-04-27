package com.qhw;

import com.qhw.util.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MywebProjectApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(MD5Utils.code("root"));
    }

}
