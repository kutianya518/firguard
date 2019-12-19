package com.unis.fire.service.impl;

import com.unis.fire.mapper.FireGuardMapper;
import com.unis.fire.pojo.Cloud;
import com.unis.fire.pojo.FireThreshold;
import com.unis.fire.service.FireGuardService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author lgf
 * @create 2019/10/16
 * @desc
 */
@Service
public class FireGuardServiceImpl implements FireGuardService {
    @Resource
    private FireGuardMapper fireGuardMapper;
    @Override
    public void insertIntoCloud(ArrayList<Cloud> cloudArrayList) {
        fireGuardMapper.insertIntoCloud(cloudArrayList);
    }

    @Override
    public void insertIntoFthr(ArrayList<FireThreshold> fireThresholdArrayList) {
        fireGuardMapper.insertIntoFthr(fireThresholdArrayList);
    }

}
