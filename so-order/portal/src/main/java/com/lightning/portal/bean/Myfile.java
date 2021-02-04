package com.lightning.portal.bean;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Myfile {

    @TableId
    private int fileId;
    private String fileName;
    private double fileSize;
    private Date time;
    private int folderId;
    private int userId;

    public Myfile(String fileName, double fileSize, Date time, int folderId, int userId) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.time = time;
        this.folderId = folderId;
        this.userId = userId;
    }
}
