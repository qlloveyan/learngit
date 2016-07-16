/*
 * 使用此js时必须导入constants.js
 */
/**
 * 根据时间维度，创建对应的趋势图
 * @param {} timeRange 时间维度(年：YEAR_COUNT, 季度：QUARTER_COUNT, 月：MONTH_COUNT,周：WEEK_COUNT,日：DAY_COUNT)
 * @param {} divId 渲染层ID
 * @param {} titleText 标题文字
 * @param {} data 数据
 */
function createHighStock(timeRange, divId, titleText, data, nameText){
	nameText = nameText || '';
	if(timeRange == YEAR_COUNT){
		createYearHighStock(divId, titleText, data, nameText);
	}else if(timeRange == QUARTER_COUNT){
		createQuarterHighStock(divId, titleText, data, nameText);
	}else if(timeRange == MONTH_COUNT){
		createMonthHighStock(divId, titleText, data, nameText);
	}else if(timeRange == WEEK_COUNT){
		createWeekHighStock(divId, titleText, data, nameText);
	}else{
		createDayHighStock(divId, titleText, data, nameText);
	}
}

function createYearHighStock(divId, titleText, data, nameText){
	$('#'+divId).highcharts('StockChart', {
				
				/**
                 * 版权信息配置，不用修改直接复制
                 *
                 * @param {boolean} enabled 是否显示版权信息
                 * @param {string} href 版权信息所链接到的地址
                 * @param {string} text 版权信息所显示的文字内容
                 */
                credits:{
                    enabled: false,
                    href: "www.1218.com.cn",
                    text: '任子行网络技术有限公司'
                },
				
                /**
                 * 时间维度
                 * 定义一组buttons,下标从0开始  
                 *
                 */
				rangeSelector : {
					inputDateFormat : '%Y',
					//inputEnabled: false,
					buttons : [],
					selected : 5
				},

				/**
                 * X轴坐标配置
                 *
                 * @param {object} dateTimeLabelFormats x轴日期时间格式化，不用修改直接使用
                 */
				xAxis : {
					// 如果X轴刻度是日期或时间，该配置是格式化日期及时间显示格式
                    dateTimeLabelFormats: {
                        second: '%H:%M:%S',
                        minute: '%H:%M',
                        hour: '%H:%M',
                        day: '%Y',
                        week: '%Y',
                        month: '%Y',
                        year: '%Y'
                    },
                    minTickInterval: 24 * 3600 * 1000 * 365
				},
				
				yAxis: {
		            labels: {
		                formatter: function() {
		                	return getLableByKey(this.value);
		                }
		            }
		        },
				
				/**
                 * 下方导航栏的X轴坐标配置
                 *
                 */
				navigator : {
					xAxis: {
						dateTimeLabelFormats: {
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%Y',
                            week: '%Y',
                            month: '%Y',
                            year: '%Y'
                        }
					}
				},

				title : {
					text : titleText
				},

				tooltip : {
					xDateFormat : '%Y' //鼠标移动到趋势线上时显示的日期格式  
				},

				series : [ {
					name : nameText,
					data : data,
					type : 'area',
					threshold : null,
					fillColor : {
						linearGradient : {
							x1: 0, 
							y1: 0, 
							x2: 0, 
							y2: 1
						},
						stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
					}
				} ]
			});
}

