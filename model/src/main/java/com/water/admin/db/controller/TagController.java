package com.water.admin.db.controller;

import com.water.admin.db.domain.dto.TableDataResponse;
import com.water.uubook.model.dto.TbUbCategoryDto;
import com.water.uubook.model.dto.TbUbTagDto;
import com.water.uubook.service.TbUbCategoryService;
import com.water.uubook.service.TbUbTagService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by mrwater on 2017/8/28.
 */
@RestController
@RequestMapping(value = "/tag")
public class TagController {

    @Resource
    private TbUbCategoryService categoryService;

    @Resource
    private TbUbTagService tagService;

    /**
     * 视图跳转-标签管理
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        List<TbUbCategoryDto> allCategories = categoryService.getAllParentCategories();

        mav.addObject("allCategories", allCategories);
        mav.setViewName("/tag/home");
        return mav;
    }

    /**
     * 条件查询-获取标签列表
     * @param model
     * @param draw
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getTagsByCondition", method = RequestMethod.POST)
    public TableDataResponse getTagsByCondition(TbUbTagDto model,
                                                 @RequestParam(defaultValue = "1") int draw,
                                                 @RequestParam(defaultValue = "1") int currentPage,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        TableDataResponse response = new TableDataResponse();
        List<TbUbTagDto> categoryDtoList = tagService.getTagsByCondition(model, null, currentPage, pageSize);
        int total = tagService.countTagTotal(model);

        response.setDraw(draw);
        response.setiTotalRecords(total);
        response.setiTotalDisplayRecords(total);
        response.setAaData(categoryDtoList);
        return response;
    }

    /**
     * 删除-标签
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/del/{tagId}")
    public String delCategoryById(@PathVariable Integer tagId) {
        tagService.delTagById(tagId);
        return "";
    }

    /**
     * 发布下架-标签
     * @param model
     * @return
     */
    @RequestMapping(value = "/stopOrStart")
    public String stopOrStart(@RequestBody TbUbTagDto model) {
        model.setCreateTime(new Date());
        tagService.updateTag(model);
        return "";
    }
}
