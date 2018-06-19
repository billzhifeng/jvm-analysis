
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
public class JVMAppMain {

    public static void main(String[] args) {
        SpringApplication.run(JVMAppMain.class, args);
    }

}
