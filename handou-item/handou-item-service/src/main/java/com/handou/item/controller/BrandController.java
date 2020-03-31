package com.handou.item.controller;

import com.handou.common.pojo.PageResult;
import com.handou.item.pojo.Brand;
import com.handou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Figo
 * @Date 2020/3/31 19:20
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 根据查询条件分页查询并排序品牌信息
     * @param key  搜索条件
     * @param page  当前页
     * @param rows  每页大小
     * @param sortBy  排序字段
     * @param desc  是否降序
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        PageResult<Brand> result = this.brandService.queryBrandByPage(key,page,rows,sortBy,desc);
        if (result == null || CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}