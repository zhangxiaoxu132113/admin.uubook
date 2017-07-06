<%--
  Created by IntelliJ IDEA.
  User: zhangmiaojie
  Date: 2017/7/6
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="add-task">
  <div id="add-task-from">
    <form action="/crawler/add.do">
      <table>
        <tr>
          <td>任务备注</td>
          <td><input type="text" /></td>
        </tr>
        <tr>
          <td>抓取类型</td>
          <td>
            <label><span>关键词</span><input type="radio" name="type" value="2" checked/></label>
            <label><span>url连接</span><input type="radio" name="type" value="1"/></label>
          </td>
        </tr>
        <tr>
          <td>任务类型</td>
          <td>
            <label><span>单次</span><input type="radio" name="type" value="2" checked/></label>
            <label><span>监控</span><input type="radio" name="type" value="1"/></label>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <input type="text" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
