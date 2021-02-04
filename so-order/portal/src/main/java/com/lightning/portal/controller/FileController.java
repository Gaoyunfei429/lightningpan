package com.lightning.portal.controller;

import com.lightning.portal.bean.PathUtils;
import com.lightning.portal.service.FileService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
     * @param srcFileName  原文件名
     * @param destFolderId 目标文件夹Id
     * @return true/false
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("srcFileName") String srcFileName, @RequestParam("destFolderId") int destFolderId, HttpServletRequest request, HttpServletResponse response) {
        return PathUtils.BASEPATH;
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
    public String downloadFile(@RequestParam("srcFileId") int srcFileId, HttpServletResponse response) {
        String path = fileService.getPath(srcFileId);
        if (!"".equals(path)) {
            BufferedInputStream bis = null;
            ServletOutputStream os = null;
            try {
                FileInputStream fis = new FileInputStream(new File(path));
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                byte[] bytes = new byte[1024];
                int len;
                while ((len = bis.read(bytes)) != -1) {
                    os.write(bytes, 0, len);
                }
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
