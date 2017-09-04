;/*!/static/js/solarSystem.js*/
$(function() {
	var solar = (function() {
		var radius = 0,
			addX = 0,
			mouseX = mouseY = mouseZ = 0,
			container = $('#contentContainer'),
			carousel = $('#carouselContainer');


		// 获取鼠标坐标
		// 设置跟随动画变换
		function onMouseMove(event) {
			// console.log('event.pageX '+event.pageX);
			// console.log('event.pageY '+event.pageY);
			// console.log('window.innerWidth '+window.innerWidth);
			// console.log('window.innerHeight '+window.innerHeight);
			// console.log('mouseX '+mouseX);
			// console.log('mouseY '+mouseY);
			// console.log('mouseZ '+mouseZ);
			mouseX = -(-(window.innerWidth * .5) + event.pageX) * .0010;
			mouseY = -(-(window.innerHeight * .5) + event.pageY) * .05;
			mouseZ = -(radius) - (Math.abs(-(window.innerHeight * .5) + event.pageY)-100);
			// mouseZ = - (Math.abs(mouseY) * 300 - 200);
		}

		// 容器变换
		function looper() {
			// var randomY = getRandomNum(20)

			addX += mouseX;

			// TweenMax.to(target,duration,variables)
			// 让物体的属性从你声明这个方法时的状态变到任何你设定的效果
			// target:Object ：你想要实现动画的目标物体
			// duration:Number ：整个过程的时间
			// variables:Object ：一个包括最终的所有你想得到的属性
			TweenMax.to(carousel, 1, {
				rotationY: addX,
				rotationX: mouseY,
				ease: Quint.easeOut
			});

			// 设置远近景
			TweenMax.set(carousel, {
				z: mouseZ
			});
		}

		// 获取 n 位以内的随机数
		function getRandomNum(n) {
			return Math.random() * n;
		}

		// 填充星星
		function starSet(num) {
			var height = container.height(),
				width = container.width(),
				i = 0,
				divArr = [];

			var divCss = {
				'position': 'absolute',
				'width': '2px',
				'height': '2px',
				'border-radius': '2px',
				'background-color': 'rgba(255, 255, 255, 0.8)',
				'box-shadow': '0 0 10px #eee'
			}

			for (; i < num; i++) {
				var
					newElem = document.createElement('div'),
					cssTop = getRandomNum(height),
					cssLeft = getRandomNum(width),
					randomScale = getRandomNum(1.5),
					divCss = {
						'position': 'absolute',
						'z-index':'10',
						'width': '2px',
						'height': '2px',
						'border-radius': '2px',
						'background-color': 'rgba(255, 255, 255, 0.8)',
						'box-shadow': '0 0 10px #eee',
						'top': cssTop,
						'left': cssLeft,
						'transform': 'scale(' + randomScale + ')',
						'-webkit-transform': 'scale(' + randomScale + ')',
						'-moz-transform': 'scale(' + randomScale + ')'
					};

				$(newElem).css(divCss);
				divArr.push(newElem);
			}

			container.append(divArr);
		}

		// 舞台缓动
		function stageMove() {
			var stageWidth = carousel.width(),
				itemLength = $('.example').length;

			// 角度设置
			radius = Math.round( (250) / Math.tan( Math.PI / itemLength ) );
		}
		return {
			init: function() {
				// 渲染星星
				starSet(100);
				// 舞台缓动
				stageMove();
			}
		}
	})();

	solar.init();


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
})
