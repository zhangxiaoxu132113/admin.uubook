package com.water.admin.db.controller.module;

import com.water.uubook.model.dto.TbUbArticleDto;
import com.water.uubook.model.dto.TbUbCategoryDto;
import com.water.uubook.service.TbUbArticleService;
import com.water.uubook.service.TbUbCategoryService;
import com.water.uubook.utils.Constants;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private TbUbArticleService articleService;

    @Resource
    private TbUbCategoryService categoryService;

    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        List<TbUbCategoryDto> allCategories = categoryService.getAllParentCategories();
        List<Map<String, Object>> allModule = this.getAllModule();

        mav.addObject("allModule", allModule);
        mav.addObject("allCategories", allCategories);
        mav.setViewName("/module/blog/home");
        return mav;
    }

    @RequestMapping(value = "/edit_view/{articleId}", method = RequestMethod.GET)
    public ModelAndView editView(@PathVariable Integer articleId) {
        ModelAndView mav = new ModelAndView();
        TbUbArticleDto articleDto = articleService.findArticleById(articleId);
        List<TbUbCategoryDto> allCategories = categoryService.getAllParentCategories();
        List<Map<String, Object>> allModule = this.getAllModule();

        mav.addObject("article", articleDto);
        mav.addObject("allModule", allModule);
        mav.addObject("allCategories", allCategories);
        mav.setViewName("/module/blog/dialog/edit_article");
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
