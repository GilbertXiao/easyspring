package com.gilxyj.easyspring.test.v1;

import com.gilxyj.easyspring.core.io.ClassPathResource;

import com.gilxyj.easyspring.core.io.FileSystemResource;
import com.gilxyj.easyspring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: easyspring
 * @description:
 * @作者 飞码录
 * @微信公众号 飞码录
 * @网站 http://www.codesboy.cn
 * @国际站 http://www.codesboy.com
 * @微信 gilbertxy
 * @GitHub https://github.com/GilbertXiao
 * @Gitee https://gitee.com/gilbertxiao
 * @create: 2020-08-23 19:08
 **/
public class ResourceTest {

    @Test
    public void testClassPathResource(){
        Resource r = new ClassPathResource("petstore-v1.xml");
        try (InputStream is = r.getInputStream()) {
            // 注意：这个测试其实并不充分！！
            Assert.assertNotNull(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFileSystemResource(){
        Resource r = new FileSystemResource("src\\test\\resources\\petstore-v1.xml");
        try (InputStream is = r.getInputStream()) {
            // 注意：这个测试其实并不充分！！
            Assert.assertNotNull(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
