<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            margin-bottom: 0.5em
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
    </style>
</head>
<jsp:include page="../common.jsp"/>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 静态化维护 <span
        class="c-gray en">&gt;</span> 首页 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div id="container">
    <div class="mt-20">
        <div id="search-from">
            <div class="row">
                <span class="from-item">
                    <label>版块区域 : </label>
                    <span class="select-box inline">
                        <select name="" id="static_module" class="select" style="width: 120px">
                            <c:forEach items="${requestScope.moduleList}" var="module">
                                <option value="${module.id}">${module.name}</option>
                            </c:forEach>
                        </select>
                    </span>
                </span>
                <span>
                    <button name="" id="add_btn" class="btn btn-success" onclick="searchFrom()" type="button"><i
                            class="Hui-iconfont">&#xe665;</i> 生成静态模板
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
    </div>
</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/lib/laypage/1.2/laypage.js"></script>
<script>
    $(document).ready(function () {

    });
    /**
     * 搜索
     */
    function searchFrom() {
        var table = $('.table-sort').dataTable();
        console.log(table);
        table.api().ajax.reload();
    }
    ;

    /**
     * 上传文章封面
     * @param articleId
     */
    function uploadPicUrl(categoryId) {
        console.log(categoryId);
        var $upload_from = $("#upload_from");
        var $upload_file = $('#upload_file');
        var requestUrl = "/upload/uploadCategoryPicUrl/" + categoryId;
        $upload_from.attr("action", requestUrl);
        $upload_file.click();
    }

    /*上传图片按钮-改变事件*/
    $('#upload_file').change(function () {
        var $upload_from = $("#upload_from");
        console.log($upload_from.action);
        $upload_from.submit();
    });

    $('#static_module').change(function(){
        var select_module = $(this).val();
        console.log(select_module);
        var table = $('.table-sort').dataTable();
        console.log(table);
        table.api().ajax.reload();
    });

    /*分类-删除*/
    function del_category(obj, categoryId) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '/category/del/' + categoryId,
                dataType: 'json',
                success: function (data) {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });
        });
    }

    /*分类-下架*/
    function product_stop(obj, categoryId) {
        layer.confirm('确认要下架吗？', function (index) {
            var jsonData = {enable: 0, id: categoryId};
            $.ajax({
                type: 'POST',
                url: '/category/stopOrStart',
                contentType:'application/json',
                data:JSON.stringify(jsonData),
                dataType: 'json',
                success: function (data) {
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,'+categoryId+')" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
                    $(obj).remove();
                    layer.msg('已下架!', {icon: 5, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });

        });
    }

    /*分类-发布*/
    function product_start(obj, categoryId) {
        layer.confirm('确认要发布吗？', function (index) {
            var jsonData = {enable: 1, id: categoryId};
            $.ajax({
                type: 'POST',
                url: '/category/stopOrStart',
                contentType:'application/json',
                data:JSON.stringify(jsonData),
                dataType: 'json',
                success: function (data) {
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,'+categoryId+')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
                    $(obj).remove();
                    layer.msg('已发布!', {icon: 6, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });

        });
    }

    $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "searching": false,//关闭datatables自带搜索功能（没什么用）
        "aLengthMenu": [[10, 15, 20], [10, 15, 20]],//搜索栏显示
        "processing": true,//代码没加载完成时 会显示加载中…
        "serverSide": true,//服务器端处理数据
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
            "url": '../article/getArticleByPage',
            "data": function (data) {//在此处对data（datatables传给服务器端的数据）进行处理 data.start是从哪个数据开始，data.length是页面长度 通过这两个参数可以分页
                //currentPage pageSize 是我们项目需要的参数 为了改动不大在data中加了这两个参数
                var order = data.order;
//                data.id = $('#article_id').val();
//                data.title = $('#article_title').val();
//                data.startTime = $('#start_time').val();
//                data.endTime = $('#end_time').val();
                data.module = $('#static_module').val();
                data.currentPage = data.start / data.length + 1;
                data.pageSize = data.length;
            }
        },
        "columns": [
            {
                "data": null,
                "title": "",
                'sClass': "text-center",
                "orderable": false,
                render: function (data, type, row, meta) {
                    return '<input name="" type="checkbox" value="">';
                }
            },//data 数据 如果复杂逻辑可以写在回调fnRowCallback中否则可以直接返回aoData的属性
            {"data": "id", "title": "ID", 'sClass': "text-center", "width": "60"},//'sClass': "text-center"居中
            {"data": "title", "title": "标题", "width": "220"},//"orderable": false是否可排序（true界面上会有排序按钮）
            {
                "data": "picUrl",
                "title": "封面",
                'sClass': "text-center",
                "width": "160",
                "orderable": false,
                render: function (data, type, row, meta) {
                    var ele;
                    if (data == undefined || data == '') {
                        var id = row.id;
                        ele = "<a onclick='uploadPicUrl(" + id + ")' href='javascript:void(0)'>上传封面</a>"
                    } else {
                        ele = '<img src="' + data + '" width="160px" height="107px"/>';
                    }
                    return ele;
                }
            },//"orderable": false是否可排序（true界面上会有排序按钮）
            {"data": "categoryStr", "title": "所属分类", 'sClass': "text-center", "orderable": false, "width": "80"},
            {"data": "createOnStr", "title": "更新日期", 'sClass': "text-center", "width": "110"},
            {"data": "viewHits", "title": "浏览次数", 'sClass': "text-center", "width": "60"},
            {
                "data": "enable", "title": "发布状态", 'sClass': "text-center td-status", "width": "70",
                render: function (data, type, row, meta) {
                    var p_status;
                    if (!data) {
                        p_status = '<span class="label radius">已下架</span>';
                    } else {
                        p_status = '<span class="label label-success radius">已发布</span>';
                    }
                    return p_status;
                }
            },
            {
                "data": null,
                "title": "操作",
                'sClass': "text-center td-manage",
                "orderable": false,
                "width": "80",
                render: function (data, type, row, meta) {
                    var is_pub;
                    if (!row.enable) {
                        is_pub = '<a style="text-decoration:none" onclick="product_start(this,' + row.id + ')" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>';
                    } else {
                        is_pub = '<a style="text-decoration:none" onclick="product_stop(this,' + row.id + ')" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>';
                    }
                    return is_pub +
                            '<a style="text-decoration:none" class="ml-5" onClick="article_edit("资讯编辑","article-add.html","10001")" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>' +
                            '<a style="text-decoration:none" class="ml-5" href="javascript:void(0)" onclick="del_article(this,' + row.id + ')" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';
                }
            }
        ]
    });
</script>
</body>
</html>
