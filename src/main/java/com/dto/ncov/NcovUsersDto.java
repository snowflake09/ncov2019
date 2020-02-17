package com.dto.ncov;

import java.util.Date;

/**
 * @author: fangqk
 * @time: 2020-02-08 20:22
 * @table: ncov_users
 * @description: 
 */
public class NcovUsersDto {

    /**
     * ID
     * max-len: 11
     * not-null: yes 
     * default: ''
     */
    private Integer ID;

    /**
     * 微信号
     * max-len: 40
     * not-null: yes 
     * default: ''
     */
    private String WECHATID;

    /**
     * 姓名
     * max-len: 20
     * not-null: yes 
     * default: ''
     */
    private String NAME;

    /**
     * 联系电话
     * max-len: 11
     * not-null: yes 
     * default: ''
     */
    private String PHONE;

    /**
     * 身份证号码
     * max-len: 18
     * not-null: yes 
     * default: ''
     */
    private String CARDNO;

    /**
     * 
     * max-len: 3
     * not-null: no 
     * default: ''
     */
    private Integer AGE;

    /**
     * 
     * max-len: 1
     * not-null: no 
     * default: ''
     */
    private Integer SEX;

    /**
     * 省名称
     * max-len: 40
     * not-null: no 
     * default: ''
     */
    private String PROVINCENAME;

    /**
     * 市名称
     * max-len: 40
     * not-null: no 
     * default: ''
     */
    private String CITYNAME;

    /**
     * 区名词
     * max-len: 40
     * not-null: no 
     * default: ''
     */
    private String AREANAME;

    /**
     * 用户类型0住户，1租户，2外来
     * max-len: 1
     * not-null: no 
     * default: ''
     */
    private Integer USERTYPE;

    /**
     * 小区ID
     * max-len: 10
     * not-null: no 
     * default: ''
     */
    private Long REGIONID;

    /**
     * 小区住址
     * max-len: 100
     * not-null: no 
     * default: ''
     */
    private String REGIONADDRESS;

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
     * max-len: 45
     * not-null: no 
     * default: ''
     */
    private String HEALTHNOTE;
    private String CARNO;
    private String TRIPNO;
    private String DOG;
    private String REASON;

    public String getCARNO() {
        return CARNO;
    }

    public void setCARNO(String CARNO) {
        this.CARNO = CARNO;
    }

    public String getTRIPNO() {
        return TRIPNO;
    }

    public void setTRIPNO(String TRIPNO) {
        this.TRIPNO = TRIPNO;
    }

    public String getDOG() {
        return DOG;
    }

    public void setDOG(String DOG) {
        this.DOG = DOG;
    }

    public String getREASON() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON = REASON;
    }

    /**
     * 状态0有效，1删除
     * max-len: 1
     * not-null: no 
     * default: ''
     */
    private Integer USERSTATUS;

    /**
     * 创建时间
     * max-len: 0
     * not-null: no 
     * default: ''
     */
    private Date CREATEDATE;

    /**
     * 
     * max-len: 40
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
    private Date UPDATEDATE;

    /**
     * 
     * max-len: 40
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
    
    public String getWECHATID(){
        return this.WECHATID;
    }

    public void setWECHATID(String WECHATID){
        this.WECHATID = WECHATID;
    }
    
    public String getNAME(){
        return this.NAME;
    }

    public void setNAME(String NAME){
        this.NAME = NAME;
    }
    
    public String getPHONE(){
        return this.PHONE;
    }

    public void setPHONE(String PHONE){
        this.PHONE = PHONE;
    }
    
    public String getCARDNO(){
        return this.CARDNO;
    }

    public void setCARDNO(String CARDNO){
        this.CARDNO = CARDNO;
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
    
    public String getPROVINCENAME(){
        return this.PROVINCENAME;
    }

    public void setPROVINCENAME(String PROVINCENAME){
        this.PROVINCENAME = PROVINCENAME;
    }
    
    public String getCITYNAME(){
        return this.CITYNAME;
    }

    public void setCITYNAME(String CITYNAME){
        this.CITYNAME = CITYNAME;
    }
    
    public String getAREANAME(){
        return this.AREANAME;
    }

    public void setAREANAME(String AREANAME){
        this.AREANAME = AREANAME;
    }
    
    public Integer getUSERTYPE(){
        return this.USERTYPE;
    }

    public void setUSERTYPE(Integer USERTYPE){
        this.USERTYPE = USERTYPE;
    }
    
    public Long getREGIONID(){
        return this.REGIONID;
    }

    public void setREGIONID(Long REGIONID){
        this.REGIONID = REGIONID;
    }
    
    public String getREGIONADDRESS(){
        return this.REGIONADDRESS;
    }

    public void setREGIONADDRESS(String REGIONADDRESS){
        this.REGIONADDRESS = REGIONADDRESS;
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
    
    public Integer getUSERSTATUS(){
        return this.USERSTATUS;
    }

    public void setUSERSTATUS(Integer USERSTATUS){
        this.USERSTATUS = USERSTATUS;
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
