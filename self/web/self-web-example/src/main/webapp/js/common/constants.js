/**
 * 访问项目的名称
 * @type String
 */
var PROJECT_NAME = '/self_web';
/***************** 代码表 ********************/
//是否可操作
var DICTIONARY_IS_OPERATE = 'IS_OPERATE';
//用户状态
var DICTIONARY_USER_STATE = 'USER_STATE';


/***************** 代码表 ********************/

/**
 * 为地市添加全部选项
 */
var AREAALLVALUE = {
	'areaCode' : "",
	'areaName' : '全部'
};

/**
 * 为数据字典添加全部选项
 */
var ALLVALUE = {
	'codeKey' : "",
	'codeValue' : '全部'
};
/*
 * 数据直接导出上限
 */
var LIMIT_EXPORT = 100;
/*
 * 所在省
 */
var PROVINCE = 1;
/*
 * 所在市
 */
var CITY = 2;
/*
 * 所在县
 */
var COUNTY = 3;

/********************导出相关常量start***********************/
/*
 * 数据直接导出上限
 */
var LIMIT_EXPORT = 10;

/*
 * excel导出上限
 */
var LIMIT_EXCEL = 1000;

/*
 * 后台导出阀值（超过此阀值执行后台导出 csv格式）
 */
var LIMIT_BACK = 100;

/*
 * 数据导出分批数量系数
 */
var BATCH_COUNT = 1000;
/********************导出相关常量end*************************/

/************************************************************* 地市地图样式常量 *************************************/
var MAP_STYLE = "<map  baseFontSize='11' baseFontColor='363636' fillAlpha='100' hoverColor='CAE7F0' BorderColor='ffffff' showBevel='0' " + " formatNumberScaleBoolean='0' showShadow='0' animation='1' legendScrollBtnColor='ffffff' showCanvasBorder='0' showToolTipShadow='0' toolTipBorderColor='ffffff' showLegend='0' legendBorderThickness='0' legendBorderColor='ffffff' legendShadow='0'>";

var colorArr = ['#A4EDFF', '#56D9FF', '#01C0F7', '#169EFF', '#0082DE', '#006DBA'];

//地市编码设置
var PROVINCE_AREA_CODE = 420000;

//地市编码设置
var PROVINCE_AREA_CODE_HZ = 420100;
//地市编码设置
var PROVINCE_AREA_CODE_LIKE = 42;

/********************************************************地市地图样式常量*******************************************/