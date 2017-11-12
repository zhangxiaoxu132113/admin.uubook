var select_upload_id = 0;
var editInfo = "编辑文章";
var editUrl = "edit_view/";

/**
 * 上传文章封面
 * @param articleId
 */
function uploadPicUrl(articleId) {
    select_upload_id = articleId;
    //console.log(articleId);
    var $upload_from = $("#upload_from");
    var $upload_file = $('#upload_file');
    var requestUrl = "/upload/uploadArticlePicUrl/" + articleId;
    $upload_from.attr("action", requestUrl);
    $upload_file.click();
}

/*产品-下架*/
function product_stop(obj, articleId) {
    layer.confirm('确认要下架吗？', function (index) {
        var jsonData = {enable: 0, id: articleId};
        $.ajax({
            type: 'POST',
            url: '/article/stopOrStart',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            dataType: 'json',
            success: function (data) {
                $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,' + articleId + ')" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
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

/*产品-发布*/
function product_start(obj, articleId) {
    layer.confirm('确认要发布吗？', function (index) {
        var jsonData = {enable: 1, id: articleId};
        $.ajax({
            type: 'POST',
            url: '/article/stopOrStart',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            dataType: 'json',
            success: function (data) {
                $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,' + articleId + ')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
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

/*文章-删除*/
function del_article(obj, articleId) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/article/del/' + articleId,
            dataType: 'json',
            success: function (data) {
                //$(obj).parents("tr").remove();
                var table = $('.table-sort').dataTable();
                table.api().ajax.reload();
                layer.msg('已删除!', {icon: 1, time: 1000});
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}

/*文章-编辑*/
function article_edit(title, articleId) {
    var index = layer.open({
        type: 2,
        title: title,
        content: editUrl + articleId
    });
    layer.full(index);
}

$(document).ready(function () {
    /*搜索*/
    $('#search-btn').click(function () {
        var table = $('.table-sort').dataTable();
        table.api().ajax.reload();
    })

    /*选择模块*/
    $('#article_module').change(function () {
        var table = $('.table-sort').dataTable();
        table.api().ajax.reload();
    });

    /*上传图片按钮-改变事件*/
    $('#upload_file').change(function () {
        setJsonBySubmit($('#upload_from'), "upload_iframe", function (data) {
            var img = '<img src="' + data + '" width="120px" height="70.5px"/>';
            $('#img-info-' + select_upload_id).replaceWith(img);
        });
    });

    $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "searching": false,//关闭datatables自带搜索功能（没什么用）
        "processing": true,//代码没加载完成时 会显示加载中…
        "serverSide": true,//服务器端处理数据
        "oLanguage":    //DataTable中文化 把提示语之类的换成中文
        {
            "sProcessing": "正在加载中......",
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
                data.id = $('#article_id').val();
                data.title = $('#article_title').val();
                data.startTime = $('#start_time').val();
                data.endTime = $('#end_time').val();
                data.module = $('#article_module').val();
                data.enable = $('#enable_select').val() == -1 ? null : $('#enable_select').val();
                //data.enable = $('#enable_select').val();
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
            {"data": "title", "title": "标题", "width": "220", "orderable": false,},//"orderable": false是否可排序（true界面上会有排序按钮）
            {
                "data": "picUrl",
                "title": "封面",
                'sClass': "text-center",
                "width": "120",
                "orderable": false,
                render: function (data, type, row, meta) {
                    var ele;
                    if (data == undefined || data == '') {
                        var id = row.id;
                        ele = "<a onclick='uploadPicUrl(" + id + ")' id='img-info-" + id + "' href='javascript:void(0)'>上传封面</a>"
                    } else {
                        ele = '<img src="' + data + '" width="120px" height="70.5px"/>';
                    }
                    return ele;
                }
            },//"orderable": false是否可排序（true界面上会有排序按钮）
            {"data": "moduleStr", "title": "所属模块", 'sClass': "text-center", "orderable": false, "width": "90"},
            {"data": "categoryStr", "title": "所属分类", 'sClass': "text-center", "orderable": false, "width": "90"},
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
            {"data": "createOnStr", "title": "更新日期", 'sClass': "text-center", "width": "110"},
            {
                "data": null,
                "title": "操作",
                'sClass': "text-center td-manage",
                "orderable": false,
                "width": "70",
                render: function (data, type, row, meta) {
                    var is_pub;
                    var articleId = row.id;
                    if (!row.enable) {
                        is_pub = '<a style="text-decoration:none" onclick="product_start(this,' + row.id + ')" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>';
                    } else {
                        is_pub = '<a style="text-decoration:none" onclick="product_stop(this,' + row.id + ')" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>';
                    }
                    return is_pub +
                        '<a style="text-decoration:none" class="ml-5" onClick="article_edit(editInfo,' + row.id + ')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>' +
                        '<a style="text-decoration:none" class="ml-5" href="javascript:void(0)" onclick="del_article(this,' + row.id + ')" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';
                }
            }
        ]
    });
});
