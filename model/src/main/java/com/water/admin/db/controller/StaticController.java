package com.water.admin.db.controller;

import com.water.admin.db.domain.dto.DataResponse;
import com.water.admin.db.domain.dto.TableDataResponse;
import com.water.admin.utils.ObjectAndByte;
import com.water.admin.utils.SerializeUtil;
import com.water.admin.utils.cache.CacheManager;
import com.water.admin.utils.common.Constants;
import com.water.uubook.model.TbUbArticle;
import com.water.uubook.model.dto.TbUbArticleDto;
import com.water.uubook.service.TbUbArticleService;
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

    @Resource
    private TbUbArticleService articleService;


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
    public TableDataResponse findStaticModuleArticles(@RequestParam(defaultValue = "1") int draw, @RequestParam Integer module) {
        TableDataResponse response = new TableDataResponse();
        String redis_key = "static_index_module_%s";
        redis_key = String.format(redis_key, module);

        List<TbUbArticleDto> articleDtoList = new ArrayList<>();
        List<byte[]> byteValues = cacheManager.lrange(redis_key.getBytes(), 0, -1);
        for (byte[] byteValue : byteValues) {
            TbUbArticleDto articleDto = (TbUbArticleDto) SerializeUtil.unserialize(byteValue);
            articleDtoList.add(articleDto);
        }
        response.setDraw(draw);
        response.setiTotalRecords(20);
        response.setiTotalDisplayRecords(20);
        response.setAaData(articleDtoList);

        return response;
    }

    @RequestMapping(value = "/index/addStaticModuleArticles")
    public DataResponse addStaticModuleArticles(@RequestBody TbUbArticleDto model) {
        DataResponse response = new DataResponse();
        response.setCode(DataResponse.SUCCEED);
        if (model == null || model.getIds() == null || model.getIds().length == 0) {
            response.setCode(DataResponse.PARAMETER_ERROR);
            return response;
        }
        String redis_key = "static_index_module_%s";
        final String key = String.format(redis_key, model.getModule());
        List<TbUbArticleDto> articleList = articleService.findArticleListInIds(new String[]{"id", "title", "picUrl", "createOn"}, model.getIds());
        putArticleToRedis(key, articleList);

        return response;
    }

    @RequestMapping(value = "/index/delModuleArticle/{module}/{articleId}")
    public DataResponse delModuleArticle(@PathVariable Integer module, @PathVariable Integer articleId) {
        DataResponse response = new DataResponse();
        response.setCode(DataResponse.SUCCEED);
        String redis_key = "static_index_module_%s";
        final String key = String.format(redis_key, module);

        return response;
    }


    private void putArticleToRedis(String key, List<TbUbArticleDto> articleList) {
        articleList.stream().forEach(p -> {
            byte[] value = SerializeUtil.serialize(p);
            cacheManager.lrem(key.getBytes(), value);
            cacheManager.rpush(key, value);
            cacheManager.ltrim(key, 0, 20);
        });
    }
}
