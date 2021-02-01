package com.lightning.portal.service.impl;

import com.lightning.portal.mapper.FileMapper;
import com.lightning.portal.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author gyf
 * @Date 2021-02-01 00:16
 * @ClassName FileServiceImpl
 * @Description
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;

    @Override
    public String moveFile(int srcFileId, int destFolderId) {
        String result = "false";

        return result;
    }

    @Override
    public String copyFile(int srcFileId, int destFolderId) {
        String result = "false";

        return result;
    }

    @Override
    public String deleteFile(int srcFileId) {
        String result = "false";

        return result;
    }
}
