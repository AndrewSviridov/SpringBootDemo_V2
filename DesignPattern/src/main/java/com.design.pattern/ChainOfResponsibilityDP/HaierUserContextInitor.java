package com.design.pattern.ChainOfResponsibilityDP;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SpringBootDemo
 * Author: 王杰
 * Create Time:2019/5/9 16:19
 */
public class HaierUserContextInitor extends AbstractUserContextInitor {

    @Override
    public UserContext excute(CurrentUserInfo currentUserInfo) {

        UserContext userContext = new UserContext();
        userContext.setBrandName("Haier");
        userContext.setDesc("这是海尔品牌的用户！");
        userContext.setAge(currentUserInfo.getAge());
        return userContext;
    }

    @Override
    public boolean accept(CurrentUserInfo currentUserInfo) {

        return "haier".equalsIgnoreCase(currentUserInfo.getDomainName());
    }
}
