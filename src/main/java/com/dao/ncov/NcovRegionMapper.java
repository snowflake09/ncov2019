package com.dao.ncov;

import com.condition.ncov.NcovRegionCondition;
import com.model.ncov.NcovRegionEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* @author: fangqk
* @time: 2020-02-08 20:22
* @table: ncov_region
* @description: 
*/
public interface NcovRegionMapper {

    /**
     * @param  entity  table entity
     * @return List<NcovRegionEntity>
     */
    List<NcovRegionEntity> getByCondition(NcovRegionEntity entity);

    /**
     * @param  ID  
     * @return NcovRegionEntity
     */
    NcovRegionEntity getByID(@Param("ID") Long ID);

    /**
     * @param  entity  table entity
     * @return int
     */
    int insert(NcovRegionEntity entity);

    /**
     * @param entity  table entity
     * @return int
     */
    int updateById(NcovRegionEntity entity);

    List<NcovRegionEntity> searchPage(NcovRegionCondition ncovRegionCondition);

    Long getCount(NcovRegionCondition ncovRegionCondition);

    int deleteByPrimaryKey(Long id);

    List<NcovRegionEntity>  getRegionList();

    List<Long> getIds(Long sessionid);

    NcovRegionEntity getByCode(String code);
}