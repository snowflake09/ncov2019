package com.dto.ncov;

import java.util.Date;

/**
 * @author: fangqk
 * @time: 2020-02-08 20:22
 * @table: ncov_region
 * @description: 
 */
public class NcovRegionDto {

    /**
     * 
     * max-len: 10
     * not-null: yes 
     * default: ''
     */
    private Long ID;

    /**
     * 小区名称
     * max-len: 100
     * not-null: yes 
     * default: ''
     */
    private String NAME;

    /**
     * 小区地址
     * max-len: 150
     * not-null: no 
     * default: ''
     */
    private String ADDRESS;

    /**
     * 管理员id
     * max-len: 10
     * not-null: yes 
     * default: ''
     */
    private Long MANAGEID;

    /**
     * 管理员名词
     * max-len: 45
     * not-null: no 
     * default: ''
     */
    private String MANAGENAME;

    /**
     * 状态1停用，0启用
     * max-len: 1
     * not-null: yes 
     * default: ''
     */
    private Integer STATUS;

    /**
     * 创建时间
     * max-len: 0
     * not-null: no 
     * default: ''
     */
    private Date CREATEDATE;

    /**
     * 创建人
     * max-len: 20
     * not-null: no 
     * default: ''
     */
    private String CREATEUSER;

    /**
     * 修改时间
     * max-len: 0
     * not-null: no 
     * default: ''
     */
    private Date UPDATEDATE;

    /**
     * 修改人
     * max-len: 20
     * not-null: no 
     * default: ''
     */
    private String UPDATEUSER;

    private String  REGIONQRPATH;
    private String CODE;

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getREGIONQRPATH() {
        return REGIONQRPATH;
    }

    public void setREGIONQRPATH(String REGIONQRPATH) {
        this.REGIONQRPATH = REGIONQRPATH;
    }

    public Long getID(){
        return this.ID;
    }

    public void setID(Long ID){
        this.ID = ID;
    }
    
    public String getNAME(){
        return this.NAME;
    }

    public void setNAME(String NAME){
        this.NAME = NAME;
    }
    
    public String getADDRESS(){
        return this.ADDRESS;
    }

    public void setADDRESS(String ADDRESS){
        this.ADDRESS = ADDRESS;
    }
    
    public Long getMANAGEID(){
        return this.MANAGEID;
    }

    public void setMANAGEID(Long MANAGEID){
        this.MANAGEID = MANAGEID;
    }
    
    public String getMANAGENAME(){
        return this.MANAGENAME;
    }

    public void setMANAGENAME(String MANAGENAME){
        this.MANAGENAME = MANAGENAME;
    }
    
    public Integer getSTATUS(){
        return this.STATUS;
    }

    public void setSTATUS(Integer STATUS){
        this.STATUS = STATUS;
    }
    
    public Date getCREATEDATE(){
        return this.CREATEDATE;
    }

    public void setCREATEDATE(Date CREATEDATE){
        this.CREATEDATE = CREATEDATE;
    }
    
    public String getCREATEUSER(){
        return this.CREATEUSER;
    }

    public void setCREATEUSER(String CREATEUSER){
        this.CREATEUSER = CREATEUSER;
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
