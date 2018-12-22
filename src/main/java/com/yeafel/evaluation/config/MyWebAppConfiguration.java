package com.yeafel.evaluation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by kangyifan on 2018/9/21 2:13
 */
@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
         *这是图片的物理路径  "file:/+本地图片的地址"
         * @Date： Create in 14:08 2017/12/20
         */     registry.addResourceHandler("/**").addResourceLocations("file:/D:/ideaWorkspace/evaluation/src/main/resources/static/");
        super.addResourceHandlers(registry);
    }
}