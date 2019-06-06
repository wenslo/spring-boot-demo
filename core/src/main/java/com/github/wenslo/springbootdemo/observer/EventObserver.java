package com.github.wenslo.springbootdemo.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.wenslo.springbootdemo.annotation.eventbus.Observer;
import com.github.wenslo.springbootdemo.observer.event.Event;
import com.google.common.eventbus.Subscribe;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-06 16:22
 * @description
 */
@Observer
public class EventObserver {

    private static final Logger logger = LoggerFactory.getLogger(EventObserver.class);

    @Subscribe
    public void call(Event event) {}
}
