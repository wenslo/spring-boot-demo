package com.github.wenslo.springbootdemo.cache;

import com.github.wenslo.springbootdemo.annotation.permission.PermissionGroup;
import com.github.wenslo.springbootdemo.model.system.Permission;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月10日 上午11:18
 * @description 权限收集器
 */
@Component
public class PermissionCollector implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(PermissionCollector.class);
    public static final Map<String, List<Permission>> permissionMap = Maps.newHashMap();
    public static final Set<String> permissionSet = Sets.newHashSet();


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        PermissionGroup permissionGroup = beanClass.getAnnotation(PermissionGroup.class);
        if (Objects.nonNull(permissionGroup)) {
            logger.debug("The permission name is {}", beanClass.getName());
            putPermissionIfPresent(beanClass);
        }
        return bean;
    }

    private void putPermissionIfPresent(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, List<Permission>> map = Arrays.stream(fields)
                .filter(s -> Objects.nonNull(s.getAnnotation(com.github.wenslo.springbootdemo.annotation.permission.Permission.class)))
                .map(s -> {
                    com.github.wenslo.springbootdemo.annotation.permission.Permission annotation = s.getAnnotation(com.github.wenslo.springbootdemo.annotation.permission.Permission.class);
                    try {
                        String name = (String) s.get(clazz);
                        logger.trace("get name is {}", name);
                        permissionSet.add(name);
                        return new Permission(annotation.value(), name, annotation.group());
                    } catch (IllegalAccessException e) {
                        logger.error("convert permissionMap label is error", e);
                        return null;
                    }
                }).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Permission::getGroup));
        permissionMap.putAll(map);
    }
}
