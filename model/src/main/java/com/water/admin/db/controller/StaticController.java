package com.water.admin.db.controller;

import com.water.admin.utils.cache.CacheManager;
import com.water.admin.utils.common.Constants;
import com.water.uubook.model.Article;
import com.water.uubook.model.dto.ArticleDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by mrwater on 2017/8/29.
 */
@RestController
@RequestMapping(value = "/static")
public class StaticController {

    @Resource
    private CacheManager cacheManager;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        List<Map<String, Object>> moduleList = new ArrayList<>();
        Arrays.asList(Constants.STATIC_INDEX.values()).stream().forEach(p -> {
            Map<String, Object> module = new HashMap<>();
            module.put("id", p.getIndex());
            module.put("name", p.getName());
            moduleList.add(module);
        });
        mav.addObject("moduleList", moduleList);
        mav.setViewName("/static/index");

        return mav;
    }

    @RequestMapping(value = "/index/findArticles")
    public List<ArticleDto> findStaticModuleArticles(@RequestParam Integer module) {
        String redis_key = "static_index_module_%s";
        redis_key = String.format(redis_key, module);
        List<ArticleDto> articleDtoList = cacheManager.getList(redis_key, ArticleDto.class);

        return articleDtoList;
    }
}
