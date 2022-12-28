/*
 * Copyright 2021-2022 www.jiahui.com
 */
package com.hank.practice.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import lombok.Data;

/**
 * @author ：hank.han
 * @date ：Created in 2022/12/28 14:11
 * @description：
 * @version: 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chain {

    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Node> node;
    @JacksonXmlProperty(isAttribute = true)
    private String contextClass;
}
