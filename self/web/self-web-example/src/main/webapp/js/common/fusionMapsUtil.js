/**
 * tangbiao
 * @用途：用于渲染FusionMaps的配置文件
 * 
 * ColorRange对象中保存业务中数值颜色范围： 如 ColorRange.HOUSE_MAPS_COLOR_RANGES保存的是默认的机房的地图颜色范围
 * createFusionMapsConfig() 方法为创建配置文件的方法，使用方法如下：
 * 		机房地图：
 *  	var colorRanges = ColorRange.HOUSE_MAPS_COLOR_RANGES;
 * 		
 *      var datas = [];
 *      datas.push(new FusionMapsData(XX, XX, XX, XX));
 *      datas.push(new FusionMapsData(XX, XX, XX, XX))
 *      datas.push(new FusionMapsData(XX, XX, XX, XX))
 *      
 *      var configXml = createFusionMapsConfig(colorRanges, datas);
 */

/**
 * 颜色配置模板
 * 
 * @type String
 */
var FUSIONMAPS_COLOR_RANGE_TEMPLATE = "<color minValue='{0}' maxValue='{1}' displayValue='{2}' color='{3}' />";

/**
 * 数据实体配置模板
 * 
 * @type String
 */
var FUSIONMAPS_DATA_TEMPLATE = "<entity id='{0}' displayValue='{1}' value='{2}' toolText='{3}' link='{4}'/>";

/**
 * FusionMaps地图颜色范围对象
 * 
 * @param {}
 *            minValue 范围最小值
 * @param {}
 *            maxValue 范围最大值
 * @param {}
 *            displayValue 页面显示值
 */
function FusionMapsRange(minValue, maxValue, displayValue) {
	this.minValue = minValue;
	this.maxValue = maxValue;
	this.displayValue = displayValue;
}

/**
 * FusionMaps地图数据对象
 * 
 * @param {}
 *            id
 * @param {}
 *            displayValue
 * @param {}
 *            value
 * @param {}
 *            toolText
 */
function FusionMapsData(id, displayValue, value, toolText, link) {
	this.id = id;
	this.displayValue = displayValue;
	this.value = value;
	this.toolText = toolText;
	if (link) {
		this.link = link;
	} else {
		this.link = 'javascript:void(0);';
	}
}

/**
 * 创建完整的地图配置文件
 * 
 * @param {}
 *            funsionMapsRangs FusionMapsRange对象数组,用于保存颜色数组
 * @param {}
 *            fusionMapsDatas FusionMapsData数据对象数组，用于保存数据
 *            fusionMapsRanges.push(new FusionMapsRange(0, 100, '一级'));
 *            fusionMapsRanges.push(new FusionMapsRange(100, 200, '二级'));
 *            fusionMapsRanges.push(new FusionMapsRange(200, 300, '三级'));
 *            fusionMapsRanges.push(new FusionMapsRange(300, 400, '四级'));
 * 
 * var fusionMapsDatas = []; fusionMapsDatas.push(new FusionMapsData(XX, XX, XX,
 * XX)); fusionMapsDatas.push(new FusionMapsData(XX, XX, XX, XX))
 * fusionMapsDatas.push(new FusionMapsData(XX, XX, XX, XX))
 * 
 * var configXml = createFusionMapsConfig(fusionMapsRanges, fusionMapsDatas);
 * 
 */
function createFusionMapsConfig(fusionMapsRanges, fusionMapsDatas) {
	// 创建颜色配置
	var fusionMapsConfig = '';
	var colorRangeConfig = createFusionMapsColorRangeConfig(fusionMapsRanges);
	var dataConfig = createFusionMapsDataConfig(fusionMapsDatas);
	fusionMapsConfig = MAP_STYLE + '<colorRange>' + colorRangeConfig
			+ '</colorRange>' + '<data>' + dataConfig + '</data>' + '</map>';
	return fusionMapsConfig;
}

/**
 * 创建FusionMaps颜色配置
 * 
 * @param {}
 *            fusionMapsRanges FusionMapsRange对象数组
 * @return {}
 */
function createFusionMapsColorRangeConfig(fusionMapsRanges) {
	var colorRanges = '';
	if (fusionMapsRanges != null && fusionMapsRanges instanceof Array) {
		if (fusionMapsRanges.length > 0) {
			var num = 0;
			$.each(fusionMapsRanges, function(i, fusionMapsRange) {
				if (fusionMapsRange instanceof FusionMapsRange) {
					colorRanges += FUSIONMAPS_COLOR_RANGE_TEMPLATE.format(
							fusionMapsRange.minValue, fusionMapsRange.maxValue,
							fusionMapsRange.displayValue, colorArr[num]);
					num++;
				}
			});
		}
	}
	return colorRanges;
}

/**
 * 创建FusionMaps数据配置
 * 
 * @param {}
 *            fusionMapsDatas FusionMapsRange数据数组
 */
