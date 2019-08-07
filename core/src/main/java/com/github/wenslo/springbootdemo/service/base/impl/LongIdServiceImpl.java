package com.github.wenslo.springbootdemo.service.base.impl;

import com.github.wenslo.springbootdemo.condition.base.LongIdCondition;
import com.github.wenslo.springbootdemo.domain.Pageable;
import com.github.wenslo.springbootdemo.domain.querydsl.CustomEntityPathBase;
import com.github.wenslo.springbootdemo.model.base.LongIdEntity;
import com.github.wenslo.springbootdemo.reposiroty.base.LongIdRepository;
import com.github.wenslo.springbootdemo.service.base.LongIdService;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月04日 上午10:43
 * @description
 */
public abstract class LongIdServiceImpl<T extends LongIdEntity, C extends LongIdCondition> implements LongIdService<T, C> {
    private static final Logger logger = LoggerFactory.getLogger(LongIdServiceImpl.class);
    @Autowired
    protected LongIdRepository<T, Long> repository;

    private List<Predicate> conditionBuilder = Lists.newArrayList();

    @Override
    public T get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> save(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void remove(T entity) {
        repository.delete(entity);
    }

    @Override
    public void remove(Iterable<T> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public Page<T> getByCondition(C condition, Pageable pageable) {
        return repository.findAll(toPredicate(condition), pageable);
    }

    @Override
    public List<T> getByCondition(C condition) {
        return this.getByCondition(condition, Sort.by("id"));
    }

    @Override
    public List<T> getByCondition(C condition, Sort sort) {
        return Lists.newArrayList(repository.findAll(toPredicate(condition), sort));
    }

    /**
     * The page list query condition populate,so far , use BooleanBuilder ,if we have any batter method , try replace it
     *
     * @param condition query condition
     * @return populated query condition
     */
    protected Predicate toPredicate(C condition) {
        List<Predicate> predicates = conditionBuild(condition);
        BooleanBuilder builder = new BooleanBuilder();
        predicates.forEach(builder::and);
        return builder;
    }

    /**
     * LongIdEntity condition building
     *
     * @param condition query condition
     * @return The completed basic query condition constructed
     * <p>On many tests, the reflect speed time is 6ms </p>
     */
    protected List<Predicate> conditionBuild(C condition) {
//        long start = System.currentTimeMillis();
        CustomEntityPathBase<T> pathBase = getEntityPath();
        Long id = condition.getId();
        if (Objects.nonNull(id)) {
            conditionBuilder.add(pathBase.id.eq(id));
        }
        List<Long> ids = condition.getIds();
        if (Objects.nonNull(ids) && !ids.isEmpty()) {
            conditionBuilder.add(pathBase.id.in(ids));
        }
        LocalDateTime createdAtStart = condition.getCreatedAtStart();
        LocalDateTime createdAtEnd = condition.getCreatedAtEnd();
        if (Objects.nonNull(createdAtStart) && Objects.nonNull(createdAtEnd)) {
            conditionBuilder.add(pathBase.createdAt.between(createdAtStart, createdAtEnd));
        } else {
            if (Objects.nonNull(createdAtStart)) {
                conditionBuilder.add(pathBase.createdAt.goe(createdAtStart));
            }
            if (Objects.nonNull(createdAtEnd)) {
                conditionBuilder.add(pathBase.updatedAt.loe(createdAtEnd));
            }
        }

        LocalDateTime updatedAtStart = condition.getUpdatedAtStart();
        LocalDateTime updatedAtEnd = condition.getUpdatedAtEnd();
        if (Objects.nonNull(updatedAtStart) && Objects.nonNull(updatedAtEnd)) {
            conditionBuilder.add(pathBase.updatedAt.between(updatedAtStart, updatedAtEnd));
        } else {
            if (Objects.nonNull(updatedAtStart)) {
                conditionBuilder.add(pathBase.updatedAt.goe(updatedAtStart));
            }
            if (Objects.nonNull(updatedAtEnd)) {
                conditionBuilder.add(pathBase.updatedAt.loe(updatedAtEnd));
            }
        }
//        long end = System.currentTimeMillis();
//        logger.debug("本次基础查询构建，总共执行的时间为：{}毫秒", end - start);
        return conditionBuilder;
    }

    protected CustomEntityPathBase<T> getEntityPath() {
        Class<T> tClass = getTClass();
        return new CustomEntityPathBase<>(tClass, getName(tClass));
    }

    private String getName(Class<T> tClass) {
        StringBuilder nameBuilder = new StringBuilder();
        String name = tClass.getSimpleName();
        String letterHead = StringUtils.lowerCase(name.substring(0, 1));
        nameBuilder.append(letterHead).append(name.substring(1));
        return nameBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
