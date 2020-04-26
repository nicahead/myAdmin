package me.nic.sys.service.impl;

import me.nic.sys.entity.UserRole;
import me.nic.sys.mapper.UserRoleMapper;
import me.nic.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色 服务实现类
 * </p>
 *
 * @author nicahead@gmail.com
 * @since 2020-04-26
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
