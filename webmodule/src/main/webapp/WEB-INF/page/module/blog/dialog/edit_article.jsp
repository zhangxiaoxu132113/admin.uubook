<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<style>
    .input-text, .btn, .input-text.size-M, .btn.size-M {
        font-size: 12px;
        height: 31px;
        line-height: 1.42857;
        padding: 4px;
    }

    .input-text {
        box-sizing: border-box;
        border: solid 1px #ddd;
        width: 100%;
        -webkit-transition: all .2s linear 0s;
        -moz-transition: all .2s linear 0s;
        -o-transition: all .2s linear 0s;
        transition: all .2s linear 0s;
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
        font-size: 12px !important;
        border-radius: 5px;
    }

    .from-item {
        margin: 0.5em 0;
    }

    .edit-article {
        padding-left: 1em;
        padding-top: 1em;
        background: #fff;
    }
</style>
<jsp:include page="../../../common.jsp"/>
<script type="text/javascript" src="<%=basePath%>/lib/ckeditor4.1/ckeditor.js"></script>
<div class="edit-article">
    <form id="detailForm" method="post">
        <div class="from-item">
            <label>标题 : </label>
            <input id="article_id" type="text" style="width: 80%;font-size: 22px;"
                   placeholder="请输入精确id或标题模糊搜索"
                   class="input-text" value="${requestScope.article.title}">
            <button name="" id="search-btn" class="btn btn-primary radius" type="button"><i
                    class="Hui-iconfont">&#xe665;</i> 保存
            </button>
        </div>
        <div class="from-item">
        <span class="from-item">
                    <label>模块 : </label>
                    <span class="select-box inline">
                        <select name="" id="article_module" class="select" style="width: 120px">
                            <option value="-1">全部分类</option>
                            <c:forEach items="${requestScope.allModule}" var="module">
                                <option value="${module.value}" <c:if test="${module.value == requestScope.article.module}"> selected </c:if>>${module.label}</option>
                            </c:forEach>
                        </select>
                    </span>
                </span>
                <span class="from-item">
                    <label>分类 : </label>
                    <span class="select-box inline">
                        <select name="" id="article_category" class="select" style="width: 120px">
                            <c:forEach items="${requestScope.allCategories}" var="category">
                                <option value="${category.id}" <c:if test="${category.id == requestScope.article.categoryDto.parent.id}">selected</c:if>>${category.name}</option>
                            </c:forEach>
                        </select>
                    </span>
                    <span class="select-box inline">
                        <select name="" id="article_category_second" class="select" style="width: 120px">
                            <c:forEach items="${requestScope.article.categoryDto.parent.children}" var="category">
                                <option value="${category.id}" <c:if test="${category.id == requestScope.article.category}">selected</c:if>>${category.name}</option>
                            </c:forEach>
                        </select>
                    </span>
                </span>
        </div>
    <textarea id="content" name="content">
        ${requestScope.article.content}
    </textarea>
        <input type="button" value="保存" id="save" onclick="save()"/>
    </form>
</div>

<script type="text/javascript">
    var editor = null;
    window.onload = function () {
        editor = CKEDITOR.replace('content'); //参数‘content’是textarea元素的name属性值，而非id属性值
    }
</script>

