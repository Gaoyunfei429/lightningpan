package com.lightning.portal.controller;

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
     * @param srcFileId
     * @param newName
     * @return
     */
    @GetMapping("/reFileName")
    public String reName(@RequestParam("srcFileId") int srcFileId, @RequestParam("newName") String newName) {
        return fileService.reName(srcFileId, newName);

    }

    /**
     * 文件上传
     *
     * @param mpfs  文件
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("destFolderId") int destFolderId, @RequestPart("mpfs") MultipartFile[] mpfs) {
        String result = fileService.uploadFile(destFolderId,mpfs);
        return result;
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
    @GetMapping("/downloadFile")
    public String downloadFile(@RequestParam("srcFileId") int srcFileId, HttpServletRequest request,HttpServletResponse response) {
        String path = fileService.getPath(srcFileId);
        File file = new File(path);
        if (file.exists()) {
            BufferedInputStream bis = null;
            ServletOutputStream os = null;
            try {
                String mimeType = request.getServletContext().getMimeType(path);
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition","attachment;filename="+file.getName());
                FileInputStream fis = new FileInputStream(new File(path));
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                IOUtils.copy(bis,os);
                return "true";
            } catch (IOException e) {
                e.printStackTrace();
                return "false";
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "false";
                    }
                }
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "false";
                    }
                }

            }
        }
        return "false";
    }
}
