package com.lightning.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lightning.portal.bean.Folder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gyf
 * @Date 2021-02-01 16:07
 * @ClassName FolderMapper
 * @Description
 */
@Mapper
public interface FolderMapper extends BaseMapper<Folder> {

    int selectFolderIdByUserId(@Param("userId") String userId);

    List<Integer> selectIdsByParentId(@Param("parentId") Integer parentId);

    List<Folder> selectByUserIdAndName(@Param("userId") String userId, @Param("folderName") String folderName);

    int updateNameById(@Param("srcFolderId") int srcFolderId, @Param("newName") String newName);
}
