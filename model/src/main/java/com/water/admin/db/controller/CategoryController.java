package com.water.admin.db.controller;

import com.water.admin.db.domain.dto.TableDataResponse;
import com.water.uubook.model.dto.TbUbCategoryDto;
import com.water.uubook.service.TbUbCategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by mrwater on 2017/8/28.
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Resource
    private TbUbCategoryService categoryService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        List<TbUbCategoryDto> allCategories = categoryService.getAllParentCategories();

        mav.addObject("allCategories", allCategories);
        mav.setViewName("/category/home");

        return mav;
    }

    @RequestMapping(value = "/findCategoryByCondition", method = RequestMethod.POST)
    public TableDataResponse findCategoryByCondition(TbUbCategoryDto model,
                                                     @RequestParam(defaultValue = "1") int draw,
                                                     @RequestParam(defaultValue = "1") int currentPage,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        TableDataResponse response = new TableDataResponse();
        List<TbUbCategoryDto> categoryDtoList = categoryService.getCategoryByCondition(model, null, currentPage, pageSize);
        int total = categoryService.countCategoryTotal(model);

        response.setDraw(draw);
        response.setiTotalRecords(total);
        response.setiTotalDisplayRecords(total);
        response.setAaData(categoryDtoList);
        return response;
    }

    @RequestMapping(value = "/del/{categoryId}")
    public String delCategoryById(@PathVariable Integer categoryId) {
        categoryService.delCategoryById(categoryId);
        return "";
    }

    @RequestMapping(value = "/stopOrStart")
    public String stopOrStart(@RequestBody TbUbCategoryDto model) {
        model.setCreateTime(new Date());
        categoryService.updateCategory(model);
        return "";
    }
}
