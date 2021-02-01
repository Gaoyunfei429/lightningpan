package com.lightning.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lightning.portal.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author gyf
 * @Date 2021-01-31 20:42
 * @ClassName UserMapper
 * @Description
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