function createQuarterHighStock(divId, titleText, data, nameText){
	$('#'+divId).highcharts('StockChart', {
				
				/**
                 * 版权信息配置，不用修改直接复制
                 *
                 * @param {boolean} enabled 是否显示版权信息
                 * @param {string} href 版权信息所链接到的地址
                 * @param {string} text 版权信息所显示的文字内容
                 */
                credits:{
                    enabled: false,
                    href: "www.1218.com.cn",
                    text: '任子行网络技术有限公司'
                },
				
                /**
                 * 时间维度
                 * 定义一组buttons,下标从0开始  
                 *
                 */
				rangeSelector : {
					//inputEnabled: false,
					inputDateFormat : '%Y-%m',
					buttons : [],
					selected : 5
				},

				/**
                 * X轴坐标配置
                 *
                 * @param {object} dateTimeLabelFormats x轴日期时间格式化，不用修改直接使用
                 */
				xAxis : {
					// 如果X轴刻度是日期或时间，该配置是格式化日期及时间显示格式
                    dateTimeLabelFormats: {
                        second: '%H:%M:%S',
                        minute: '%H:%M',
                        hour: '%H:%M',
                        day: '%Y<br/>%m',
                        week: '%Y<br/>%m',
                        month: '%Y-%m',
                        year: '%Y'
                    },
                    minTickInterval: 24 * 3600 * 1000 * 28
				},
				
				yAxis: {
		            labels: {
		                formatter: function() {
		                	return getLableByKey(this.value);
		                }
		            }
		        },
				
				/**
                 * 下方导航栏的X轴坐标配置
                 *
                 */
				navigator : {
					xAxis: {
						dateTimeLabelFormats: {
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%Y-%m',
                            week: '%Y-%m',
                            month: '%Y-%m',
                            year: '%Y'
                        }
					}
				},

				title : {
					text : titleText
				},

				tooltip : {
					xDateFormat : '%Y-%m' //鼠标移动到趋势线上时显示的日期格式  
				},

				series : [ {
					name : nameText,
					data : data,
					type : 'area',
					threshold : null,
					fillColor : {
						linearGradient : {
							x1: 0, 
							y1: 0, 
							x2: 0, 
							y2: 1
						},
						stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
					}
				} ]
			});
}


/**
 * 创建 月 趋势图
 * @param {} divId 渲染层ID
 * @param {} titleText 标题
 * @param {} data 数据
 */
function createMonthHighStock(divId, titleText, data, nameText){
	$('#'+divId).highcharts('StockChart', {
				
				/**
                 * 版权信息配置，不用修改直接复制
                 *
                 * @param {boolean} enabled 是否显示版权信息
                 * @param {string} href 版权信息所链接到的地址
                 * @param {string} text 版权信息所显示的文字内容
                 */
                credits:{
                    enabled: false,
                    href: "www.1218.com.cn",
                    text: '任子行网络技术有限公司'
                },
				
                /**
                 * 时间维度
                 * 定义一组buttons,下标从0开始  
                 *
                 */
				rangeSelector : {
					inputDateFormat : '%Y-%m',
					buttons : [],
					selected : 5
				},

				/**
                 * X轴坐标配置
                 *
                 * @param {object} dateTimeLabelFormats x轴日期时间格式化，不用修改直接使用
                 */
				xAxis : {
					// 如果X轴刻度是日期或时间，该配置是格式化日期及时间显示格式
                    dateTimeLabelFormats: {
                        second: '%H:%M:%S',
                        minute: '%H:%M',
                        hour: '%H:%M',
                        day: '%Y<br/>%m',
                        week: '%Y<br/>%m',
                        month: '%Y-%m',
                        year: '%Y'
                    },
                    minTickInterval: 24 * 3600 * 1000 * 28
				},
				
				yAxis: {
		            labels: {
		                formatter: function() {
		                	return getLableByKey(this.value);
		                }
		            }
		        },
				
				/**
                 * 下方导航栏的X轴坐标配置
                 *
                 */
				navigator : {
					xAxis: {
						dateTimeLabelFormats: {
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%Y-%m',
                            week: '%Y-%m',
                            month: '%Y-%m',
                            year: '%Y'
                        }
					}
				},

				title : {
					text : titleText
				},

				tooltip : {
					xDateFormat : '%Y-%m' //鼠标移动到趋势线上时显示的日期格式  
				},

				series : [ {
					name : nameText,
					data : data,
					type : 'area',
					threshold : null,
					fillColor : {
						linearGradient : {
							x1: 0, 
							y1: 0, 
							x2: 0, 
							y2: 1
						},
						stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
					}
				} ]
			});
}

