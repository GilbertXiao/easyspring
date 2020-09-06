package com.gilxyj.easyspring.core.io.support;

import com.gilxyj.easyspring.core.io.FileSystemResource;
import com.gilxyj.easyspring.core.io.Resource;
import com.gilxyj.easyspring.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

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
 * @create: 2020-08-31 23:27
 **/
public class PackageResourceLoader {

    private static final Logger LOGGER= LoggerFactory.getLogger(PackageResourceLoader.class);

    private final ClassLoader classLoader;

    public PackageResourceLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public Resource[] getResources(String basePackage) {
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(location);
        File rootDir = new File(url.getFile());
        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] resources = new Resource[matchingFiles.size()];
        int i = 0;
        for (File matchingFile : matchingFiles) {
            resources[i++] = new FileSystemResource(matchingFile);
        }
        return resources;
    }

    private Set<File> retrieveMatchingFiles(File rootDir){
        if (!rootDir.exists()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exist");

            }
            return Collections.emptySet();
        }

        if (!rootDir.isDirectory()) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
            }
            return Collections.emptySet();
        }

        if (!rootDir.canRead()) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                        "] because the application is not allowed to read the directory");
            }
            return Collections.emptySet();
        }

        Set<File> result = new LinkedHashSet<>();
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    private void doRetrieveMatchingFiles(File dir, Set<File> result) {
        File[] files = dir.listFiles();
        if (files == null) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            }
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                if (!file.canRead()) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Skipping subdirectory [" + dir.getAbsolutePath() +
                                "] because the application is not allowed to read the directory");
                    }
                }
                else {
                    doRetrieveMatchingFiles(file, result);
                }
            }else{
                result.add(file);
            }
        }
    }
}
