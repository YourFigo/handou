package com.handou.item.service;

import com.handou.item.mapper.CategoryMapper;
import com.handou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Figo
 * @Date 2020/3/30 14:42
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点的id查询子节点
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }

    /**
     * 根据品牌id查询种类信息
     * @param bid
     * @return
     */
    public List<Category> queryCategoryByBrandId(Long bid) {
        return this.categoryMapper.queryCategoryByBrandId(bid);
    }
}
