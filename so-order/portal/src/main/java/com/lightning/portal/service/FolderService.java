package com.lightning.portal.service;

/**
 * @Author gyf
 * @Date 2021-02-01 16:01
 * @ClassName folderService
 * @Description
 */
public interface FolderService {
    String makeDir(String srcFoldername, int destFolderId);

    String moveFolder(int srcFolderId, int destFolderId);

    String copyFolder(int srcFolderId, int destFolderId);

    String deleteFolder(int srcFolderId);
}
