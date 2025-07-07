package org.example.mysteryboxlearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan({"org.example.mysteryboxlearn.**.mapper"})
@ServletComponentScan
public class MysteryBoxLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysteryBoxLearnApplication.class, args);
    }
}
