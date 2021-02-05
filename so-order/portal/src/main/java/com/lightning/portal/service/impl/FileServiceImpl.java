package com.lightning.portal.service.impl;

import com.lightning.portal.bean.Folder;
import com.lightning.portal.bean.Myfile;
import com.lightning.portal.mapper.FileMapper;
import com.lightning.portal.mapper.FolderMapper;
import com.lightning.portal.service.FileService;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author gyf
 * @Date 2021-02-01 00:16
 * @ClassName FileServiceImpl
 * @Description
 */
@Log
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;
    @Autowired
    FolderMapper folderMapper;
    @Autowired
    FolderServiceImpl folderService;

    /**
     * 移动文件
     *
     * @param srcFileId
     * @param destFolderId
     * @return
     */
    @Override
    public String moveFile(int srcFileId, int destFolderId) {
        String srcPath = getPath(srcFileId);
        String destPath = folderService.getPath(destFolderId);
        if (!"".equals(srcPath) && !"".equals(destPath)) {
            try {
                FileUtils.moveFileToDirectory(new File(srcPath), new File(destPath), false);
                changeParentId(srcFileId, destFolderId);
            } catch (IOException e) {
                e.printStackTrace();
                return "false";
            }
            return "true";
        }
        return "false";
    }

    private void changeParentId(int srcFileId, int destFolderId) {
        Myfile myFile = fileMapper.selectById(srcFileId);
        myFile.setFolderId(destFolderId);
        fileMapper.updateById(myFile);
    }

    public String getPath(int srcFileId) {
        Myfile file = fileMapper.selectById(srcFileId);
        if (file != null) {
            StringBuilder path = new StringBuilder();
            path.insert(0, folderService.getPath(file.getFolderId()) + "\\" + file.getFileName());
            return path.toString();
        }
        return "";
    }

    @Override
    public String reName(int srcFileId, String newName) {
        try {
            String path = getPath(srcFileId);
            File file = new File(path);
            String parent = file.getParent();
            File newFile = new File(parent + "\\" + newName);
            if (!newFile.exists()) {
                file.renameTo(newFile);
                fileMapper.updateNameById(srcFileId, newName);
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @Override
    public String uploadFile(int destFolderId, MultipartFile[] mpfs) {
        String path = folderService.getPath(destFolderId);
        if (!"".equals(path)) {
            File file = null;
            try {
                for (MultipartFile mpf : mpfs) {
                    file = new File(path + "\\" + mpf.getOriginalFilename());
                    if (!file.exists()) {
                        mpf.transferTo(file);
                        mkFile(mpf.getOriginalFilename(), mpf.getSize(), destFolderId);
                    }
                }
                return "true";
            } catch (IOException e) {
                e.printStackTrace();
                return "false";
            }
        }
        return "false";
    }

    /**
     * 复制文件
     *
     * @param srcFileId
     * @param destFolderId
     * @return
     */
    @Override
    public String copyFile(int srcFileId, int destFolderId) {
        String srcPath = getPath(srcFileId);
        String destPath = folderService.getPath(destFolderId);
        if (!"".equals(srcPath) && !"".equals(destPath)) {
            try {
                if (CF(srcFileId, destFolderId))
                    FileUtils.copyFileToDirectory(new File(srcPath), new File(destPath));
                else {
                    return "false";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "false";
            }
            return "true";
        }
        return "false";
    }

    public boolean CF(int srcFileId, int destFolderId) {
        Myfile myFile = fileMapper.selectById(srcFileId);
        HashMap<String, Object> sohm = new HashMap<>();
        sohm.put("folderId", destFolderId);
        sohm.put("fileName", myFile.getFileName());
        List<Myfile> myfiles = fileMapper.selectByMap(sohm);
        if (myfiles.size() == 0) {
            Myfile myNewFile = new Myfile(myFile.getFileName(), myFile.getFileSize(), myFile.getTime(), destFolderId, myFile.getUserId());
            fileMapper.insert(myNewFile);
            return true;
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param srcFileId
     * @return true/false
     */
    @Override
    public String deleteFile(int srcFileId) {
        String path = getPath(srcFileId);
        if (!"".equals(path)) {
            File file = new File(path);
            if (file.delete()) {
                fileMapper.deleteById(srcFileId);
                return "true";
            }
        }
        return "false";
    }

    public void mkFile(String srcFileName, double fileSize, int destFolderId) {
        Folder folder = folderMapper.selectById(destFolderId);
        Myfile newFile = new Myfile(srcFileName, fileSize, new Date(System.currentTimeMillis()), destFolderId, folder.getUserId());
        fileMapper.insert(newFile);
    }
}
