package org.example.mysteryboxlearn.user.root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.mysteryboxlearn.user.root.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
