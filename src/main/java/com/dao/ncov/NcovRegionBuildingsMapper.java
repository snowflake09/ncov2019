package com.dao.ncov;

import com.condition.ncov.NcovBuildingCondition;
import com.model.ncov.NcovRegionBuildingsEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* @author: fangqk
* @time: 2020-02-11 14:29
* @table: ncov_region_buildings
* @description: 
*/
public interface NcovRegionBuildingsMapper {

    /**
     * @param  entity  table entity
     * @return List<NcovRegionBuildingsEntity>
     */
    List<NcovRegionBuildingsEntity> getByCondition(NcovRegionBuildingsEntity entity);

    /**
     * @param  ID  
     * @return NcovRegionBuildingsEntity
     */
    NcovRegionBuildingsEntity getByID(@Param("ID") Long ID);

    /**
     * @param  entity  table entity
     * @return int
     */
    int insert(NcovRegionBuildingsEntity entity);

    /**
     * @param entity  table entity
     * @return int
     */
    int updateById(NcovRegionBuildingsEntity entity);

    List<NcovRegionBuildingsEntity> searchPage(NcovBuildingCondition ncovBuildingCondition);

    Long getCount(NcovBuildingCondition ncovBuildingCondition);

    int deleteByPrimaryKey(Long id);

    List<NcovRegionBuildingsEntity>  getBuildingsList();
}