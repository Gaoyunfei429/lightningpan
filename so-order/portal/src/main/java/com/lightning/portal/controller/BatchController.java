package com.lightning.portal.controller;

import com.lightning.portal.bean.File;
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
//
//    文件列表     /getFoldersAndFiles        (userId,destFoderId)     userId: 用户Id，   destFoderId: 目标文件夹Id(请求主目录时不传值即可)
//    返回值：List<Map<String,String>>folders[],files[](map中保持文件(夹)Id，文件(夹)name，文件(夹）创建时间，文件大小)
//
//
//    文件搜索     /getFoldersOrFilesByName    (userId,targetName)      userId: 用户Id，   targetName： 需要查找的文件名
//    返回值：List<Map<String,String>>folders[],files[](map中保持文件(夹)Id，文件(夹)name，文件(夹）创建时间，文件大小)
//
//
//    多选批量删除 /deleteFoldersAndFiles     (srcFolderIds,srcFileIds)              srcFolderIds: 文件夹Id链表,  srcFileIds：文件Id链表                         true/false
//    全部删除     /deleteAll                 (userId)                               userId：用户Id                                                              true/false

    /**
     * 文件列表
     *
     * @param userId       用户Id
     * @param destFolderId 目标文件夹Id(请求主目录时不传值即可)
     * @return true/false
     */
    @GetMapping("/getFoldersAndFiles")
    public String getFoldersAndFiles(@RequestParam("userId") int userId, @RequestParam(value = "destFolderId", required = true, defaultValue = "-1") int destFolderId) {
        if (destFolderId == -1) {
            destFolderId = batchService.getRealFolderId(userId);
        }
        List<Folder> folders = batchService.getFoldersById(destFolderId);
        List<File> files = batchService.getFilesById(destFolderId);
        return "";
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
        return "";
    }
}