function createWeekHighStock(divId, titleText, data, nameText){
	$('#'+divId).highcharts('StockChart', {
				
				/**
                 * 版权信息配置，不用修改直接复制
                 *
                 * @param {boolean} enabled 是否显示版权信息
                 * @param {string} href 版权信息所链接到的地址
                 * @param {string} text 版权信息所显示的文字内容
                 */
                credits:{
                    enabled: false,
                    href: "www.1218.com.cn",
                    text: '任子行网络技术有限公司'
                },
				
                /**
                 * 时间维度
                 * 定义一组buttons,下标从0开始  
                 *
                 */
				rangeSelector : {
					inputDateFormat : '%Y-%m-%d',
					buttons : [],
					selected : 5
				},

				/**
                 * X轴坐标配置
                 *
                 * @param {object} dateTimeLabelFormats x轴日期时间格式化，不用修改直接使用
                 */
				xAxis : {
					// 如果X轴刻度是日期或时间，该配置是格式化日期及时间显示格式
                    dateTimeLabelFormats: {
                        second: '%H:%M:%S',
                        minute: '%H:%M',
                        hour: '%H:%M',
                        day: '%Y<br/>%m-%d',
                        week: '%Y<br/>%m-%d',
                        month: '%Y-%m',
                        year: '%Y'
                    },
                    minTickInterval: 24 * 3600 * 1000 * 7
				},
				
				yAxis: {
		            labels: {
		                formatter: function() {
		                	return getLableByKey(this.value);
		                }
		            }
		        },
				
				/**
                 * 下方导航栏的X轴坐标配置
                 *
                 */
				navigator : {
					xAxis: {
						dateTimeLabelFormats: {
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%Y-%m-%d',
                            week: '%Y-%m-%d',
                            month: '%Y-%m',
                            year: '%Y'
                        }
					}
				},

				title : {
					text : titleText
				},

				tooltip : {
					xDateFormat : '%Y-%m-%d' //鼠标移动到趋势线上时显示的日期格式  
				},

				series : [ {
					name : nameText,
					data : data,
					type : 'area',
					threshold : null,
					fillColor : {
						linearGradient : {
							x1: 0, 
							y1: 0, 
							x2: 0, 
							y2: 1
						},
						stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
					}
				} ]
			});
}

/**
 * 创建 天 趋势图
 * @param {} divId 渲染层ID
 * @param {} titleText 标题
 * @param {} data 数据
 */
function createDayHighStock(divId, titleText, data, nameText){
	$('#'+divId).highcharts('StockChart', {
				
				/**
                 * 版权信息配置，不用修改直接复制
                 *
                 * @param {boolean} enabled 是否显示版权信息
                 * @param {string} href 版权信息所链接到的地址
                 * @param {string} text 版权信息所显示的文字内容
                 */
                credits:{
                    enabled: false,
                    href: "www.1218.com.cn",
                    text: '任子行网络技术有限公司'
                },
				
                /**
                 * 时间维度
                 * 定义一组buttons,下标从0开始  
                 *
                 */
				rangeSelector : {
					inputDateFormat : '%Y-%m-%d',
					buttons : [],
					selected : 5
				},

				/**
                 * X轴坐标配置
                 *
                 * @param {object} dateTimeLabelFormats x轴日期时间格式化，不用修改直接使用
                 */
				xAxis : {
					// 如果X轴刻度是日期或时间，该配置是格式化日期及时间显示格式
                    dateTimeLabelFormats: {
                        second: '%H:%M:%S',
                        minute: '%H:%M',
                        hour: '%H:%M',
                        day: '%Y<br/>%m-%d',
                        week: '%Y<br/>%m-%d',
                        month: '%Y-%m',
                        year: '%Y'
                    },
                    minTickInterval: 24 * 3600 * 1000
				},
				
				yAxis: {
					tickInterval: 1,
			        max:4,
			        min: 0,
		            labels: {
		                formatter: function() {
		                	return getLableByKey(this.value);
		                }
		            }
		        },
				
				/**
                 * 下方导航栏的X轴坐标配置
                 *
                 */
				navigator : {
					xAxis: {
						dateTimeLabelFormats: {
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%Y-%m-%d',
                            week: '%Y-%m-%d',
                            month: '%Y-%m',
                            year: '%Y'
                        }
					}
				},

				title : {
					text : titleText
				},

				tooltip : {
					xDateFormat : '%Y-%m-%d' //鼠标移动到趋势线上时显示的日期格式  
				},

				series : [ {
					name : nameText,
					data : data,
					type : 'area',
					threshold : null,
					fillColor : {
						linearGradient : {
							x1: 0, 
							y1: 0, 
							x2: 0, 
							y2: 1
						},
						stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
					}
				} ]
			});
}

