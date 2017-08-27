package com.water.admin.db.controller;

import com.water.uubook.model.Article;
import com.water.uubook.model.dto.ArticleDto;
import com.water.uubook.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by mrwater on 2017/8/27.
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/del/{articleId}", method = RequestMethod.POST)
    public String delArticle(@PathVariable Integer articleId) {
        articleService.delArticleById(articleId);
        return "dfd";
    }

    @RequestMapping(value = "/stopOrStart", method = RequestMethod.POST)
    public ArticleDto stopOrStart(@RequestBody ArticleDto model) {
        model.setCreateOn(System.currentTimeMillis());
        articleService.updateArticle(model);
        return model;
    }
}
