package com.water.admin.db.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

/**
 * Created by mrwater on 16/6/4.
 * 视图转发器
 */
@Controller
public class ViewDispatcher {
    private Log logger = LogFactory.getLog(ViewDispatcher.class);

    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws ExecutionException {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/index");
        return mav;
    }
}