function createFusionMapsDataConfig(fusionMapsDatas) {
	var dataConfig = '';
	if (fusionMapsDatas != null && fusionMapsDatas instanceof Array) {
		if (fusionMapsDatas.length > 0) {
			var num = 0;
			$.each(fusionMapsDatas, function(i, fusionMapsData) {
				if (fusionMapsData instanceof FusionMapsData) {
					dataConfig += FUSIONMAPS_DATA_TEMPLATE.format(
							fusionMapsData.id, fusionMapsData.displayValue,
							fusionMapsData.value, fusionMapsData.toolText,
							fusionMapsData.link);
					num++;
				}
			});
		}
	}
	return dataConfig;
}

/** ***************************************************************************************************** */

/**
 * 业务中地图范围取值
 * 
 * @type
 */
var ColorRange = {
	HOUSE_MAPS_COLOR_RANGES : [ new FusionMapsRange(0, 1, '0级'),
			new FusionMapsRange(1, 3, '1级'), new FusionMapsRange(3, 5, '2级'),
			new FusionMapsRange(5, 10, '3级'),
			new FusionMapsRange(10, 20, '4级'),
			new FusionMapsRange(20, 90000, '5级') ],
	WEBSITE_MAPS_COLOR_RANGE : [ new FusionMapsRange(0, 1, '0-1'),
			new FusionMapsRange(1, 20000, '1-2W'),
			new FusionMapsRange(20000, 50000, '2W-5W'),
			new FusionMapsRange(50000, 100000, '5W-10W'),
			new FusionMapsRange(100000, 300000, '10W-30W'),
			new FusionMapsRange(300000, 9999999999999999, '30W以上') ],
	IP_MAPS_COLOR_RANGE : [ new FusionMapsRange(0, 1, '0-1'),
			new FusionMapsRange(1, 400000, '1-40W'),
			new FusionMapsRange(400000, 800000, '40W-80W'),
			new FusionMapsRange(800000, 1200000, '80W-120W'),
			new FusionMapsRange(1200000, 1600000, '120W-160W'),
			new FusionMapsRange(1600000, 999999999999999999, '160W以上') ],
	VITUAL_HOST_MAPS_COLOR_RANGE : [ new FusionMapsRange(0, 1, '0-1'),
			new FusionMapsRange(1, 500, '1-500'),
			new FusionMapsRange(500, 1000, '501-1000'),
			new FusionMapsRange(1000, 1500, '1001-1500'),
			new FusionMapsRange(1500, 2000, '1501-2000'),
			new FusionMapsRange(2001, 9999999999999999999, '2000以上') ],
	MAINFRAME_MAPS_COLOR_RANGE : [ new FusionMapsRange(0, 1, '0-1'),
			new FusionMapsRange(1, 500, '1-500'),
			new FusionMapsRange(500, 1000, '501-1000'),
			new FusionMapsRange(1000, 1500, '1001-1500'),
			new FusionMapsRange(1500, 2000, '1501-2000'),
			new FusionMapsRange(2001, 9999999999999999999, '2000以上') ]
}

/**
 * FusionMaps地图文件路径集合
 * 
 * @type
 */
