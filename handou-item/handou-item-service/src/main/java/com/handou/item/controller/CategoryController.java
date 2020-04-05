package com.handou.item.controller;

import com.handou.item.pojo.Category;
import com.handou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Figo
 * @Date 2020/3/30 14:46
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点的id查询子节点
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0")Long pid){

        // 400：参数不合法
        if (pid == null || pid < 0){
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories =  this.categoryService.queryCategoriesByPid(pid);
        // if (categories == null || categories.size() == 0)
        // 404：资源未找到
        if (CollectionUtils.isEmpty(categories)){
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }
        // 200：查询成功
        return ResponseEntity.ok(categories);
    }

    /**
     * 根据品牌id查询种类信息
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBrandId(@PathVariable String bid){
        List<Category> list = this.categoryService.queryCategoryByBrandId(bid);
        if (list == null || list.size() < 1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
}
