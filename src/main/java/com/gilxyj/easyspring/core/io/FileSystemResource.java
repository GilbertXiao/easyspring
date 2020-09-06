package com.gilxyj.easyspring.core.io;



import com.gilxyj.easyspring.util.Assert;

import java.io.File;
import java.io.FileInputStream;
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
 * @create: 2020-08-23 19:15
 **/
public class FileSystemResource implements Resource {

    private String path;
    private File file;

    public FileSystemResource(String path) {
        Assert.notNull(path,"path cannot be null");
        this.path = path;
        this.file = new File(path);
    }

    public FileSystemResource(File matchingFile) {
        this.file = matchingFile;
        this.path = matchingFile.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file["+file.getAbsolutePath()+"]";
    }
}
