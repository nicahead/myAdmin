package me.nic.sys.service.impl;

import me.nic.sys.entity.Log;
import me.nic.sys.mapper.LogMapper;
import me.nic.sys.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author nicahead@gmail.com
 * @since 2020-04-26
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
