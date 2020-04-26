package me.nic.sys.service.impl;

import me.nic.sys.entity.Role;
import me.nic.sys.mapper.RoleMapper;
import me.nic.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author nicahead@gmail.com
 * @since 2020-04-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
