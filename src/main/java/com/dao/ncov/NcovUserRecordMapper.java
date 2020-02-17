package com.dao.ncov;

import com.condition.ncov.NcovUserRecordCondition;
import com.model.ncov.NcovUserRecordEntity;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
* @author: fangqk
* @time: 2020-02-08 20:22
* @table: ncov_user_record
* @description: 
*/
public interface NcovUserRecordMapper {

    /**
     * @param  entity  table entity
     * @return List<NcovUserRecordEntity>
     */
    List<NcovUserRecordEntity> getByCondition(NcovUserRecordEntity entity);

    /**
     * @param  ID  ID
     * @return NcovUserRecordEntity
     */
    NcovUserRecordEntity getByID(@Param("ID") Integer ID);

    /**
     * @param  entity  table entity
     * @return int
     */
    int insert(NcovUserRecordEntity entity);

    /**
     * @param entity  table entity
     * @return int
     */
    int updateById(NcovUserRecordEntity entity);
    List<NcovUserRecordEntity> searchPage(NcovUserRecordCondition ncovUserRecordCondition);

    Long getCount(NcovUserRecordCondition ncovUserRecordCondition);

    int deleteByPrimaryKey(Long id);

}