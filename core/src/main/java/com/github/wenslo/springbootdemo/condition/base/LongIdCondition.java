package com.github.wenslo.springbootdemo.condition.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-01 00:13
 * @description
 */
public abstract class LongIdCondition implements Serializable {
    protected Long id;
    protected List<Long> ids;
    protected LocalDateTime createdAtStart;
    protected LocalDateTime createdAtEnd;
    protected LocalDateTime updatedAtStart;
    protected LocalDateTime updatedAtEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public LocalDateTime getCreatedAtStart() {
        return createdAtStart;
    }

    public void setCreatedAtStart(LocalDateTime createdAtStart) {
        this.createdAtStart = createdAtStart;
    }

    public LocalDateTime getCreatedAtEnd() {
        return createdAtEnd;
    }

    public void setCreatedAtEnd(LocalDateTime createdAtEnd) {
        this.createdAtEnd = createdAtEnd;
    }

    public LocalDateTime getUpdatedAtStart() {
        return updatedAtStart;
    }

    public void setUpdatedAtStart(LocalDateTime updatedAtStart) {
        this.updatedAtStart = updatedAtStart;
    }

    public LocalDateTime getUpdatedAtEnd() {
        return updatedAtEnd;
    }

    public void setUpdatedAtEnd(LocalDateTime updatedAtEnd) {
        this.updatedAtEnd = updatedAtEnd;
    }
}
