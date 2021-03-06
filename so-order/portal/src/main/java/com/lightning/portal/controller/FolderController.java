package com.lightning.portal.controller;

import com.lightning.portal.service.FolderService;
import com.lightning.portal.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 修改文件名
     * @param srcFolderId
     * @param newName
     * @return
     */
    @GetMapping("/reFolderName")
    public String reName(@RequestParam("srcFolderId") int srcFolderId, @RequestParam("newName") String newName) {
        return Results.myResult(folderService.reName(srcFolderId, newName));

    }
    /**
     * 创建文件夹
     *
     * @param srcFolderName 创建的文件夹名
     * @param destFolderId  目标文件夹Id
     * @return true/false
     */
    @GetMapping("/makeDir")
    public String makeDir(@RequestParam("srcFolderName") String srcFolderName, @RequestParam("destFolderId") int destFolderId) {
        return Results.myResult(folderService.makeDir(srcFolderName, destFolderId));
    }

    /**
     * 移动文件夹
     *
     * @param srcFolderId  原文件夹Id
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @Deprecated
    @GetMapping("/moveFolder")
    public String moveFolder(@RequestParam("srcFolderId") int srcFolderId, @RequestParam("destFolderId") int destFolderId) {
        return Results.myResult(folderService.moveFolder(srcFolderId, destFolderId));
    }

    /**
     * 复制文件夹
     *
     * @param srcFolderId  原文件夹Id
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @Deprecated
    @GetMapping("/copyFolder")
    public String copyFolder(@RequestParam("srcFolderId") int srcFolderId, @RequestParam("destFolderId") int destFolderId) {
        return Results.myResult(folderService.copyFolder(srcFolderId, destFolderId));
    }

    /**
     * 删除文件夹
     *
     * @param srcFolderId 原文件夹Id
     * @return true/false
     */
    @Deprecated
    @GetMapping("/deleteFolder")
    public String deleteFolder(@RequestParam("srcFolderId") int srcFolderId) {
        return Results.myResult(folderService.deleteFolder(srcFolderId));
    }
}
