package com.lightning.portal.service;

import com.lightning.portal.bean.File;
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
    List<File> getFilesById(int destFolderId);

    List<Folder> getFoldersById(int destFolderId);

    int getRealFolderId(int userId);
}