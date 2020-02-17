package com.dao.ncov;

import com.condition.ncov.NcovUsersCondition;
import com.model.ncov.NcovRegionEntity;
import com.model.ncov.NcovUsersEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* @author: fangqk
* @time: 2020-02-08 20:22
* @table: ncov_users
* @description: 
*/
public interface NcovUsersMapper {

    /**
     * @param  entity  table entity
     * @return List<NcovUsersEntity>
     */
    List<NcovUsersEntity> getByCondition(NcovUsersEntity entity);

    /**
     * @param  ID  ID
     * @return NcovUsersEntity
     */
    NcovUsersEntity getByID(@Param("ID") Integer ID);

    /**
     * @param  entity  table entity
     * @return int
     */
    int insert(NcovUsersEntity entity);

    /**
     * @param entity  table entity
     * @return int
     */
    int updateById(NcovUsersEntity entity);

    List<NcovUsersEntity> searchPage(NcovUsersCondition ncovUsersCondition);

    List<NcovUsersEntity> searchPageNo(NcovUsersCondition ncovUsersCondition);

    Long getCount(NcovUsersCondition ncovUsersCondition);

    int deleteByPrimaryKey(Long id);

    NcovUsersEntity selectByPrimaryKey(Long id);

    NcovUsersEntity findByWechatId(String id);
}