package com.lightning.portal.controller;

import com.lightning.portal.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author gyf
 * @Date 2021-02-01 15:42
 * @ClassName FolderController
 * @Description
 */
@RestController
public class FolderController {
    @Autowired
    FolderService folderService;

    /**
     * 创建文件夹
     *
     * @param srcFolderName 创建的文件夹名
     * @param destFolderId  目标文件夹Id
     * @return true/false
     */
    @GetMapping("/makeDir")
    public String makeDir(@RequestParam("srcFolderName") String srcFolderName, @RequestParam("destFolderId") int destFolderId) {
        String result = folderService.makeDir(srcFolderName, destFolderId);
        return result;
    }

    /**
     * 移动文件夹
     *
     * @param srcFolderId  原文件夹Id
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @GetMapping("/moveFolder")
    public String moveFolder(@RequestParam("srcFolderId") int srcFolderId, @RequestParam("destFolderId") int destFolderId) {
        String result = folderService.moveFolder(srcFolderId, destFolderId);
        return result;
    }

    /**
     * 复制文件夹
     *
     * @param srcFolderId  原文件夹Id
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @GetMapping("/copyFolder")
    public String copyFolder(@RequestParam("srcFolderId") int srcFolderId, @RequestParam("destFolderId") int destFolderId) {
        String result = folderService.copyFolder(srcFolderId, destFolderId);
        return result;
    }

    /**
     * 删除文件夹
     *
     * @param srcFolderId 原文件夹Id
     * @return true/false
     */
    @GetMapping("/deleteFolder")
    public String deleteFolder(@RequestParam("srcFolderId") int srcFolderId) {
        String result = folderService.deleteFolder(srcFolderId);
        return result;
    }
}
