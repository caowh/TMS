<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%
    String key = request.getParameter("key");//用request得到
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>GVML自动化测试</title>
    <link rel="icon" href="/assets/img/get-icon.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="http://127.0.0.1:3000/dist/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="http://127.0.0.1:3000/dist/css/gvml.css" />
    <style>
        input{border:1px solid #fffffb;background-color:transparent;color: coral}
        #eachTest {position:absolute;top:80px;left:0;color:#fff}
        #showResult {position:absolute;top:80px;left:0;color:#fff}
        #status {position:absolute;top:80px;right:0px;color:#fff}
        #eachTest {display:block}
        #eachTest #dd span{float:left}
        #eachTest #ddPC {display:none}
        #caseDetailResult {display:none;}
        #testlinkDetailResult {display:none;}
        .check {position: relative;left: 10px}
        #mtResult {display:none}
        #atResult {display:none}
        #caseResult {display:none}
        #testlinkResult {display:none}
        #showResult dd span {float:left}
        .prefixStyle {color: #41ff55}
    </style>
</head>

<body>
<div id="container" class="fullSize"></div>
<div id="eachTest">
    <dd><span class="prefixStyle">当前执行用例：</span><span id="currentCase"></span></dd>
    <dd><span class="prefixStyle">执行时间：</span><span id="currentTime"></span></dd>
    <dd id="ddPC"><span class="prefixStyle">通过准则：</span><span id="passCriteria"></span></dd>
    <dd id="dd"><span class="prefixStyle">执行结果：</span>
        <span id="mtResult">
            <label><input name="result" id="resultP" type="radio" value="p" />通过</label>
            <label><input name="result" id="resultF" type="radio" value="f" />失败</label>
            <label>(请在倒计时到达1s前做出选择，不选择等于未执行)</label>
        </span>
        <span id="atResult">
            <label id="result"></label>
        </span>
    </dd>
</div>
<div id="showResult">
    <dd id="caseResult">
        <span class="prefixStyle">用例执行结果：</span>
        <span id="caseTotalResult"></span>
        <span id="caseDetailResult"><input type="text" value="" /></span>
        <span class="check"><input type="checkbox" id="detailCaseCheckbox" onchange="changeStyleCaseResult()"/>显示详细信息</span>
    </dd>
    <dd id="testlinkResult">
        <span class="prefixStyle">发送testlink结果：</span>
        <span id="testlinkTotalResult"></span>
        <span id="testlinkDetailResult"><input type="text" value="" /></span>
        <span class="check"><input type="checkbox" id="detailtestLinkCheckbox" onchange="changeStyleTestlinkResult()"/>显示详细信息</span>
    </dd>
</div>
<div id="status">
    <span class="prefixStyle">当前执行状态：</span><span id="currentStautus"></span>
</div>
<script src="http://127.0.0.1:3000/dist/GVML.js"></script>
<script type="text/javascript" src="/assets/js/libs/jquery-1.10.2.min.js"></script>

<script type="text/javascript" src="/assets/js/roujiamo/testUtils.js"></script>
<script type="text/javascript" src="/assets/js/roujiamo/roujiamo.js"></script>
<script type="text/javascript" src="/assets/js/roujiamo/console.catch.js"></script>
<script type="text/javascript" src="/assets/js/roujiamo/testcases.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        var key='<%=key%>'
        Roujiamo.start(key)
    })
</script>
</body>
</html>