var MapsPath = {
	CN_BJ : 'js/plugs/fusionmaps/swf/CN_BJ.swf', // 北京市
	CN_TJ : 'js/plugs/fusionmaps/swf/CN_TJ.swf', // 天津市
	CN_HB : 'js/plugs/fusionmaps/swf/CN_HB.swf', // 河北省
	CN_SX : 'js/plugs/fusionmaps/swf/CN_SX.swf', // 山西省
	CN_NM : 'js/plugs/fusionmaps/swf/CN_NM.swf', // 内蒙古自治区
	CN_LN : 'js/plugs/fusionmaps/swf/CN_LN.swf', // 辽宁省
	CN_JL : 'js/plugs/fusionmaps/swf/CN_JL.swf', // 吉林省
	CN_HL : 'js/plugs/fusionmaps/swf/CN_HL.swf', // 黑龙江省
	CN_SH : 'js/plugs/fusionmaps/swf/CN_SH.swf', // 上海市
	CN_JS : 'js/plugs/fusionmaps/swf/CN_JS.swf', // 江苏省
	CN_ZJ : 'js/plugs/fusionmaps/swf/CN_ZJ.swf', // 浙江省
	CN_AH : 'js/plugs/fusionmaps/swf/CN_AH.swf', // 安徽省
	CN_FJ : 'js/plugs/fusionmaps/swf/CN_FJ.swf', // 福建省
	CN_JX : 'js/plugs/fusionmaps/swf/CN_JX.swf', // 江西省
	CN_SD : 'js/plugs/fusionmaps/swf/CN_SD.swf', // 山东省
	CN_HE : 'js/plugs/fusionmaps/swf/CN_HE.swf', // 河南省
	CN_HU : 'js/plugs/fusionmaps/swf/CN_HU.swf', // 湖北省
	CN_HN : 'js/plugs/fusionmaps/swf/CN_HN.swf', // 湖南省
	CN_GD : 'js/plugs/fusionmaps/swf/CN_GD.swf', // 广东省
	CN_GX : 'js/plugs/fusionmaps/swf/CN_GX.swf', // 广西壮族自治区
	CN_HA : 'js/plugs/fusionmaps/swf/CN_HA.swf', // 海南省
	CN_CQ : 'js/plugs/fusionmaps/swf/CN_CQ.swf', // 重庆市
	CN_SC : 'js/plugs/fusionmaps/swf/CN_SC.swf', // 四川省
	CN_GZ : 'js/plugs/fusionmaps/swf/CN_GZ.swf', // 贵州省
	CN_YN : 'js/plugs/fusionmaps/swf/CN_YN.swf', // 云南省
	CN_XZ : 'js/plugs/fusionmaps/swf/CN_XZ.swf', // 西藏自治区
	CN_SA : 'js/plugs/fusionmaps/swf/CN_SA.swf', // 陕西省
	CN_GS : 'js/plugs/fusionmaps/swf/CN_GS.swf', // 甘肃省
	CN_QH : 'js/plugs/fusionmaps/swf/CN_QH.swf', // 青海省
	CN_NX : 'js/plugs/fusionmaps/swf/CN_NX.swf', // 宁夏回族自治区
	CN_XJ : 'js/plugs/fusionmaps/swf/CN_XJ.swf', // 新疆维吾尔自治区
	CN_TA : 'js/plugs/fusionmaps/swf/CN_TA.swf', // 台湾省
	CN_HK : 'js/plugs/fusionmaps/swf/CN_HK.swf', // 香港特别行政区
	CN_MA : 'js/plugs/fusionmaps/swf/CN_MA.swf' // 澳门特别行政区
}

function getMapPath() {
	var areaCode = PROVINCE_AREA_CODE;
	if (areaCode == '110000') {
		return MapsPath.CN_BJ;
	} else if (areaCode == '120000') {
		return MapsPath.CN_TJ;
	} else if (areaCode == '130000') {
		return MapsPath.CN_HB;
	} else if (areaCode == '140000') {
		return MapsPath.CN_SX;
	} else if (areaCode == '150000') {
		return MapsPath.CN_NM;
	} else if (areaCode == '210000') {
		return MapsPath.CN_LN;
	} else if (areaCode == '220000') {
		return MapsPath.CN_JL;
	} else if (areaCode == '230000') {
		return MapsPath.CN_HL;
	} else if (areaCode == '310000') {
		return MapsPath.CN_SH;
	} else if (areaCode == '320000') {
		return MapsPath.CN_JS;
	} else if (areaCode == '330000') {
		return MapsPath.CN_ZJ;
	} else if (areaCode == '340000') {
		return MapsPath.CN_AH;
	} else if (areaCode == '350000') {
		return MapsPath.CN_FJ;
	} else if (areaCode == '360000') {
		return MapsPath.CN_JX;
	} else if (areaCode == '370000') {
		return MapsPath.CN_SD;
	} else if (areaCode == '410000') {
		return MapsPath.CN_HE;
	} else if (areaCode == '420000') {
		return MapsPath.CN_HU;
	} else if (areaCode == '430000') {
		return MapsPath.CN_HN;
	} else if (areaCode == '440000') {
		return MapsPath.CN_GD;
	} else if (areaCode == '450000') {
		return MapsPath.CN_GX;
	} else if (areaCode == '460000') {
		return MapsPath.CN_HA;
	} else if (areaCode == '500000') {
		return MapsPath.CN_CQ;
	} else if (areaCode == '510000') {
		return MapsPath.CN_SC;
	} else if (areaCode == '520000') {
		return MapsPath.CN_GZ;
	} else if (areaCode == '530000') {
		return MapsPath.CN_YN;
	} else if (areaCode == '540000') {
		return MapsPath.CN_XZ;
	} else if (areaCode == '610000') {
		return MapsPath.CN_SA;
	} else if (areaCode == '620000') {
		return MapsPath.CN_GS;
	} else if (areaCode == '630000') {
		return MapsPath.CN_QH;
	} else if (areaCode == '640000') {
		return MapsPath.CN_NX;
	} else if (areaCode == '650000') {
		return MapsPath.CN_XJ;
	} else if (areaCode == '710000') {
		return MapsPath.CN_TA;
	} else if (areaCode == '810000') {
		return MapsPath.CN_HK;
	} else if (areaCode == '820000') {
		return MapsPath.CN_MA;
	}
}

MapsPath.CURRENT_MAP = getMapPath();
