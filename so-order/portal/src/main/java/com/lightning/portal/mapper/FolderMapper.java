package com.lightning.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author gyf
 * @Date 2021-02-01 16:07
 * @ClassName FolderMapper
 * @Description
 */
@Mapper
public interface FolderMapper {

    int selectFolderIdByUserId(@Param("userId") int userId);
}
