package com.hanlin.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.hanlin.oa.mapper")
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.hanlin.oa.mapper")
public class OaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OaCoreApplication.class, args);
	}

}
