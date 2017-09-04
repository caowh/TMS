<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017/9/1
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/test/reset.css" />
    <link rel="stylesheet" type="text/css" href="/assets/css/test/solarSystemStyle.css" />
</head>
<body>
<div class="container">
    <div class="demo-perspective-photo">
        <div id="contentContainer">
            <div id="carouselContainer">
                <figure class="mod-sun">
                    <div style="width: 100%;height: 100%" id="chart"></div>
                </figure>
                <figure class='example example2'>
                    <h1 style="color: rgba(228, 128, 24, 0.8)">基础设施</h1>
                    <div class="x">
                        <div class="x1">
                        </div>
                        <div class="x2"></div>
                        <div class="xInner"></div>
                        <div class="y"></div>
                        <div class="y1"></div>
                        <div class="y2"></div>
                        <div class="yInner"></div>
                        <div class="z"></div>
                        <div class="z1"></div>
                        <div class="z2"></div>
                        <div class="zInner"></div>
                    </div>
                </figure>
                <figure class='example example3'>
                    <h1 style="color: rgba(31, 107, 234, 0.8)">专题服务集</h1>
                    <div class="x">
                        <div class="x1"></div>
                        <div class="x2"></div>
                        <div class="xInner"></div>
                        <div class="y"></div>
                        <div class="y1"></div>
                        <div class="y2"></div>
                        <div class="yInner"></div>
                        <div class="z"></div>
                        <div class="z1"></div>
                        <div class="z2"></div>
                        <div class="zInner"></div>
                    </div>
                </figure>
                <figure class='example example4'>
                    <h1 style="color: rgba(245, 192, 86, 0.7)">数据中心</h1>
                    <div class="x">
                        <div class="x1"></div>
                        <div class="x2"></div>
                        <div class="xInner"></div>
                        <div class="y"></div>
                        <div class="y1"></div>
                        <div class="y2"></div>
                        <div class="yInner"></div>
                        <div class="z"></div>
                        <div class="z1"></div>
                        <div class="z2"></div>
                        <div class="zInner"></div>
                    </div>
                </figure>
                <figure class='example example6'>
                    <h1 style="color: rgba(136, 155, 250, 0.8)">应用平台</h1>
                    <div class="x">
                        <div class="x1"></div>
                        <div class="x2"></div>
                        <div class="xInner"></div>
                        <div class="y"></div>
                        <div class="y1"></div>
                        <div class="y2"></div>
                        <div class="yInner"></div>
                        <div class="z"></div>
                        <div class="z1"></div>
                        <div class="z2"></div>
                        <div class="zInner"></div>
                    </div>
                </figure>
                <figure class="circle circle2"></figure>
                <figure class="circle circle3"></figure>
                <figure class="circle circle4"></figure>
                <figure class="circle circle6"></figure>
                <div class="box">
                    <div  id="img1" style="transform: rotateY(0deg) translateZ(275px);">
                        <a href="/CaseResult/total.do?name=GVML规范" target="_blank"><img src="/assets/img/test/1.png" ></a>
                    </div>
                    <div id="img2" style="transform: rotateY(40deg) translateZ(275px);">
                        <img src="/assets/img/test/2.png">
                    </div>
                    <div id="img3" style="transform: rotateY(80deg) translateZ(275px);">
                        <img src="/assets/img/test/3.png">
                    </div>
                    <div id="img4" style="transform: rotateY(120deg) translateZ(275px);">
                        <img src="/assets/img/test/4.png">
                    </div>
                    <div id="img5" style="transform: rotateY(160deg) translateZ(275px);">
                        <img src="/assets/img/test/5.png">
                    </div>
                    <div id="img6" style="transform: rotateY(200deg) translateZ(275px);">
                        <a href="/CaseResult/total.do?name=元数据服务" target="_blank"><img src="/assets/img/test/6.png" ></a>
                    </div>
                    <div id="img7" style="transform: rotateY(240deg) translateZ(275px);">
                        <img src="/assets/img/test/7.png">
                    </div>
                    <div id="img8" style="transform: rotateY(280deg) translateZ(275px);">
                        <img src="/assets/img/test/8.png">
                    </div>
                    <div id="img9" style="transform: rotateY(320deg) translateZ(275px);">
                        <a href="/CaseResult/total.do?name=用户管理服务" target="_blank"><img src="/assets/img/test/9.png"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/assets/js/libs/jquery-1.10.2.min.js"></script>
<script src="/assets/js/test/TweenMax.min.js"></script>
<script src="/assets/js/test/solarSystem.js"></script>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="https://d3js.org/d3-hsv.v0.1.min.js"></script>
<script src="https://d3js.org/d3-contour.v1.min.js"></script>
<script src="/assets/js/echarts.min.js"></script>
<script src="assets/js/echarts-gl.min.js"></script>
</body>
</html>
