package com.seewo.spider.vo;

import com.seewo.spider.common.Constant;
import com.seewo.spider.util.PropertiesUtils;
import com.seewo.spider.vo.common.PropertiesPair;

/**
 * 爬虫配置类
 *
 * @author qiuwuhui
 * @date 2019/6/10
 */
public class SpiderConfig {

    @PropertiesPair(key = "spider.threadNum")
    private Integer threadNum;
    @PropertiesPair(key = "spider.sleepTime")
    private Integer sleepTime;
    @PropertiesPair(key = "spider.socketTimeOut")
    private Integer socketTimeOut;

    public Integer getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Integer getSocketTimeOut() {
        return socketTimeOut;
    }

    public void setSocketTimeOut(Integer socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpiderConfig{");
        sb.append("threadNum=").append(threadNum);
        sb.append(", sleepTime=").append(sleepTime);
        sb.append(", socketTimeOut=").append(socketTimeOut);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(PropertiesUtils.propertiesToBean(Constant.SPIDER_CONFIG_PATH, SpiderConfig.class));
    }
}
