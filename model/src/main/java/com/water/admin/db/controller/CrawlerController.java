package com.water.admin.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangmiaojie on 2017/7/6.
 */
@Controller
@RequestMapping(value = "/crawler")
public class CrawlerController {

    @RequestMapping(value = "/task")
    public ModelAndView task() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/crawler/task/home");

        return mav;
    }

    @RequestMapping(value = "/addTaskDialog")
    public ModelAndView addTaskDialog() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/crawler/task/dialog/addTask");

        return mav;
    }
}
