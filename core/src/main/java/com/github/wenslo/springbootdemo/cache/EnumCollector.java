package com.github.wenslo.springbootdemo.cache;

import com.github.wenslo.fluent.core.domain.SimpleEnum;
import com.github.wenslo.springbootdemo.enums.BaseEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月10日 下午2:46
 * @description 枚举收集器
 */
@Component
public class EnumCollector implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(EnumCollector.class);
    public final Map<String, List<SimpleEnum>> enums = Maps.newHashMap();

    @SuppressWarnings("unchecked")
    @Override
    public void run(String... args) throws Exception {
        logger.debug("------------------enum preparing-------------------------");
        Reflections reflections = new Reflections("com.github.wenslo.springbootdemo.enums");
        Set<Class<? extends BaseEnum>> types = reflections.getSubTypesOf(BaseEnum.class);
        for (Class<? extends BaseEnum> it : types) {
            Method valuesMethod = it.getMethod("values");
            Method labelMethod = it.getMethod("getLabel");

            Enum<? extends BaseEnum>[] result = (Enum<? extends BaseEnum>[]) valuesMethod.invoke(it, new Object[]{});
            List<SimpleEnum> list = Lists.newArrayList();
            for (Enum<? extends BaseEnum> anEnum : result) {
                list.add(new SimpleEnum(anEnum.ordinal(), anEnum.name(), (String) labelMethod.invoke(anEnum, new Object[]{})));
            }
            enums.put(it.getSimpleName(), list);
            logger.trace("enums is {}", enums);
        }
    }
}
