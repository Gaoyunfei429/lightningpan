package com.lightning.portal.controller;

import com.lightning.portal.bean.Myfile;
import com.lightning.portal.bean.Folder;
import com.lightning.portal.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author gyf
 * @Date 2021-02-01 15:43
 * @ClassName BatchController
 * @Description
 */
@RestController
public class BatchController {
    @Autowired
    BatchService batchService;

    /**
     * 文件列表
     *
     * @param userId       用户Id
     * @param destFolderId 目标文件夹Id(请求主目录时不传值即可)
     * @return true/false
     */
    @GetMapping("/getFoldersAndFiles")
    public String getFoldersAndFiles(@RequestParam("userId") int userId, @RequestParam(value = "destFolderId", required = true, defaultValue = "-1") int destFolderId) {
        try {
            if (destFolderId == -1) {
                destFolderId = batchService.getRealFolderId(userId);
            }
            List<Folder> folders = batchService.getFoldersById(destFolderId);
            List<Myfile> files = batchService.getFilesById(destFolderId);
            return folders + files.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * 文件搜索
     *
     * @param userId     用户Id
     * @param targetName 需要查找的文件名
     * @return true/false
     */
    @GetMapping("/getFoldersOrFilesByName")
    public String getFilesByName(@RequestParam("userId") int userId, @RequestParam("targetName") String targetName) {
        try {
            List<Folder> folders = batchService.getFoldersByUserIdAndName(userId,targetName);
            List<Myfile> files = batchService.getFilesByUserIdAndName(userId,targetName);
            return folders + files.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
