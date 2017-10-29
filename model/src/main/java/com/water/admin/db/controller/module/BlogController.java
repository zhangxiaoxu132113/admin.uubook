package com.water.admin.db.controller.module;

import com.alibaba.druid.support.console.TabledDataPrinter;
import com.water.admin.db.domain.dto.TableDataResponse;
import com.water.uubook.model.dto.ArticleDto;
import com.water.uubook.model.dto.CategoryDto;
import com.water.uubook.service.ArticleService;
import com.water.uubook.service.CategoryService;
import com.water.uubook.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mrwater on 2017/8/23.
 */
@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        List<CategoryDto> allCategories = categoryService.getAllParentCategories();
        List<Map<String, Object>> allModule = this.getAllModule();

        mav.addObject("allModule", allModule);
        mav.addObject("allCategories", allCategories);
        mav.setViewName("/module/blog/home");
        return mav;
    }

    private List<Map<String, Object>> getAllModule() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        for (Map.Entry entry : Constants.ARTICLE_MODULE_MAP.entrySet()) {
            map = new HashMap<>();
            map.put("label", entry.getValue());
            map.put("value", entry.getKey());
            list.add(map);
        }
        return list;
    }




}
