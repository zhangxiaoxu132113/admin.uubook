/**
 * 创建表格
 * @param tableId
 * @param cols
 * @param url
 * @param my_data
 */
function create_table(tableId, cols, url, my_data) {
    $('.' + tableId + '').dataTable({
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
            "url": url,
            "data": function (data) {//在此处对data（datatables传给服务器端的数据）进行处理 data.start是从哪个数据开始，data.length是页面长度 通过这两个参数可以分页
                //currentPage pageSize 是我们项目需要的参数 为了改动不大在data中加了这两个参数
                var order = data.order;
                data.currentPage = data.start / data.length + 1;
                data.pageSize = data.length;
                for (var Key in my_data){
                    if (Key == 'module') {
                        data.module = my_data[Key]
                    }
                }
            }
        },
        "columns":cols
    });
}

/**
 * 删除一行
 * @param obj
 * @param categoryId
 */
function del_row(obj, url) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: url,
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