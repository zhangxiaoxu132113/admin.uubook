<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <title>爬虫任务</title>
  <meta charset="UTF-8">
  <jsp:include page="../../common.jsp"/>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 爬虫框架 <span class="c-gray en">&gt;</span> 爬虫任务 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
  <div class="text-c">
    <button onclick="addTask()" class="btn btn-primary radius">新增</button>
	 <span class="select-box inline">
		<select name="" class="select">
          <option value="0">全部分类</option>
          <option value="1">分类一</option>
          <option value="2">分类二</option>
        </select>
		</span> 日期范围：
    <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
    -
    <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
    <input type="text" name="" id="" placeholder=" 资讯名称" style="width:250px" class="input-text">
    <button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜资讯</button>
  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" data-title="添加资讯" data-href="article-add.html" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
  <div class="mt-20">
    <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
      <thead>
      <tr class="text-c">
        <th width="25"><input type="checkbox" name="" value=""></th>
        <th width="80">ID</th>
        <th>备注</th>
        <th width="80">执行状态</th>
        <th width="80">任务数量</th>
        <th width="120">创建时间</th>
        <th width="75">完成时间</th>
        <th width="120">状态</th>
        <th width="120">操作</th>
      </tr>
      </thead>
      <tbody>
      <tr class="text-c">
        <td><input type="checkbox" value="" name=""></td>
        <td>10001</td>
        <td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('查看','article-zhang.html','10001')" title="查看">资讯标题</u></td>
        <td>待爬取</td>
        <td>12</td>
        <td>2014-6-11 11:11:42</td>
        <td>21212</td>
        <td class="td-status"><span class="label label-success radius">已发布</span></td>
        <td class="f-14 td-manage"><a style="text-decoration:none" onClick="article_stop(this,'10001')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_edit('资讯编辑','article-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
<script>
  function addTask () {
    layer.open({
      type: 2,
      area: ['600px','450px'],
      fix: false, //不固定
      maxmin: true,
      shade:0.4,
      title: '新建任务',
      content: '/crawler/addTaskDialog"/>'
    });
  }
</script>
</body>
</html>
