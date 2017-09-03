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
    <style>
        img{
            width:200px;
            height: 300px;
        }
        @keyframes fn{
            0% {transform:rotateX(-15deg) rotateY(0deg);}
            100%{transform:rotateX(-15deg) rotateY(360deg);}

        }
        @-moz-keyframes fn{
            0% {transform:rotateX(-15deg) rotateY(0deg);}
            100%{transform:rotateX(-15deg) rotateY(360deg);}
        }
        @-ms-keyframes fn{
            0% {transform:rotateX(-15deg) rotateY(0deg);}
            100%{transform:rotateX(-15deg) rotateY(360deg);}
        }
        @-webkit-keyframes fn{
            0% {transform:rotateX(-15deg) rotateY(0deg);}
            100%{transform:rotateX(-15deg) rotateY(360deg);}
        }
        .box{
            width:200px;
            height:300px;
            right: 100px;
            position:relative;
            transform-style:preserve-3d;
            transform:rotateX(-10deg);
            animation: fn 10s;
            animation-iteration-count: infinite;
            animation-timing-function: linear;
            position: relative;
            z-index: 10001;
        }
        .example:hover{
            animation-play-state: paused;
        }
        .box:hover{
            animation-play-state: paused;
        }
        .box>div{
            position: absolute;
            left:0;
            top:-700px;
        }
    </style>
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
                    <div class="x" id="thoughtBubble">
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
                        <a href="#"><img src="/assets/img/test/1.png" ></a>
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
                        <a href="#"><img src="/assets/img/test/6.png" ></a>
                    </div>
                    <div id="img7" style="transform: rotateY(240deg) translateZ(275px);">
                        <img src="/assets/img/test/7.png">
                    </div>
                    <div id="img8" style="transform: rotateY(280deg) translateZ(275px);">
                        <img src="/assets/img/test/8.png">
                    </div>
                    <div id="img9" style="transform: rotateY(320deg) translateZ(275px);">
                        <a href="#"><img src="/assets/img/test/9.png"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 3d照片墙 -->

</div>

<script src="/assets/js/libs/jquery-1.10.2.min.js"></script>
<script src="/assets/js/test/TweenMax.min.js"></script>
<script src="/assets/js/test/solarSystem.js"></script>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="https://d3js.org/d3-hsv.v0.1.min.js"></script>
<script src="https://d3js.org/d3-contour.v1.min.js"></script>
<script src="/assets/js/echarts.min.js"></script>
<script src="assets/js/echarts-gl.min.js"></script>
<script type="text/javascript" src='/assets/js/test/jquery.thoughtBubble.min.js'></script>
<script>
    $('.demo-perspective-photo,#contentContainer').css('height',$(window).height());
    $('.example6').hover(function () {
        $('#img1').slideUp(500);
        $('#img1').slideDown(500);
    })
    $('.example4').hover(function () {
        $('#img6').slideUp(500);
        $('#img9').slideUp(500);
        $('#img6').slideDown(500)
        $('#img9').slideDown(500);
    })
</script>
<script>

    var config = {
        color: '#ecdd40',
        levels: 1,
        intensity: 5,
        threshold: 0.01
    }

    var canvas = document.createElement('canvas');
    canvas.width = 4096;
    canvas.height = 2048;
    context = canvas.getContext("2d");

    context.lineWidth = 0.5;
    context.strokeStyle = config.color;
    context.fillStyle = config.color;
    context.shadowColor = config.color;

    image('/assets/img/test/data-1491837512042-rJlLfXYax.jpg').then(function(image) {
        var m = image.height,
            n = image.width,
            values = new Array(n * m),
            contours = d3.contours().size([n, m]).smooth(true),
            projection = d3.geoIdentity().scale(canvas.width / n),
            path = d3.geoPath(projection, context);

//   StackBlur.R(image, 5);

        for (var j = 0, k = 0; j < m; ++j) {
            for (var i = 0; i < n; ++i, ++k) {
                values[k] = image.data[(k << 2)] / 255;
            }
        }

        var opt = {
            image: canvas
        }

        var results = [];
        function update(threshold, levels) {
            context.clearRect(0, 0, canvas.width, canvas.height);
            var thresholds = [];
            for (var i = 0; i < levels; i++) {
                thresholds.push((threshold + 1 / levels * i) % 1);
            }
            results = contours.thresholds(thresholds)(values);
            redraw();
        }

        function redraw() {
            results.forEach(function (d, idx) {
                context.beginPath();
                path(d);
                context.globalAlpha = 1;
                context.stroke();
                if (idx > config.levels / 5 * 3) {
                    context.globalAlpha = 0.01;
                    context.fill();
                }
            });
            opt.onupdate();
        }
        d3.timer(function(t) {
            var threshold = (t % 10000) / 10000;
            update(threshold, 1);
        });

        initCharts(opt);

        update(config.threshold, config.levels);

    });

    function image(url) {
        return new Promise(function(resolve) {
            var image = new Image;
            image.src = url;
            image.onload = function() {
                var canvas = document.createElement("canvas");
                canvas.width = image.width / 8;
                canvas.height = image.height / 8;
                var context = canvas.getContext("2d");
                context.drawImage(image, 0, 0, canvas.width, canvas.height);
                resolve(context.getImageData(0, 0, canvas.width, canvas.height));
            };
        });
    }

    function initCharts(opt) {
        var contourChart = echarts.init(document.createElement('canvas'), null, {
            width: 4096,
            height: 2048
        });

        var img = new echarts.graphic.Image({
            style: {
                image: opt.image,
                x: -1,
                y: -1,
                width: opt.image.width + 2,
                height: opt.image.height + 2
            }
        });
        contourChart.getZr().add(img);

        opt.onupdate = function () {
            img.dirty();
        }
        myChart=echarts.init(document.getElementById("chart"));
        myChart.setOption({
            backgroundColor: '#000000',
            globe: {

                environment: '#000000',

                baseTexture: '/assets/img/test/data-1491890179041-Hkj-elqpe.jpg',
                heightTexture: '/assets/img/test/data-1491889019097-rJQYikcpl.jpg',

                displacementScale: 0.05,
                displacementQuality: 'high',


                shading: 'realistic',
                realisticMaterial: {
                    roughness: 0.2,
                    metalness: 0
                },

                postEffect: {
                    enable: true,
                },

                light: {
                    ambient: {
                        intensity: 0
                    },
                    main: {
                        intensity: 0.1,
                        shadow: false
                    },
                    ambientCubemap: {
                        texture: '/assets/img/test/data-1491837984109-r1u7NmY6e.hdr',
                        exposure: 1,
                        diffuseIntensity: 0.5,
                        specularIntensity: 2
                    }
                },
                viewControl: {
                    autoRotate: true,
                    zoomSensitivity:0,
                    distance:300
                },

                layers: [{
                    type: 'blend',
                    blendTo: 'emission',
                    texture: contourChart,
                    intensity: config.intensity
                } ,{
                    type: 'overlay',
                    texture: '/assets/img/test/data-1491890092270-BJEhJg96l.png',
                    shading: 'lambert',
                    distance: 5
                }]
            },
            series: [{
                type: 'lines3D',
                effect: {
                    show: true,
                    trailWidth: 2,
                    trailLength: 0.15,
                    trailOpacity: 1,
                    trailColor: 'rgb(30, 30, 60)'
                },

                lineStyle: {
                    width: 1,
                    color: 'rgb(50, 50, 150)',
                    // color: 'rgb(118, 233, 241)',
                    opacity: 0.1
                },
                blendMode: 'lighter',

            }]
        });
    }
</script>
</body>
</html>
