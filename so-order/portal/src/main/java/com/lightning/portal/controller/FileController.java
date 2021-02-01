package com.lightning.portal.controller;

import com.lightning.portal.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author gyf
 * @Date 2021-02-01 00:05
 * @ClassName FileController
 * @Description
 */
@RestController
public class FileController {
    @Autowired
    FileService fileService;

    /**
     * 文件上传
     *
     * @param srcFileName  原文件名
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("srcFileName") String srcFileName, @RequestParam("destFolderId") int destFolderId) {
        return "result";
    }

    /**
     * 移动文件
     *
     * @param srcFileId    原文件Id
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @GetMapping("/moveFile")
    public String moveFile(@RequestParam("srcFileId") int srcFileId, @RequestParam("destFolderId") int destFolderId) {
        String result = fileService.moveFile(srcFileId, destFolderId);
        return result;
    }


    /**
     * 文件复制
     *
     * @param srcFileId    原文件Id
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @GetMapping("/copyFile")
    public String copyFile(@RequestParam("srcFileId") int srcFileId, @RequestParam("destFolderId") int destFolderId) {
        String result = fileService.copyFile(srcFileId, destFolderId);
        return result;
    }

    /**
     * 文件删除
     *
     * @param srcFileId 原文件Id
     * @return true/false
     */
    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam("srcFileId") int srcFileId) {
        String result = fileService.deleteFile(srcFileId);
        return result;
    }


    /**
     * 文件下载
     *
     * @param srcFileId 原文件Id
     * @return true/false
     */
    @PostMapping("/downloadFile")
    public String downloadFile(@RequestParam("srcFileId") int srcFileId) {
        return "";
    }
}
