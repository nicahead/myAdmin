package me.nic.sys.service.impl;

import me.nic.sys.entity.User;
import me.nic.sys.mapper.UserMapper;
import me.nic.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author nicahead@gmail.com
 * @since 2020-04-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
