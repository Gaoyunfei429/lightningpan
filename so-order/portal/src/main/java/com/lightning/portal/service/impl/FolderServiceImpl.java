package com.lightning.portal.service.impl;

import com.lightning.portal.bean.Folder;
import com.lightning.portal.bean.PathUtils;
import com.lightning.portal.mapper.FolderMapper;
import com.lightning.portal.service.FolderService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * 创建文件夹
     */
    @Override
    public String makeDir(String srcFolderName, int destFolderId) {
        String result = "false";
        String path = getPath(destFolderId);
        if (!"".equals(path)) {
            File file = new File(path + "\\" + srcFolderName);
            if (!file.exists()) {
                file.mkdir();
                result = mkDir(srcFolderName, destFolderId);
                return result;
            }
            return result;
        }
        return result;
    }

    /**
     * 移动文件夹
     *
     * @param srcFolderId
     * @param destFolderId
     * @return true/false
     */
    @Override
    public String moveFolder(int srcFolderId, int destFolderId) {
        String srcPath = getPath(srcFolderId);
        String destPath = getPath(destFolderId);
        if (!"".equals(srcPath) && !"".equals(destPath)) {
            try {
                FileUtils.moveDirectoryToDirectory(new File(srcPath), new File(destPath), false);
                changeParentId(srcFolderId, destFolderId);
            } catch (IOException e) {
                e.printStackTrace();
                return "false";
            }
            return "true";
        }
        return "false";
    }

    private void changeParentId(int srcFolderId, int destFolderId) {
        Folder folder = folderMapper.selectById(srcFolderId);
        folder.setParentId(destFolderId);
        folderMapper.updateById(folder);
    }

    /**
     * 复制文件夹
     *
     * @param srcFolderId  原文件夹Id
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @Override
    public String copyFolder(int srcFolderId, int destFolderId) {
        String srcPath = getPath(srcFolderId);
        String destPath = getPath(destFolderId);
        if (!"".equals(srcPath) && !"".equals(destPath)) {
            try {
                if (CF(srcFolderId, destFolderId))
                    FileUtils.copyDirectoryToDirectory(new File(srcPath), new File(destPath));
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

    private boolean CF(int srcFolderId, int destFolderId) {
        Folder folder = folderMapper.selectById(srcFolderId);
        HashMap<String, Object> sohm = new HashMap<>();
        sohm.put("parentId", destFolderId);
        sohm.put("folderName", folder.getFolderName());
        List<Folder> folders = folderMapper.selectByMap(sohm);
        if (folders.size() == 0) {
            Folder newFolder = new Folder(folder.getFolderName(), folder.getTime(), destFolderId, folder.getUserId());
            folderMapper.insert(newFolder);
            return true;
        }
        return false;
    }

    /**
     * 删除文件夹
     *
     * @param srcFolderId
     * @return true/false
     */
    @Override
    public String deleteFolder(int srcFolderId) {
        String path = getPath(srcFolderId);
        if (!"".equals(path)) {
            File file = new File(path);
            try {
                FileUtils.deleteDirectory(file);
                removeFolder(srcFolderId);
            } catch (IOException e) {
                return "false";
            }
            return "true";
        }
        return "false";
    }

    @Override
    public String reName(int srcFolderId, String newName) {
        try {
            String path = getPath(srcFolderId);
            File file = new File(path);
            String parent = file.getParent();
            File newFile = new File(parent + "\\" + newName);
            if (!newFile.exists()) {
                file.renameTo(newFile);
                folderMapper.updateNameById(srcFolderId, newName);
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    private void removeFolder(int srcFolderId) {
        Folder folder = folderMapper.selectById(srcFolderId);
        if (folder != null) {
            List<Integer> parentIds = new ArrayList<>();
            List<Integer> sonIds = new ArrayList<>();
            parentIds.add(folder.getFolderId());
            List<Integer> folderIds;
            while (parentIds.size() != 0) {
                for (Integer parentId : parentIds) {
                    folderMapper.deleteById(parentId);
                    folderIds = folderMapper.selectIdsByParentId(parentId);
                    sonIds.addAll(folderIds);
                }
                parentIds.clear();
                parentIds.addAll(sonIds);
                sonIds.clear();
            }
        }
    }

    /**
     * 获得父文件夹路径
     *
     * @param destFolderId 父文件夹Id
     * @return 返回父文件夹路径
     */
    public String getPath(int destFolderId) {
        Folder folder = folderMapper.selectById(destFolderId);
        if (folder != null) {
            StringBuilder path = new StringBuilder();
            while (folder.getParentId() != -1) {
                path.insert(0, "\\" + folder.getFolderName());
                folder = folderMapper.selectById(folder.getParentId());
            }
            path.insert(0, PathUtils.BASEPATH + "\\" + folder.getFolderName());
            return path.toString();
        }
        return "";
    }

    /**
     * 创建文件夹与update表
     *
     * @param srcFolderName 创建文件夹名
     * @param destFolderId  parentId
     */
    public String mkDir(String srcFolderName, int destFolderId) {
        Folder folder = folderMapper.selectById(destFolderId);
        Folder newFolder = new Folder(srcFolderName, new Date(System.currentTimeMillis()), destFolderId, folder.getUserId());
        folderMapper.insert(newFolder);
        return "true";
    }
}
