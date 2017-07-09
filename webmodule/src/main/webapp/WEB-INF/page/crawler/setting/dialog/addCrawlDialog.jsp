<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:include page="../../../common.jsp"/>
<link rel="stylesheet" href="<%=path%>/lib/select2/css/select2.min.css">
<link rel="stylesheet" href="<%=path%>/static/extends/css/button.css">
<script src="<%=path%>/lib/select2/js/select2.min.js"></script>
<script src="<%=path%>/lib/angularjs/angular-1.2.22.min.js"></script>
<style>
    #add-crawler-dialog {
        padding: 1em;
        height: 100%;
        position: relative;
        box-sizing: border-box;
    }

    #add-crawler-table tr {
        display: block;
        margin-bottom: 1em;
    }

    #add-crawler-table tr td:first-child {
        width: 80px;
        text-align: center;
    }

    .server-flag {
        width: 200px;
    }

    #add-crawler-table input, #add-rule-table input {
        width: 200px;
        height: 28px;
        border: 1px solid #aaa;
    }

    #add-crawler-dialog-inner {
        position: relative;
        height: inherit;
        padding-bottom: 60px;
        padidng-left: 2px;
    }

    .operator-btn-group {
        text-align: center;
        background: #efeef0;
        position: fixed;
        width: 100%;
        height: 60px;
        line-height: 60px;
        bottom: 0px;
        left: 0;
    }

    .operator-btn-group button {
        font-size: 14px;
        font-weight: 400;
    }

    select.get-rule {
        width: 80px;
        height: 28px;
        background: #fff;
    }
</style>
<div id="add-crawler-dialog" ng-app="myApp">
    <div id="add-crawler-dialog-frame" ng-controller="addCrawlDialogCtrl">
        <div id="add-crawler-dialog-inner">
            <table id="add-crawler-table">
                <tr>
                    <td>标志</td>
                    <td>
                        <select class="server-flag" ng-model="crawlRule.id">
                            <option value="ibm.com">ibm.com</option>
                            <option value="csdn.cn">csdn.cn</option>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td>描述</td>
                    <td><input type="text" ng-model="crawlRule.description"></td>
                </tr>
                <tr>
                    <td>模块</td>
                    <td><input type="text" ng-model="crawlRule.module[0].mid"></td>
                </tr>
            </table>
            <table class="table table-border table-bordered table-bg table-hover table-sort" id="add-rule-table">
                <thead>
                <tr class="text-c">
                    <th width="80">序号</th>
                    <th width="120">样式规则</th>
                    <th width="120">对象属性</th>
                    <th width="120">提取规则</th>
                    <th width="120">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr class="text-c" ng-repeat="item in crawlRule.module[0].ruleList">
                    <td><span style="margin-right: 0.5em">规则</span>{{$index+1}}</td>
                    <td>
                        <span><input type="text" ng-model="item.rule"></span>
                    </td>
                    <td>
                        <select class="get-rule" ng-model="item.id">
                            <option value="title">标题</option>
                            <option value="author">作者</option>
                            <option value="content">内容</option>
                            <option value="content">创建时间</option>
                        </select>
                    </td>
                    <td>
                        <select class="get-rule" ng-model="item.get">
                            <option value="html">html</option>
                            <option value="text">text</option>
                            <option value="owner-text">owner-text</option>
                        </select>
                    </td>
                    <td>
                        <span ng-click="addRule()">新增</span>
                        <span ng-click="removeRule($index, item)">移除</span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="operator-btn-group">
            <button class="button button-primary button-rounded button-small" ng-click="addRule()"
                    style="margin-right: 0.5em">添加规则
            </button>
            <button class="button button-primary button-rounded button-small" ng-click="saveCrawler()">保存</button>
            <button class="button button-caution button-rounded button-small">取消</button>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $(".server-flag").select2();
    });
    var app = angular.module('myApp', []);
    app.controller("addCrawlDialogCtrl", function ($scope, $http) {
        $scope.crawlRule = {};

        $scope.crawlRule.module = [];
        $scope.crawlRule.module[0] = {};
        $scope.crawlRule.module[0].ruleList = [];

        $scope.saveCrawler = function () {
            console.log("haha");
            console.log($scope.crawlRule);
            $http({
                method:"POST",
                url: 'http://macmrwater.ngrok.cc/test1',
                headers: {
                    "Content-Type": "application/json"
                },
                data: $scope.crawlRule
            }).then(function successCallback(response) {
                console.log(response);
                // 请求成功执行代码
            }, function errorCallback(response) {
                // 请求失败执行代码
            });

//            $http.post("http://macmrwater.ngrok.cc/test1",
//                    $scope.crawlRule
//            ).success(function (data, status, headers, config) {
//                console.log(data);
//                alert("success");
//            }).error(function (data, status, headers, config) {
//                alert("error");
//            });

        }

        $scope.addRule = function () {
            var rule = {};
            $scope.crawlRule.module[0].ruleList.push(rule);

        }

        $scope.removeRule = function (index, item) {
            $scope.crawlRule.module[0].ruleList.splice(index, 1);
        }
    });
</script>

