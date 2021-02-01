package com.lightning.portal.service.impl;

import com.lightning.portal.bean.File;
import com.lightning.portal.bean.Folder;
import com.lightning.portal.mapper.FileMapper;
import com.lightning.portal.mapper.FolderMapper;
import com.lightning.portal.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author gyf
 * @Date 2021-02-01 18:57
 * @ClassName BatchServiceImpl
 * @Description
 */
@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    FolderMapper folderMapper;
    @Autowired
    FileMapper fileMapper;
    @Override
    public List<File> getFilesById(int destFolderId) {
        List<File> files =null;
        return files;
    }

    @Override
    public List<Folder> getFoldersById(int destFolderId) {
        List<Folder> folders =null;
        return folders;
    }

    @Override
    public int getRealFolderId(int userId) {
        folderMapper.selectFolderIdByUserId(userId);
        return 0;
    }
}
