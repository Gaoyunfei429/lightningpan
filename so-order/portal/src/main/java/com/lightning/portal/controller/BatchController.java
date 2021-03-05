package com.lightning.portal.controller;

import com.google.gson.Gson;
import com.lightning.portal.bean.Myfile;
import com.lightning.portal.bean.Folder;
import com.lightning.portal.service.BatchService;
import com.lightning.portal.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String getFoldersAndFiles(@RequestParam("userId") int userId, @RequestParam(value = "destFolderId", defaultValue = "-1") int destFolderId) {
        Map<String, Object> som=new HashMap<>();;
        Gson gson = new Gson();
        try {
            Map<String, Object> data = new HashMap<>();
            if (destFolderId == -1) {
                destFolderId = batchService.getRealFolderId(userId);
            }
            List<Folder> folders = batchService.getFoldersById(destFolderId);
            List<Myfile> files = batchService.getFilesById(destFolderId);
            data.put("folders", folders);
            data.put("files", files);
            som.put("code", 200);
            som.put("data", data);
            som.put("msg", "success");
            return gson.toJson(som);
        } catch (Exception e) {
            e.printStackTrace();
            som.put("code",500);
            som.put("data","false");
            som.put("msg","error");
            return gson.toJson(som);
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
        Map<String, Object> som=new HashMap<>();;
        Gson gson = new Gson();
        try {
            Map<String, Object> data = new HashMap<>();
            List<Folder> folders = batchService.getFoldersByUserIdAndName(userId, targetName);
            List<Myfile> files = batchService.getFilesByUserIdAndName(userId, targetName);
            data.put("folders", folders);
            data.put("files", files);
            som.put("code", 200);
            som.put("data", data);
            som.put("msg", "success");
            return gson.toJson(som);
        } catch (Exception e) {
            e.printStackTrace();
            som.put("code",500);
            som.put("data","false");
            som.put("msg","error");
            return gson.toJson(som);
        }
    }
    @GetMapping("/copyFilesAndFolders")
    public String copyFilesAndFolders(@RequestParam("srcFileIds") List srcFileIds,@RequestParam("srcFolderIds") List srcFolderIds,@RequestParam("destFolderId") int destFolderId){
        return Results.myResult(batchService.copyFilesAndFolders(srcFileIds,srcFolderIds,destFolderId));
    }
    @GetMapping("/moveFilesAndFolders")
    public String moveFilesAndFolders(@RequestParam("srcFileIds") List srcFileIds,@RequestParam("srcFolderIds") List srcFolderIds,@RequestParam("destFolderId") int destFolderId){
        return Results.myResult(batchService.moveFilesAndFolders(srcFileIds,srcFolderIds,destFolderId));
    }
    @GetMapping("/deleteFilesAndFolders")
    public String deleteFilesAndFolders(@RequestParam("srcFileIds") List srcFileIds,@RequestParam("srcFolderIds") List srcFolderIds){
        return Results.myResult(batchService.deleteFilesAndFolders(srcFileIds,srcFolderIds));
    }

}
