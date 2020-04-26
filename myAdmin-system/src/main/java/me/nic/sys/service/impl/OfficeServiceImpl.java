package me.nic.sys.service.impl;

import me.nic.sys.entity.Office;
import me.nic.sys.mapper.OfficeMapper;
import me.nic.sys.service.IOfficeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author nicahead@gmail.com
 * @since 2020-04-26
 */
@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeMapper, Office> implements IOfficeService {

}
