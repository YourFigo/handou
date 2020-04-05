package com.handou.item.mapper;

import com.handou.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Figo
 * @Date 2020/3/30 14:17
 */
public interface CategoryMapper extends Mapper<Category> {

    /**
     * 根据品牌id查询种类信息
     * @param bid
     * @return
     */
    @Select("SELECT * FROM tb_brand WHERE id IN (SELECT brand_id FROM tb_category_brand WHERE brand_id = #{bid})")
    List<Category> queryCategoryByBrandId(String bid);
}
