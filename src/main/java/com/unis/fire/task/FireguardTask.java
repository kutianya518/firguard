package com.unis.fire.task;

import com.unis.fire.pojo.Cloud;
import com.unis.fire.pojo.FireThreshold;
import com.unis.fire.service.FireGuardService;
import com.unis.fire.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author lgf
 * @create 2019/10/16
 * @desc 定时任务类
 */
@Controller
public class FireguardTask {

    @Value("${node.host:127.0.0.1}")
    private String host;
    @Value("${node.port:80}")
    private String port;
    @Value("${node.app:status}")
    private String requestUrl;
    @Value("node.cloud:Cloud")
    private String cloudTableName;
    @Value("node.fireThreshold:fire_threshold")
    private String fireThreshold;
    private Logger logger = LoggerFactory.getLogger(FireguardTask.class);
    @Autowired
    private FireGuardService fireGuardService;

    /**
     * 定时采集数据、入库
     */
    @Scheduled(cron = "${node.cron}")
    @RequestMapping("/test")
    @ResponseBody
    public void FirguardDataTask()  {
        String[] hostList = host.split(",");
        for (String host : hostList) {
            logger.info(host);
            ArrayList<Cloud> cloudArrayList = new ArrayList<>();
            ArrayList<FireThreshold> fireThresholdArrayList = new ArrayList<>();
            String url = "http://" + host + ":" + port + "/" + requestUrl;
            String result = HttpUtil.doGetJsoup(url);
            if (result!=null){
                ParseResponseData(host, result, cloudArrayList, fireThresholdArrayList);
                fireGuardService.insertIntoCloud(cloudArrayList);
                fireGuardService.insertIntoFthr(fireThresholdArrayList);
                logger.info(result);
            }
        }
    }

    /**
     * 解析数据，批量入库
     *
     * @param result
     */
    public void ParseResponseData(String host, String result, ArrayList<Cloud> cloudArrayList, ArrayList<FireThreshold> fireThresholdArrayList) {
        String flag = null;
        Date saveTime = new Date();
        List<String> resultList = Arrays.asList(result.split(System.getProperty("line.separator")));
        for (String line : resultList) {
            String[] lineArray = line.split(" ");
            flag = StringUtils.isNumeric(lineArray[0]) ? lineArray[1] : lineArray[0];
            switch (flag) {
                case "Cloud":
                    Cloud cloud = new Cloud();
                    cloud.setNodeId(host);
                    cloud.setPipeId(Integer.valueOf(lineArray[0]));
                    cloud.setSaveTime(saveTime);
                    cloud.setLineData(line);
                    for (int i = 1; i < lineArray.length; i = i + 2) {
                        setBeanData(lineArray[i], lineArray[i + 1], cloud);
                    }
                    cloudArrayList.add(cloud);
                    break;
                case "FThr":
                    FireThreshold fireThreshold = new FireThreshold();
                    fireThreshold.setNodeId(host);
                    fireThreshold.setPipeId(Integer.valueOf(lineArray[0]));
                    fireThreshold.setSaveTime(saveTime);
                    fireThreshold.setLineData(line);
                    String threshold = lineArray[2] + "," + lineArray[3] + "," + lineArray[4] + "," + lineArray[5];
                    fireThreshold.setFThr(threshold);
                    for (int i = 6; i < lineArray.length; i = i + 2) {
                        setBeanData(lineArray[i], lineArray[i + 1], fireThreshold);
                    }
                    fireThresholdArrayList.add(fireThreshold);
                    break;
                case "FAULT":
                    break;
                default:
                    break;
            }
        }


    }

    /**
     * 反射封装对象
     *
     * @param key   属性
     * @param value 值
     * @param t     对象
     * @param <T>   对象类型
     */
    public <T> void setBeanData(String key, String value, T t) {
        try {
            Field field = t.getClass().getDeclaredField(key);
            field.setAccessible(true);
            field.set(t, Double.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
