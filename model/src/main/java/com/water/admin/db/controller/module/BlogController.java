package com.water.admin.db.controller.module;

import com.alibaba.druid.support.console.TabledDataPrinter;
import com.water.admin.db.domain.dto.TableDataResponse;
import com.water.uubook.model.dto.ArticleDto;
import com.water.uubook.service.ArticleService;
import com.water.uubook.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mrwater on 2017/8/23.
 */
@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/module/blog/home");
        return mav;
    }

    @RequestMapping(value = "/getArticleByPage")
    public TableDataResponse getArticleByPage(ArticleDto model,
                                              @RequestParam(defaultValue = "1") int currentPage,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        TableDataResponse response = new TableDataResponse();
        model.setModule(0);
        List<ArticleDto> articleDtoList = articleService.findArticleListByCondition(model, new String[]{"id","title"}, currentPage, pageSize);
//        int total = articleService.countArticleTotal(model);
        int total = 100;
        response.setiTotalRecords(total);
        response.setiTotalDisplayRecords(total);
        response.setAaData(articleDtoList);
        return response;
    }


}
