package com.unis.fire.mapper;


import com.unis.fire.pojo.Cloud;
import com.unis.fire.pojo.FireThreshold;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author lgf
 * @create 2019/10/16
 * @desc
 */
@Repository
public interface FireGuardMapper {
    /**
     * 批量入库Cloud
     * @param cloudArrayList cloud集合
     */
    void insertIntoCloud(ArrayList<Cloud> cloudArrayList);

    /**
     * 批量入库FireThr
     * @param fireThresholdArrayList
     */
    void insertIntoFthr(ArrayList<FireThreshold> fireThresholdArrayList);


}
