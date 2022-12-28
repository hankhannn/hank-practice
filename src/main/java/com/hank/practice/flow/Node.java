/*
 * Copyright 2021-2022 www.jiahui.com
 */
package com.hank.practice.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

/**
 * @author ：hank.han
 * @date ：Created in 2022/12/28 14:11
 * @description：
 * @version: 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Node {

    @JacksonXmlText
    private String node;
}
