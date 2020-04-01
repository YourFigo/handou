package com.handou.item.mapper;

import com.handou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author Figo
 * @Date 2020/3/31 19:19
 */
public interface BrandMapper extends Mapper<Brand> {

    /**
     * 品牌和类名中间表的新增
     * @param cid
     * @param id
     */
    @Insert("INSERT INTO tb_category_brand (category_id,brand_id) VALUES (#{cid},#{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long id);
}
