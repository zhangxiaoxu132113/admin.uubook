package com.water.admin.db.controller;

import com.water.admin.db.domain.dto.TableDataResponse;
import com.water.uubook.model.TbUbArticle;
import com.water.uubook.model.dto.TbUbArticleDto;
import com.water.uubook.service.TbUbArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mrwater on 2017/8/27.
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private TbUbArticleService articleService;

    @RequestMapping(value = "/del/{articleId}", method = RequestMethod.POST)
    public String delArticle(@PathVariable Integer articleId) {
        articleService.delArticleById(articleId);
        return "dfd";
    }

    @RequestMapping(value = "/stopOrStart", method = RequestMethod.POST)
    public TbUbArticleDto stopOrStart(@RequestBody TbUbArticleDto model) {
        model.setCreateOn(System.currentTimeMillis());
        articleService.updateArticle(model);
        return model;
    }

    @RequestMapping(value = "/getArticleByPage")
    public TableDataResponse getArticleByPage(TbUbArticleDto model,
                                              @RequestParam(defaultValue = "1") int draw,
                                              @RequestParam(defaultValue = "1") int currentPage,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        TableDataResponse response = new TableDataResponse();
        String[] cols = new String[]{"id", "title", "viewHits","picUrl", "enable", "createOn", "category", "module"};
        List<TbUbArticleDto> articleDtoList = articleService.findArticleListByCondition(model, cols, null, currentPage, pageSize);
        int total = articleService.countArticleTotal(model);

        response.setDraw(draw);
        response.setiTotalRecords(total);
        response.setiTotalDisplayRecords(total);
        response.setAaData(articleDtoList);
        return response;
    }
}
