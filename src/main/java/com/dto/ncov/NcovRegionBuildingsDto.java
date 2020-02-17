package com.dto.ncov;

import java.util.Date;

/**
 * @author: fangqk
 * @time: 2020-02-11 14:29
 * @table: ncov_region_buildings
 * @description: 
 */
public class NcovRegionBuildingsDto {

    /**
     * 
     * max-len: 11
     * not-null: yes 
     * default: ''
     */
    private Integer ID;

    /**
     * 楼号
     * max-len: 30
     * not-null: no 
     * default: ''
     */
    private String BUILDINGNAME;

    /**
     * 管理员姓名
     * max-len: 30
     * not-null: no 
     * default: ''
     */
    private String MANAGERNAME;

    /**
     * 管理员电话
     * max-len: 11
     * not-null: no 
     * default: ''
     */
    private String MANAGERPHONE;

    /**
     * 
     * max-len: 10
     * not-null: no 
     * default: ''
     */
    private Integer REGIONID;

    /**
     * 状态
     * max-len: 1
     * not-null: no 
     * default: ''
     */
    private Integer STATUS;

    /**
     * 
     * max-len: 30
     * not-null: no 
     * default: ''
     */
    private String CREATEUSER;

    /**
     * 
     * max-len: 0
     * not-null: no 
     * default: ''
     */
    private Date CREATETIME;

    /**
     * 
     * max-len: 30
     * not-null: no 
     * default: ''
     */
    private String UPDATEUSER;

    /**
     * 
     * max-len: 0
     * not-null: no 
     * default: ''
     */
    private Date UPDATETIME;
    private String NAME;

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public Integer getID(){
        return this.ID;
    }

    public void setID(Integer ID){
        this.ID = ID;
    }
    
    public String getBUILDINGNAME(){
        return this.BUILDINGNAME;
    }

    public void setBUILDINGNAME(String BUILDINGNAME){
        this.BUILDINGNAME = BUILDINGNAME;
    }
    
    public String getMANAGERNAME(){
        return this.MANAGERNAME;
    }

    public void setMANAGERNAME(String MANAGERNAME){
        this.MANAGERNAME = MANAGERNAME;
    }
    
    public String getMANAGERPHONE(){
        return this.MANAGERPHONE;
    }

    public void setMANAGERPHONE(String MANAGERPHONE){
        this.MANAGERPHONE = MANAGERPHONE;
    }
    
    public Integer getREGIONID(){
        return this.REGIONID;
    }

    public void setREGIONID(Integer REGIONID){
        this.REGIONID = REGIONID;
    }
    
    public Integer getSTATUS(){
        return this.STATUS;
    }

    public void setSTATUS(Integer STATUS){
        this.STATUS = STATUS;
    }
    
    public String getCREATEUSER(){
        return this.CREATEUSER;
    }

    public void setCREATEUSER(String CREATEUSER){
        this.CREATEUSER = CREATEUSER;
    }
    
    public Date getCREATETIME(){
        return this.CREATETIME;
    }

    public void setCREATETIME(Date CREATETIME){
        this.CREATETIME = CREATETIME;
    }
    
    public String getUPDATEUSER(){
        return this.UPDATEUSER;
    }

    public void setUPDATEUSER(String UPDATEUSER){
        this.UPDATEUSER = UPDATEUSER;
    }
    
    public Date getUPDATETIME(){
        return this.UPDATETIME;
    }

    public void setUPDATETIME(Date UPDATETIME){
        this.UPDATETIME = UPDATETIME;
    }
    
}
