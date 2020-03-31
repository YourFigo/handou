package com.handou.item.service;

import com.handou.item.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Figo
 * @Date 2020/3/31 19:19
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


}
