package com.unis.fire.pojo;

import java.util.Date;

public class Cloud {
    /**node 编号**/
    private String nodeId;
    /**管号0,1,2,3**/
    private Integer pipeId;
    /**采集时间**/
    private Date saveTime;
    /**Current cloud level**/
    private Double Cloud;
    /**Current optical level**/
    private Double Op;
    /**Current CO level**/
    private Double Co;
    /**Current temperature**/
    private Double Te;
    /**Optical THist**/
    private Double OTh;
    /**CO THist**/
    private Double CTh;
    /**Temperature THist**/
    private Double TTh;
    /**Airflow**/
    private Double Af;
    /**Vanilla cloud reading**/
    private Double Cl;
    /**Boost value**/
    private Double Hy;
    /**Minimum cloud value**/
    private Double Cmin;
    /**Maximum cloud value**/
    private Double Cmax;
    /**Cloud background**/
    private Double Cbg;
    /**Combined fire and smoke value (CFS)**/
    private Double Flev;
    /**Current pipe fire level. 0 is pre-alarm, 1 is fire 1 etc…**/
    private Double FIRE;
    /**原始行数据**/
    private String lineData;


    public Cloud(){

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

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public Double getCloud() {
        return Cloud;
    }

    public void setCloud(Double cloud) {
        Cloud = cloud;
    }

    public Double getOp() {
        return Op;
    }

    public void setOp(Double op) {
        Op = op;
    }

    public Double getCo() {
        return Co;
    }

    public void setCo(Double co) {
        Co = co;
    }

    public Double getTe() {
        return Te;
    }

    public void setTe(Double te) {
        Te = te;
    }

    public Double getOTh() {
        return OTh;
    }

    public void setOTh(Double OTh) {
        this.OTh = OTh;
    }

    public Double getCTh() {
        return CTh;
    }

    public void setCTh(Double CTh) {
        this.CTh = CTh;
    }

    public Double getTTh() {
        return TTh;
    }

    public void setTTh(Double TTh) {
        this.TTh = TTh;
    }

    public Double getAf() {
        return Af;
    }

    public void setAf(Double af) {
        Af = af;
    }

    public Double getCl() {
        return Cl;
    }

    public void setCl(Double cl) {
        Cl = cl;
    }

    public Double getHy() {
        return Hy;
    }

    public void setHy(Double hy) {
        Hy = hy;
    }

    public Double getCmin() {
        return Cmin;
    }

    public void setCmin(Double cmin) {
        Cmin = cmin;
    }

    public Double getCmax() {
        return Cmax;
    }

    public void setCmax(Double cmax) {
        Cmax = cmax;
    }

    public Double getCbg() {
        return Cbg;
    }

    public void setCbg(Double cbg) {
        Cbg = cbg;
    }

    public Double getFlev() {
        return Flev;
    }

    public void setFlev(Double flev) {
        Flev = flev;
    }

    public Double getFIRE() {
        return FIRE;
    }

    public void setFIRE(Double FIRE) {
        this.FIRE = FIRE;
    }

    public String getLineData() {
        return lineData;
    }

    public void setLineData(String lineData) {
        this.lineData = lineData;
    }
}
