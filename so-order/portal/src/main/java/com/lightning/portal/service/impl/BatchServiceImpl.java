package com.lightning.portal.service.impl;

import com.lightning.portal.bean.Myfile;
import com.lightning.portal.bean.Folder;
import com.lightning.portal.mapper.FileMapper;
import com.lightning.portal.mapper.FolderMapper;
import com.lightning.portal.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Myfile> getFilesById(int destFolderId) {
        Map<String, Object> sohm = new HashMap<>();
        sohm.put("folderId",destFolderId);
        List<Myfile> myfiles=fileMapper.selectByMap(sohm);
        return myfiles;
    }

    @Override
    public List<Folder> getFoldersById(int destFolderId) {
        Map<String, Object> sohm = new HashMap<>();
        sohm.put("parentId",destFolderId);
        List<Folder> folders =folderMapper.selectByMap(sohm);
        return folders;
    }

    @Override
    public int getRealFolderId(int userId) {
        int id = folderMapper.selectFolderIdByUserId(userId);
        return id;
    }

    @Override
    public List<Folder> getFoldersByUserIdAndName(int userId, String targetName) {
        List<Folder> folders = folderMapper.selectByUserIdAndName(userId,"%"+targetName+"%");
        return folders;
    }

    @Override
    public List<Myfile> getFilesByUserIdAndName(int userId, String fileName) {
        List<Myfile> myfiles = fileMapper.selectByUserIdAndName(userId,"%"+fileName+"%");
        return myfiles;
    }

}
