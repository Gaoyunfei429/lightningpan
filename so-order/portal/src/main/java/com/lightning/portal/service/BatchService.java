package com.lightning.portal.service;

import com.lightning.portal.bean.Myfile;
import com.lightning.portal.bean.Folder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author gyf
 * @Date 2021-02-01 16:32
 * @ClassName FolderAndFileService
 * @Description
 */
@Service
public interface BatchService {
    List<Myfile> getFilesById(int destFolderId);

    List<Folder> getFoldersById(int destFolderId);

    int getRealFolderId(int userId);

    List<Folder> getFoldersByUserIdAndName(int userId, String targetName);


    List<Myfile> getFilesByUserIdAndName(int userId, String fileName);
}