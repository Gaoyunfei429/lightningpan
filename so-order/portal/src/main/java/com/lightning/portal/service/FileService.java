package com.lightning.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;

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
}
