package com.search;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Title: 
 * Description: 
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SpringBootDemo
 * Author: 王杰
 * Create Time:2019-12-30 13:50
 */
@SpringBootApplication
public class SearchApp {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SearchApp.class).web(true).run(args);
	}
}
