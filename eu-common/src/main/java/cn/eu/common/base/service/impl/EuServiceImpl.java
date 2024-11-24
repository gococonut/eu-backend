package cn.eu.common.base.service.impl;

import cn.eu.common.base.service.IEuService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.data.domain.Pageable;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public abstract class EuServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IEuService<T> {

    /**
     * 分页参数从1开始
     */
    protected void getPage(Pageable pageable) {
        getPage(pageable, true);
    }

    /**
     * 分页参数从1开始
     */
    protected void getPage(Pageable pageable, boolean isOrder) {
        if (isOrder) {
            String order = null;
            if (pageable.getSort() != null) {
                order = pageable.getSort().toString();
                order = order.replace(":", " ");
                if ("UNSORTED".equals(order)) {
                    PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
                    return;
                }
            }
            PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(), order);
        } else {
            PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        }
    }
}
