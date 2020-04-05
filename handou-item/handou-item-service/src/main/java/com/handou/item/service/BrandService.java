package com.handou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.handou.common.pojo.PageResult;
import com.handou.item.mapper.BrandMapper;
import com.handou.item.mapper.CategoryMapper;
import com.handou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author Figo
 * @Date 2020/3/31 19:19
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据查询条件分页查询并排序品牌信息
     * @param key  搜索条件
     * @param page  当前页
     * @param rows  每页大小
     * @param sortBy  排序字段
     * @param desc  是否降序
     * @return
     */
    public PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {

        // 初始化一个example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        // 根据name模糊查询，或者根据首字母查询
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("name","%" + key + "%").orEqualTo("letter",key);
        }

        // 添加分页条件
        PageHelper.startPage(page,rows);

        // 添加排序条件
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy + " " + (desc?"desc":"asc"));
        }

        List<Brand> brands = this.brandMapper.selectByExample(example);
        // 包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        // 包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids
     * @return
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        // 先新增Brand
        Boolean flag = this.brandMapper.insertSelective(brand) == 1;
        // 后新增中间表
        if (flag){
            cids.forEach(cid ->{
                this.brandMapper.insertCategoryAndBrand(cid, brand.getId());
            });
        }
    }

    @Transactional
    public void deleteBrand(Long bid) {
        int categorySize = this.categoryMapper.queryCategoryByBrandId(bid).size();
        Boolean flag = this.brandMapper.deleteCategoryAndBrand(bid) == categorySize;
        if (flag){
            this.brandMapper.deleteByPrimaryKey(bid);
        }
    }
}
