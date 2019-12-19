package com.unis.fire.service;

import com.unis.fire.pojo.Cloud;
import com.unis.fire.pojo.FireThreshold;

import java.util.ArrayList;

/**
 * @author lgf
 * @create 2019/10/16
 * @desc
 */
public interface FireGuardService {
    /**
     * 入库Cloud
     * @param cloudArrayList
     */
    void insertIntoCloud(ArrayList<Cloud> cloudArrayList);

    /**
     * 入库 FireThr
     * @param fireThresholdArrayList
     */
    void insertIntoFthr(ArrayList<FireThreshold> fireThresholdArrayList);


}
