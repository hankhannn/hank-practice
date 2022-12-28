package com.hank.practice.cmdline;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.hank.practice.flow.Chain;
import com.hank.practice.flow.Flow;
import com.hank.practice.flow.FlowParser;
import graphql.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：hank.han
 * @date ：Created in 2022/12/28 14:11
 * @description：
 * @version: 1.0
 */
@Component
public class ReadXmlCommandLine implements CommandLineRunner {

    @Value("${flow.xml.paths:classpath:/flow/*.xml}")
    private String flowPaths;

    @Override
    public void run(String... args) throws Exception {
        String[] split = flowPaths.split(",");
        FlowParser flowParser = new FlowParser();
        Resource[] resources = flowParser.matchRuleResources(Lists.newArrayList(split));
        for (Resource resource : resources) {
            XmlMapper mapper = new XmlMapper();
            Flow flow = mapper.readValue(resource.getInputStream(), Flow.class);
            List<Chain> flowChain = flow.getChain();

        }
    }
}
