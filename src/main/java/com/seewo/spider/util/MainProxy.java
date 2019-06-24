package com.seewo.spider.util;

import com.seewo.spider.Main;
import static com.seewo.spider.common.Constant.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * 程序入口Main代理类
 * 使用CGLIB进行增强，织入日志记录
 *
 * @author qiuwuhui
 * @date 2019/6/10
 */
public class MainProxy implements MethodInterceptor {

    private Main target;
    private static Logger logger = Logger.getLogger(Main.class);

    public MainProxy(Main target) {
        super();
        this.target = target;
    }

    public Main createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (Main) enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long begin = System.currentTimeMillis();
        logger.info(method.getName() + " begin...");
        Object result = methodProxy.invokeSuper(proxy, args);
        long cost = System.currentTimeMillis() - begin;
        logger.info(method.getName() + " success!");
        if (cost < MILLIS_PER_SECOND) {
            logger.info(method.getName() + " cost " + cost + "ms.");
        } else if (cost < MILLIS_PER_MINUTE) {
            long second = cost / MILLIS_PER_SECOND;
            long millis = cost % MILLIS_PER_SECOND;
            logger.info(method.getName() + " cost " + second + "s " + millis + "ms.");
        } else if (cost < MILLIS_PER_HOUR) {
            long minute = cost / MILLIS_PER_MINUTE;
            long second = cost % MILLIS_PER_MINUTE / MILLIS_PER_SECOND;
            logger.info(method.getName() + " cost " + minute + "min " + second + "s.");
        } else {
            long hour = cost / MILLIS_PER_HOUR;
            long minute = cost % MILLIS_PER_HOUR / MILLIS_PER_MINUTE;
            long second = cost % MILLIS_PER_MINUTE / MILLIS_PER_SECOND;
            logger.info(method.getName() + " cost " + hour + "h " + minute + "min " + second + "s.");
        }
        return result;
    }
}
