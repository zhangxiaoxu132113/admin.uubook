<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mrwater
  Date: 2017/8/23
  Time: 上午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <style>
        #container {
            padding: 0 1em;
        }

        #search-from {
            background: #fff;
            padding: 1em 0 0 1em;
            padding-bottom: 1em;
            border-bottom: 1px solid #ebeef2;
        }
        #DataTables_Table_0_wrapper {
            background: #fff;
            padding: 1em;
        }
        .row {
            padding: 0 1em;
        }

        .from-item {
            margin-right: 0.5em
        }

        .text-center {
            text-align: center !important;
        }
        input {
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            font-size: 12px!important;
            border-radius: 5px;
        }
        label {
            font-size:12px!important;
        }
    </style>
</head>
<jsp:include page="../../common.jsp"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 版块管理 <span
        class="c-gray en">&gt;</span> 文档模块 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div id="container">
    <div>
        <div id="search-from">
            <div class="row">
                <span class="from-item">
                    <label>模块 : </label>
                    <span class="select-box inline">
                        <select name="" id="article_module" class="select" style="width: 120px">
                            <option value="-1" selected>全部分类</option>
                            <c:forEach items="${requestScope.allModule}" var="category">
                                <option value="${category.value}">${category.label}</option>
                            </c:forEach>
                        </select>
                    </span>
                </span>
                <span class="from-item">
                    <label>分类 : </label>
                    <span class="select-box inline">
                        <select name="" id="article_category" class="select" style="width: 120px">
                            <option value="0" disabled>全部分类</option>
                            <c:forEach items="${requestScope.allCategories}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </span>
                </span>
                <span class="from-item">
                    <span class="select-box inline">
                        <select name="" id="enable_select" class="select" style="width: 120px">
                            <option value="-1">是否发布</option>
                            <option value="0">已下架</option>
                            <option value="1">已发布</option>
                        </select>
                    </span>
                </span>
                <span class="from-item"><input id="article_id" type="text" style="width: 220px"
                                                                   placeholder="请输入精确id或标题模糊搜索"
                                                                   class="input-text"></span>
                <span class="from-item">
                    <button name="" id="search-btn" class="btn btn-primary radius" type="button"><i
                            class="Hui-iconfont">&#xe665;</i> 搜索
                    </button>
                </span>
                <span class="from-item">
                    <button name="" id="del_btn" class="btn btn-danger radius" onclick="searchFrom()" type="button"><i
                            class="Hui-iconfont">&#xe6e2;</i> 删除
                    </button>
                </span>
            </div>
        </div>
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
        </table>
    </div>
    <div id="upload_from_box" style="display: none">
        <form action="" id="upload_from" method="post" enctype="multipart/form-data">
            <input type="file" name="file" id="upload_file">
        </form>
        <iframe src="" name="upload_iframe" style="display: none" frameborder="0"></iframe>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="<%=path%>/asset/js/mw-upload.js"></script>
<script type="text/javascript" src="<%=path%>/asset/js/blog/blogModule.js"></script>
</body>
</html>
