package com.lightning.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author gyf
 * @Date 2021-02-01 00:16
 * @ClassName FileService
 * @Description
 */
public interface FileService {
    String moveFile(int srcFileId, int destFolderId);

    String copyFile(int srcFileId, int destFolderId);

    String deleteFile(int srcFileId);
    String getPath(int srcFileId);

    String reName(int srcFileId, String newName);

    String uploadFile(int destFolderId, MultipartFile[] mpfs);
}
