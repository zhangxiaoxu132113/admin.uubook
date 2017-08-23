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
        #container {padding:0 1em;}
        #search-from {margin-bottom: 0.5em}
        .row {padding:0 1em;}
        .from-item {margin-right: 0.5em}
    </style>
</head>
<jsp:include page="../../common.jsp"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 版块管理 <span class="c-gray en">&gt;</span> 文档模块 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div id="container">
    <div class="mt-20">
        <div id="search-from">
            <div class="row">
                <span class="from-item"><label>ID : </label><input type="text" style="width: 120px" class="input-text"></span>
                <span class="from-item"><label>标题 : </label><input type="text" style="width: 220px" class="input-text"></span>
                <span class="from-item">
                    <label>日期范围 : </label>
                    <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
                    -
                    <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
                </span>
                <span class="from-item">
                    <label>分类 : </label>
                    <span class="select-box inline">
                        <select name="" class="select" style="width: 120px">
                            <option value="0">全部分类</option>
                            <option value="1">分类一</option>
                            <option value="2">分类二</option>
                        </select>
                    </span>
                </span>
                <span>
                    <button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
                </span>
            </div>
        </div>
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
        </table>
    </div>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/laypage/1.2/laypage.js"></script>
<script>
    $('.table-sort').dataTable({
        "aaSorting": [[ 1, "desc" ]],//默认第几个排序
        "bStateSave": true,//状态保存
        "searching": false,//关闭datatables自带搜索功能（没什么用）
        "aLengthMenu": [[10, 15, 20], [10, 15, 20]],//搜索栏显示
        "order": [[2, "desc"]],//第2列的数据倒序排序 此条会通过参数传给服务器
        "processing": true,//代码没加载完成时 会显示加载中…
        "oLanguage":    //DataTable中文化 把提示语之类的换成中文
           {
                "sProcessing": "正在加载中......",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "对不起，查询不到相关数据！",
                "sEmptyTable": "表中无数据存在！",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                "sSearch": "搜索",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
        },
        ajax: {
            "type": "POST",
            "url": '../blog/getArticleByPage',
            "data": function (data) {//在此处对data（datatables传给服务器端的数据）进行处理 data.start是从哪个数据开始，data.length是页面长度 通过这两个参数可以分页
                                    //currentPage pageSize 是我们项目需要的参数 为了改动不大在data中加了这两个参数
                var order = data.order;
                data.currentPage = data.start / data.length + 1;
                data.pageSize = data.length;
            }
        },
        "columns": [
                {"data": null, "title": "", 'sClass': "text-center", "orderable": false},//data 数据 如果复杂逻辑可以写在回调fnRowCallback中否则可以直接返回aoData的属性
                {"data": "id", "title": "ID", 'sClass': "text-center", "orderable": false},//'sClass': "text-center"居中
                {"data": "title", "title": "标题", 'sClass': "text-center", "orderable": false},//"orderable": false是否可排序（true界面上会有排序按钮）
            ]
    });
</script>
</body>
</html>
