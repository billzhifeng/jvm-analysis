
package com.github.scaffold;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 * 
 * @author wangzhifeng
 * @EnableDubboConfiguration
 */
@SpringBootApplication
@MapperScan({ "com.github.scaffold.dal" })
public class MiniAppMain {

    public static void main(String[] args) {
        SpringApplication.run(MiniAppMain.class, args);
    }

}