/**
 * 创建 天 趋势图
 * @param {} divId 渲染层ID
 * @param {} titleText 标题
 * @param {} data 数据
 */
function createDayHighStock2(divId, titleText, data, nameText){
	$('#'+divId).highcharts('StockChart', {
				
				/**
                 * 版权信息配置，不用修改直接复制
                 *
                 * @param {boolean} enabled 是否显示版权信息
                 * @param {string} href 版权信息所链接到的地址
                 * @param {string} text 版权信息所显示的文字内容
                 */
                credits:{
                    enabled: false,
                    href: "www.1218.com.cn",
                    text: '任子行网络技术有限公司'
                },
				
                /**
                 * 时间维度
                 * 定义一组buttons,下标从0开始  
                 *
                 */
				rangeSelector : {
					inputDateFormat : '%Y-%m-%d',
					buttons : [],
					selected : 5
				},

				/**
                 * X轴坐标配置
                 *
                 * @param {object} dateTimeLabelFormats x轴日期时间格式化，不用修改直接使用
                 */
				xAxis : {
					// 如果X轴刻度是日期或时间，该配置是格式化日期及时间显示格式
                    dateTimeLabelFormats: {
                        second: '%H:%M:%S',
                        minute: '%H:%M',
                        hour: '%H:%M',
                        day: '%Y<br/>%m-%d',
                        week: '%Y<br/>%m-%d',
                        month: '%Y-%m',
                        year: '%Y'
                    },
                    minTickInterval: 24 * 3600 * 1000
				},
				
				yAxis: {
					tickInterval: 1,
			        max:6,
			        min: 0,
		            labels: {
		                formatter: function() {
		                	return getLableByKey2(this.value);
		                }
		            }
		        },
				
				/**
                 * 下方导航栏的X轴坐标配置
                 *
                 */
				navigator : {
					xAxis: {
						dateTimeLabelFormats: {
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%Y-%m-%d',
                            week: '%Y-%m-%d',
                            month: '%Y-%m',
                            year: '%Y'
                        }
					}
				},

				title : {
					text : titleText
				},

				tooltip : {
					xDateFormat : '%Y-%m-%d' //鼠标移动到趋势线上时显示的日期格式  
				},

				series : [ {
					name : nameText,
					data : data,
					type : 'area',
					threshold : null,
					fillColor : {
						linearGradient : {
							x1: 0, 
							y1: 0, 
							x2: 0, 
							y2: 1
						},
						stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
					}
				} ]
			});
}

function getLableByKey(value){
	if(value==0){
		return '正常';
	}else if(value==1){
		return '连接接口异常';
	}else if(value==2){
		return '企业未上报';
	}else if(value==3){
		return '数据不正确';
	}else{
		return value;
	}
}

function getLableByKey2(value){
	if(value==0){
		return '正常';
	}else if(value==1){
		return '连接接口异常';
	}else if(value==2){
		return '企业未上报';
	}else if(value==3){
		return '数据不正确';
	}else if(value==4){
		return '冻结未生效';
	}else if(value==5){
		return '取消冻结未生效';
	}else{
		return value;
	}
}