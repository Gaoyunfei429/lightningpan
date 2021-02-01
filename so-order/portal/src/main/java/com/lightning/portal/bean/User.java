package com.lightning.portal.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author gyf
 * @Date 2021-01-31 20:49
 * @ClassName User
 * @Description
 */
@Data
public class User {
    @TableId
    private int userId;
    private String username;
    private String password;
}
