package com.deslre.demospringbootstarter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deslre.demospringbootstarter.entity.TblBook;
import com.deslre.demospringbootstarter.mapper.TblBookMapper;
import com.deslre.demospringbootstarter.service.ITblBookService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-08
 */
@Service
public class TblBookServiceImpl extends ServiceImpl<TblBookMapper, TblBook> implements ITblBookService {
    public void S(){
        System.out.println("log = " + log);
        System.out.println("log = " + log);
        System.out.println("log = " + log);
        System.out.println("log = " + log);
        System.out.println("log = " + log);
        System.out.println("log = " + log);
        System.out.println("log = " + log);
    }
}
