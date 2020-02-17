package com.model.ncov;

import java.util.Date;

/**
 * @author: fangqk
 * @time: 2020-02-08 20:22
 * @table: ncov_user_record
 * @description: 
 */
public class NcovUserRecordEntity {

    /**
     * ID
     * max-len: 11
     * not-null: yes 
     * default: ''
     */
    private Integer ID;

    /**
     * 用户名
     * max-len: 40
     * not-null: no 
     * default: ''
     */
    private String USERNAME;

    /**
     * 年龄
     * max-len: 3
     * not-null: no 
     * default: ''
     */
    private Integer AGE;

    /**
     * 性别0女，1男
     * max-len: 1
     * not-null: no 
     * default: ''
     */
    private Integer SEX;

    /**
     * 体温
     * max-len: 10
     * not-null: no 
     * default: ''
     */
    private String TEMPERATURE;

    /**
     * 是否健康
     * max-len: 1
     * not-null: no 
     * default: ''
     */
    private Integer HEALTHY;

    /**
     * 健康说明
     * max-len: 100
     * not-null: no 
     * default: ''
     */
    private String HEALTHNOTE;

    /**
     * 上报用户ID
     * max-len: 10
     * not-null: no 
     * default: ''
     */
    private Long REPORTUSERID;

    /**
     * 上报用户名
     * max-len: 20
     * not-null: no 
     * default: ''
     */
    private String CREATEUSER;

    /**
     * 上报时间
     * max-len: 0
     * not-null: no 
     * default: ''
     */
    private Date CREATEDATE;

    /**
     * 修改时间
     * max-len: 0
     * not-null: no 
     * default: ''
     */
    private Date UPDATEDATE;

    /**
     * 修改用户
     * max-len: 20
     * not-null: no 
     * default: ''
     */
    private String UPDATEUSER;

    
    public Integer getID(){
        return this.ID;
    }

    public void setID(Integer ID){
        this.ID = ID;
    }
    
    public String getUSERNAME(){
        return this.USERNAME;
    }

    public void setUSERNAME(String USERNAME){
        this.USERNAME = USERNAME;
    }
    
    public Integer getAGE(){
        return this.AGE;
    }

    public void setAGE(Integer AGE){
        this.AGE = AGE;
    }
    
    public Integer getSEX(){
        return this.SEX;
    }

    public void setSEX(Integer SEX){
        this.SEX = SEX;
    }
    
    public String getTEMPERATURE(){
        return this.TEMPERATURE;
    }

    public void setTEMPERATURE(String TEMPERATURE){
        this.TEMPERATURE = TEMPERATURE;
    }
    
    public Integer getHEALTHY(){
        return this.HEALTHY;
    }

    public void setHEALTHY(Integer HEALTHY){
        this.HEALTHY = HEALTHY;
    }
    
    public String getHEALTHNOTE(){
        return this.HEALTHNOTE;
    }

    public void setHEALTHNOTE(String HEALTHNOTE){
        this.HEALTHNOTE = HEALTHNOTE;
    }
    
    public Long getREPORTUSERID(){
        return this.REPORTUSERID;
    }

    public void setREPORTUSERID(Long REPORTUSERID){
        this.REPORTUSERID = REPORTUSERID;
    }
    
    public String getCREATEUSER(){
        return this.CREATEUSER;
    }

    public void setCREATEUSER(String CREATEUSER){
        this.CREATEUSER = CREATEUSER;
    }
    
    public Date getCREATEDATE(){
        return this.CREATEDATE;
    }

    public void setCREATEDATE(Date CREATEDATE){
        this.CREATEDATE = CREATEDATE;
    }
    
    public Date getUPDATEDATE(){
        return this.UPDATEDATE;
    }

    public void setUPDATEDATE(Date UPDATEDATE){
        this.UPDATEDATE = UPDATEDATE;
    }
    
    public String getUPDATEUSER(){
        return this.UPDATEUSER;
    }

    public void setUPDATEUSER(String UPDATEUSER){
        this.UPDATEUSER = UPDATEUSER;
    }
    
}
