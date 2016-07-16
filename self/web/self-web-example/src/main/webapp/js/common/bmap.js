//提交数据是清楚缓存 针对IE提交缓存问题
$.ajaxSetup({cache:false});

var colors = [{range:'0',color:'#00ff00'},
              {range:'0-0.05',color:'#fdfd2a'},
	          {range:'0.05-0.1',color:'#fc6216'},
	          {range:'0.1-0.25',color:'#fc1616'},
	          {range:'0.25-0.6',color:'#c80501'},
	          {range:'0.6-1.0',color:'#800200'}];

function getColor(percent){
	for (var i = 0; i < colors.length; i++) {
		var range = colors[i].range.split("-");
		if(range.length  == 2){
			if (percent > range[0] && percent <= range[1]) {
				return colors[i].color;
			}
		}else{
			if (percent == range[0]) {
				return colors[i].color;
			}
		}
	}
	return "#00ff00";
}

/**************************************浙江配置**********************************/
/*
//初始化地图展示中心坐标位置
var centerPointLat = 120.175311;//经度
var centerPointLng = 30.256561;//纬度
//视野范围坐标点位置
var visionLeftLat = 119.276502;
var visionLeftLng = 27.988112;
var visionRightLat = 122.808783;
var visionRightLng = 30.822994;
//省内各地市坐标
var sPoints = [{city:"杭州市",point:new BMap.Point(120.149878, 30.300263)},//杭州
               {city:"嘉兴市",point:new BMap.Point(120.756989, 30.770079)},//嘉兴
               {city:"湖州市",point:new BMap.Point(120.10438, 30.884502)},//湖州
               {city:"绍兴市",point:new BMap.Point(120.582214, 30.020478)},//绍兴
               {city:"宁波市",point:new BMap.Point(121.557271, 29.900325)},//宁波
               {city:"舟山市",point:new BMap.Point(122.201177, 30.004466)},//舟山
               {city:"衢州市",point:new BMap.Point(118.871266, 28.96622)},//衢州
               {city:"金华市",point:new BMap.Point(119.643952, 29.103656)},//金华
               {city:"丽水市",point:new BMap.Point(119.919912, 28.479678)},//丽水
               {city:"温州市",point:new BMap.Point(120.692598, 28.015356)},//温州
               {city:"台州市",point:new BMap.Point(121.410093, 28.67457)}//台州
               ];
*/
/**************************************浙江配置**********************************/

/**************************************北京配置**********************************/
//初始化地图展示中心坐标位置
var centerPointLat = 116.4;//经度
var centerPointLng = 39.9;//纬度
//视野范围坐标点位置
var visionLeftLat = 115.2;//左上角点经度
var visionLeftLng = 38.9;//左上角纬度
var visionRightLat = 117.5;//右上角点经度
var visionRightLng = 40.9;//右上角点纬度
//省内各地市坐标
var sPoints = [{city:"北京市",point:new BMap.Point(116.4, 39.9)},
               {city:"天安门",point:new BMap.Point(116.38, 39.9)},
               {city:"东城区",point:new BMap.Point(116.42, 39.93)},
               {city:"西城区",point:new BMap.Point(116.37, 39.92)},
               {city:"崇文区",point:new BMap.Point(116.43, 39.88)},
               {city:"宣武区",point:new BMap.Point(116.35, 39.87)},
               {city:"朝阳区",point:new BMap.Point(116.43, 39.92)},
               {city:"丰台区",point:new BMap.Point(116.28, 39.85)},
               {city:"石景山区",point:new BMap.Point(116.22, 39.9)},
               {city:"海淀区",point:new BMap.Point(116.3, 39.95)},
               {city:"门头沟区",point:new BMap.Point(116.1, 39.93)},
               {city:"房山区",point:new BMap.Point(116.13, 39.75)},
               {city:"通州区",point:new BMap.Point(116.65, 39.92)},
               {city:"顺义区",point:new BMap.Point(116.65, 40.13)},
               {city:"昌平区",point:new BMap.Point(116.23, 40.22)},
               {city:"大兴区",point:new BMap.Point(116.33, 39.73)},
               {city:"怀柔区",point:new BMap.Point(116.63, 40.32)},
               {city:"平谷区",point:new BMap.Point(117.12, 40.13)},
               {city:"密云县",point:new BMap.Point(116.83, 40.37)},
               {city:"延庆县",point:new BMap.Point(115.97, 40.45)}
               ];
/**************************************北京配置**********************************/

function getPoint(city){
	for (var i = 0; i < sPoints.length; i++) {
		if (sPoints[i].city == city) {
			return sPoints[i].point;
		}
	}
	return null;
}

function addArrow(map,polyline,length,angleValue){ //绘制箭头的函数  
	var linePoint = polyline.getPath();//线的坐标串  
	var arrowCount = linePoint.length;  
	for(var i = 1; i < arrowCount; i ++){ //在拐点处绘制箭头  
		var pixelStart = map.pointToPixel(linePoint[i-1]);  
		var pixelEnd = map.pointToPixel(linePoint[i]);  
		var angle = angleValue;//箭头和主线的夹角  
		var r = length; // r/Math.sin(angle)代表箭头长度  
		var delta = 0; //主线斜率，垂直时无斜率  
		var param = 0; //代码简洁考虑  
		var pixelTemX,pixelTemY;//临时点坐标  
		var pixelX,pixelY,pixelX1,pixelY1;//箭头两个点  
		if(pixelEnd.x - pixelStart.x == 0){ //斜率不存在是时  
			pixelTemX = pixelEnd.x;  
			if(pixelEnd.y > pixelStart.y){
				pixelTemY = pixelEnd.y - r;  
			}else{
				pixelTemY = pixelEnd.y + r;
			}  
			//已知直角三角形两个点坐标及其中一个角，求另外一个点坐标算法  
			pixelX = pixelTemX - r * Math.tan(angle);   
			pixelX1 = pixelTemX + r*Math.tan(angle);  
			pixelY = pixelY1 = pixelTemY;  
		}else{
			//斜率存在时 
			delta=(pixelEnd.y-pixelStart.y)/(pixelEnd.x-pixelStart.x);  
			param=Math.sqrt(delta*delta+1);  
			if((pixelEnd.x-pixelStart.x)<0){
				//第二、三象限  
				pixelTemX=pixelEnd.x+ r/param;  
				pixelTemY=pixelEnd.y+delta*r/param;
			}else{
				//第一、四象限
	    	pixelTemX=pixelEnd.x- r/param;  
	    	pixelTemY=pixelEnd.y-delta*r/param;  
			}  
			//已知直角三角形两个点坐标及其中一个角，求另外一个点坐标算法  
			pixelX=pixelTemX+ Math.tan(angle)*r*delta/param;  
			pixelY=pixelTemY-Math.tan(angle)*r/param;  
            pixelX1=pixelTemX- Math.tan(angle)*r*delta/param;  
            pixelY1=pixelTemY+Math.tan(angle)*r/param;  
		}  
	  
		var pointArrow=map.pixelToPoint(new BMap.Pixel(pixelX,pixelY));  
		var pointArrow1=map.pixelToPoint(new BMap.Pixel(pixelX1,pixelY1));  
		var Arrow = new BMap.Polyline([pointArrow,linePoint[i],pointArrow1],
				{strokeColor:"blue", strokeWeight:3, strokeOpacity:1});  
		map.addOverlay(Arrow);  
	}  
}  
