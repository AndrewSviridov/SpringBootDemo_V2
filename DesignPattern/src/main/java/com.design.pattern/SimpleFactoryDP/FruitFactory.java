package com.design.pattern.SimpleFactoryDP;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SpringBootDemo
 * Author: 王杰
 * Create Time:2019/5/9 15:10
 */
public class FruitFactory {

    public static Fruit produceFruit(String fruit) {
        switch (fruit) {
            case "apple":
                return new Apple();
            case "orange":
                return new Orange();
            default:
                return null;
        }
    }
}
