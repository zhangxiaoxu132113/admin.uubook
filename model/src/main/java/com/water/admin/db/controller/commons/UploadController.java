package com.water.admin.db.controller.commons;

import com.water.admin.db.domain.dto.BaseResponse;
import com.water.admin.db.domain.dto.DataResponse;
import com.water.admin.utils.lang.FileTools;
import com.water.image.client.ImageUploadClient;
import com.water.uubook.model.Category;
import com.water.uubook.model.dto.ArticleDto;
import com.water.uubook.service.ArticleService;
import com.water.uubook.service.CategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
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

    @Resource
    private CategoryService categoryService;

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
    public DataResponse uploadArticlePicUrl(@RequestParam(value = "file", required = false) MultipartFile file,
                                            @PathVariable Integer articleId, HttpServletRequest request) throws IOException, TException {
        //定义返回的json格式
        ArticleDto article = articleService.findArticleById(articleId);
        if (article == null) {
            return new DataResponse(BaseResponse.NOT_FOUND_RESOURCE, "id为" + articleId + "的文章不存在！");
        }
        String picPath = ImageUploadClient.uploadImage(file.getBytes(), file.getOriginalFilename());
        article.setPicUrl(picPath);
        articleService.updateArticle(article);
        return new DataResponse(picPath);
    }

    @RequestMapping(value = "/uploadCategoryPicUrl/{categoryId}", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> uploadCategoryPicUrl(@RequestParam(value = "file", required = false)
                                                    MultipartFile file,
                                                    @PathVariable Integer categoryId,
                                                    HttpServletRequest request) {
        //定义返回的json格式
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null) {
            logger.info("id为" + categoryId + "的文章不存在！");
            return null;
        }

        //保存文件
        try {
            String picUrl = this.uploadPic(request, file);
            jsonMap.put("imgSrc", picUrl);
            category.setPicUrl(picUrl);
            categoryService.updateCategory(category);
            return jsonMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String uploadPic(HttpServletRequest request, MultipartFile file) {
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
            return "/upload" + File.separator + uploadDate + File.separator + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
