package com.lightning.portal.bean;


import com.baomidou.mybatisplus.annotation.TableId;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Folder {

  @TableId
  private int folderId;
  private String folderName;
  private Date time;
  private int parentId;
  private int userId;

  public Folder(String folderName, Date time, int parentId, int userId) {
    this.folderName = folderName;
    this.time = time;
    this.parentId = parentId;
    this.userId = userId;
  }
}
