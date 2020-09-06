package com.gilxyj.easyspring.test.v4;

import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

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
 * @create: 2020-08-31 23:23
 **/
public class PackageResourceLoaderTest {

    @Test
    public void testGetResource(){
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.gilxyj.easyspring.dao.v4");
        Assert.assertEquals(2, resources.length);
    }
}
