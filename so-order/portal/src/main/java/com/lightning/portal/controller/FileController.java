package com.lightning.portal.controller;

import com.google.gson.Gson;
import com.lightning.portal.service.FileService;
import com.lightning.portal.service.impl.FolderServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    FolderServiceImpl folderService;

    /**
     * 修改文件名
     *
     * @param srcFileId
     * @param newName
     * @return
     */
    @GetMapping("/reFileName")
    public String reName(@RequestParam("srcFileId") int srcFileId, @RequestParam("newName") String newName) {
        return myResult(fileService.reName(srcFileId, newName));
    }

    /**
     * 文件上传
     *
     * @param mpfs         文件
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("destFolderId") int destFolderId, @RequestPart("mpfs") MultipartFile[] mpfs) {
        return myResult(fileService.uploadFile(destFolderId, mpfs));
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
        return myResult(fileService.moveFile(srcFileId, destFolderId));
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
        return myResult(fileService.copyFile(srcFileId, destFolderId));
    }

    /**
     * 文件删除
     *
     * @param srcFileId 原文件Id
     * @return true/false
     */
    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam("srcFileId") int srcFileId) {
        return myResult(fileService.deleteFile(srcFileId));
    }


    /**
     * 文件下载
     *
     * @param srcFileId 原文件Id
     * @return true/false
     */
    @GetMapping("/downloadFile")
    public String downloadFile(@RequestParam("srcFileId") int srcFileId, HttpServletRequest request, HttpServletResponse response) {
        String path = fileService.getPath(srcFileId);
        File file = new File(path);
        if (file.exists()) {
            BufferedInputStream bis = null;
            ServletOutputStream os = null;
            try {
                String mimeType = request.getServletContext().getMimeType(path);
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
                FileInputStream fis = new FileInputStream(new File(path));
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                IOUtils.copy(bis, os);
                return myResult("true");
            } catch (IOException e) {
                e.printStackTrace();
                return myResult("false");
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return myResult("false");
                    }
                }
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return myResult("false");
                    }
                }

            }
        }
        return myResult("false");
    }
    public static String myResult(String boo){
        Map<String, String> ssm = new HashMap<>();
        Gson gson = new Gson();
        if ("true".equals(boo)) {
            ssm.put("code", "200");
            ssm.put("msg", "success");
        } else {
            ssm.put("code", "500");
            ssm.put("msg", "error");
        }
        String s = gson.toJson(ssm);
        return s;
    }
}
