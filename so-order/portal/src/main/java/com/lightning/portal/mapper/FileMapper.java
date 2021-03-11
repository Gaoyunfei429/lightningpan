package com.lightning.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lightning.portal.bean.Myfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gyf
 * @Date 2021-02-01 00:20
 * @ClassName FileMapper
 * @Description
 */
@Mapper
public interface FileMapper extends BaseMapper<Myfile> {
    List<Myfile> selectByUserIdAndName(@Param("userId") String userId, @Param("fileName") String fileName);

    int updateNameById(@Param("srcFildId") int srcFileId, @Param("newName") String newName);
}
