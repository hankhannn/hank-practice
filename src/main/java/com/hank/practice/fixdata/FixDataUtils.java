package com.hank.practice.fixdata;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author ：ranking.han
 * @date ：Created in 2023/3/21 9:13
 * @description：
 * @version: 1.0
 */
public class FixDataUtils {

    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\personal documents\\fix\\zhongxin_fixdata1.txt"));
        String s = bufferedReader.readLine();
        while (null != s) {
            String[] syncFilmOrderS = s.split("sync order ");
            HttpRequest httpRequest = HttpUtil.createPost("");
            HttpResponse execute = httpRequest.body(syncFilmOrderS[1]).execute();
            System.out.println("Post: " + syncFilmOrderS[1]);
            System.out.println(execute.body());
            s = bufferedReader.readLine();
            Thread.sleep(200);
        }
    }
}
