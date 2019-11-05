package com.github.wenslo.springbootdemo.controller;

import com.github.wenslo.fluent.security.SecurityUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:20
 * @description controller 基类
 */
public abstract class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    protected Gson gson;
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    protected String getLoginUsername() {
        return SecurityUtil.getLoginUser().getUsername();
    }

    @SuppressWarnings("unchecked")
    protected <T> Page<T> pageTranslate(Page page, Class<T> beTranslateClass) {
        List<T> resultContent = getPageContent(page, beTranslateClass);
        return populateNewPage(page, resultContent);
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> getPageContent(Page page, Class<T> beTranslateClass) {
        return (List<T>) page.getContent().stream().map(it -> modelMapper.map(it, beTranslateClass)).collect(Collectors.toList());
    }

    protected <T> Page<T> populateNewPage(Page page, List<T> resultContent) {
        return new PageImpl<>(resultContent, page.getPageable(), page.getTotalElements());
    }

    protected void expireUser(String username) {
        List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(username, false);
        if (null != sessionsInfo && sessionsInfo.size() > 0) {
            for (SessionInformation sessionInformation : sessionsInfo) {
                sessionInformation.expireNow();
            }
        }
    }
}
