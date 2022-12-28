/*
 * Copyright 2021-2022 www.jiahui.com
 */
package com.hank.practice.flow;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ：hank.han
 * @date ：Created in 2022/12/28 14:11
 * @description：
 * @version: 1.0
 */
public class FlowParser {

    /**
     * 根据配置的ruleSource查找匹配的资源
     */
    public Resource[] matchRuleResources(final List<String> pathList) throws IOException {
        Assert.notEmpty(pathList, "rule source must not be null");

        List<Resource> allResource = new ArrayList<>();
        for (String path : pathList) {
            String locationPattern;

            //如果path是绝对路径且这个文件存在时，我们认为这是一个本地文件路径，而并非classpath路径
            if (FileUtil.isAbsolutePath(path) && FileUtil.isFile(path)) {
                locationPattern = ResourceUtils.FILE_URL_PREFIX + path;
            } else {
                if (!path.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)) {
                    locationPattern = ResourceUtils.CLASSPATH_URL_PREFIX + path;
                } else {
                    locationPattern = path;
                }
            }

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources(locationPattern);
            allResource.addAll(ListUtil.toList(resources));
        }

        //如果有多个资源，检查资源都是同一个类型，如果出现不同类型的配置，则抛出错误提示
        Set<String> fileTypeSet = new HashSet<>();
        allResource.forEach(resource -> fileTypeSet.add(FileUtil.extName(resource.getFilename())));
        return allResource.toArray(new Resource[]{});
    }
}
