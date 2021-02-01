package com.lightning.portal.service.impl;

import com.lightning.portal.mapper.FolderMapper;
import com.lightning.portal.service.FolderService;
import com.mysql.cj.exceptions.StreamingNotifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author gyf
 * @Date 2021-02-01 16:02
 * @ClassName FolderServiceImpl
 * @Description
 */
@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    FolderMapper folderMapper;
    @Override
    /**
     * 创建文件夹
     */
    public String makeDir(String srcFolderName, int destFolderId) {
        String result ="false";
        String path = getPath(destFolderId);
        if(!"".equals(path)) {
            result = mkDir(srcFolderName, path);
            return result;
        }
        return result;
    }

    @Override
    public String moveFolder(int srcFolderId, int destFolderId) {
        String result = "false";

        return result;
    }

    @Override
    public String copyFolder(int srcFolderId, int destFolderId) {
        String result = "false";

        return result;
    }

    @Override
    public String deleteFolder(int srcFolderId) {
        String result = "false";

        return result;
    }

    /**
     * 获得父文件夹路径
     * @param destFolderId 父文件夹Id
     * @return 返回父文件夹路径
     */
    public String getPath(int destFolderId) {
        String path = "";
        return path;
    }

    /**
     * 创建文件夹与update表
     * @param srcFolderName 创建文件夹名
     * @param path 父文件夹路径
     */
    public String mkDir(String srcFolderName, String path) {
        String result = "false";
        return result;
    }
}
