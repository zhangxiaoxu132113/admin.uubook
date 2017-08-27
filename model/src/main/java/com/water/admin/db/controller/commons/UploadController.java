package com.water.admin.db.controller.commons;

import com.water.admin.utils.lang.FileTools;
import com.water.uubook.model.dto.ArticleDto;
import com.water.uubook.service.ArticleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mrwater on 16/7/7.
 */
@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    @Resource
    private ArticleService articleService;

    private static Log logger = LogFactory.getLog(UploadController.class);

    /**
     * @description 多图片文件上传
     * @time 2016-07-07
     */
    @RequestMapping(value = "/imageList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> uploadMultiImages(@RequestParam(value = "file", required = false)
                                                 MultipartFile file, HttpServletRequest request) {
        //定义返回的json格式
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        //文件上传路径
        String path = request.getSession().getServletContext().getRealPath("upload");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String uploadDate = dateFormat.format(new Date());
        path = path + File.separator + uploadDate;

        //上传文件名字
        String extendsFileName = FileTools.getFileExtendsName(file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + extendsFileName;
        File targetFile = new File(path, fileName);

        //创建文件
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        //保存文件
        try {
            file.transferTo(targetFile);
            jsonMap.put("imgSrc", "/upload" + File.separator + uploadDate + File.separator + fileName);
            return jsonMap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
    @RequestMapping(value = "/uploadArticlePicUrl/{articleId}", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> uploadArticlePicUrl(@RequestParam(value = "file", required = false)
                                                   MultipartFile file,
                                                   @PathVariable Integer articleId,
                                                   HttpServletRequest request) {
        //定义返回的json格式
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        ArticleDto article = articleService.findArticleById(articleId);
        if (article == null) {
            logger.info("id为" + articleId + "的文章不存在！");
            return null;
        }
        //文件上传路径
        String path = request.getSession().getServletContext().getRealPath("upload");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String uploadDate = dateFormat.format(new Date());
        path = path + File.separator + uploadDate;

        //上传文件名字
        String extendsFileName = FileTools.getFileExtendsName(file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + extendsFileName;
        File targetFile = new File(path, fileName);

        //创建文件
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        //保存文件
        try {
            file.transferTo(targetFile);
            jsonMap.put("imgSrc", "/upload" + File.separator + uploadDate + File.separator + fileName);
            article.setPicUrl("/upload" + File.separator + uploadDate + File.separator + fileName);
            articleService.updateArticle(article);
            return jsonMap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
