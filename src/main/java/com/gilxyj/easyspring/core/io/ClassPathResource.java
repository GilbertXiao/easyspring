package com.gilxyj.easyspring.core.io;


import com.gilxyj.easyspring.util.ClassUtils;

import java.io.FileNotFoundException;
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
 * @create: 2020-08-23 19:13
 **/
public class ClassPathResource implements Resource {

    private String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        if (classLoader == null) {
            this.classLoader = ClassUtils.getDefaultClassLoader();
            return;
        }
        this.classLoader = classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.classLoader.getResourceAsStream(this.path);
        if (inputStream == null) {
            throw new FileNotFoundException(path + " cannot be opened");
        }
        return inputStream;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
