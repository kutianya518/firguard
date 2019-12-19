package com.unis.fire.pojo;

import java.util.Date;

public class FireThreshold {
    /**
     * node 编号
     **/
    private String nodeId;
    /**
     * 管号0,1,2,3
     **/
    private Integer pipeId;
    /**
     * A B C D. Fire thresholds PreAlarm(A) Fire 1(B) Fire 2(C) Fire 3(D)
     **/
    private String FThr;
    /**
     * 采集时间
     **/
    private Date saveTime;
    private Double Flag;
    private Double Obs;
    private Double AfAcc;
    private Double GAdj;
    private Double RCount;
    private Double ITime;
    private Double Raf;
    private Double Chg;
    private Double CErr;
    /**
     * 原始行数据
     **/
    private String lineData;


    public FireThreshold() {

    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getPipeId() {
        return pipeId;
    }

    public void setPipeId(Integer pipeId) {
        this.pipeId = pipeId;
    }

    public String getFThr() {
        return FThr;
    }

    public void setFThr(String FThr) {
        this.FThr = FThr;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public Double getFlag() {
        return Flag;
    }

    public void setFlag(Double flag) {
        Flag = flag;
    }

    public Double getObs() {
        return Obs;
    }

    public void setObs(Double obs) {
        Obs = obs;
    }

    public Double getAfAcc() {
        return AfAcc;
    }

    public void setAfAcc(Double afAcc) {
        AfAcc = afAcc;
    }

    public Double getGAdj() {
        return GAdj;
    }

    public void setGAdj(Double GAdj) {
        this.GAdj = GAdj;
    }

    public Double getRCount() {
        return RCount;
    }

    public void setRCount(Double RCount) {
        this.RCount = RCount;
    }

    public Double getITime() {
        return ITime;
    }

    public void setITime(Double ITime) {
        this.ITime = ITime;
    }

    public Double getRaf() {
        return Raf;
    }

    public void setRaf(Double raf) {
        Raf = raf;
    }

    public Double getChg() {
        return Chg;
    }

    public void setChg(Double chg) {
        Chg = chg;
    }

    public Double getCErr() {
        return CErr;
    }

    public void setCErr(Double CErr) {
        this.CErr = CErr;
    }

    public String getLineData() {
        return lineData;
    }

    public void setLineData(String lineData) {
        this.lineData = lineData;
    }
}
