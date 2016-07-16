/*
 * 构建饼状图
 */
function createPieCharts(divId, value, title, text, tooltip){
    var title = title || '';
	var text = text || '';
	var tooltip = tooltip || '';
	var chart = new Highcharts.Chart({
         	chart: {
	         	renderTo: divId,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            credits:{
                enabled: false,
                href: "www.1218.com.cn",
                text: '任子行网络技术有限公司'
            },
            title: {
                text: title
            },
            plotOptions: {
            	pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    format: tooltip
	                },
	                showInLegend: true
	            }
	        },
            series: [{
                type: 'pie',
                name: text,
                data:value
            }]
        });

}



/**
 * 创建曲线图
 * @param {} divId 需要渲染的DIV的id
 * @param {} dateArray s
 * @param {} data 后台传入的数据
 * @param {} title 柱状图标题
 * @param {} yAxisText Y轴显示的文字
 * @param {} tooltip 鼠标移动到显示提示tip
 */
function createLineCharts(divId, dateArray,datas, title, yAxisText, tooltip){
	var title = title || '';
	var yAxisText = yAxisText || '';
	var tooltip = tooltip || '';
	//数据组合
	  $('#'+divId).highcharts({
	      chart: {
	          type: 'spline'
	      },
	      title: {
	          text: title
	      },
	      credits:{
              enabled: false,
              href: "www.1218.com.cn",
              text: '任子行网络技术有限公司'
          },
	      xAxis: {
	    	  categories: dateArray
	      },
	      yAxis: {
	            title: {
	                text: yAxisText
	            },
	            labels: {
	                formatter: function() {
	                    return this.value
	                }
	            }
	        },
	      tooltip: {
	          valueSuffix: tooltip
	      },
	      plotOptions: {
	          spline: {
	              lineWidth: 3,
	              marker: {
	                  enabled: false
	              }
	          }
	      },
	      series: datas,
	      navigation: {
	          menuItemStyle: {
	              fontSize: '10px'
	          }
	      }
	  });
}

function createStock(divId,seriesOptions, titleText) {
	var titleText=titleText || '';
	$('#'+divId).highcharts('StockChart', {
	    chart: {
	    },

	    rangeSelector: {
	        selected: 4
	    },
	    credits:{
            enabled: false,
            href: "www.1218.com.cn",
            text: '任子行网络技术有限公司'
        },
        title : {
			text : titleText
		},
	    yAxis: {
	    	labels: {
	    		formatter: function() {
	    			return  this.value + '%';
	    		}
	    	},
	    	plotLines: [{
	    		value: 0,
	    		width: 2,
	    		color: 'silver'
	    	}]
	    },
	    rangeSelector : {
			inputDateFormat : '%Y-%m-%d',
			buttons : [],
			selected : 5
		},
	    plotOptions: {
	    	series: {
	    		compare: 'percent'
	    	}
	    },
	    
	    tooltip: {
	    	pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
	    	valueDecimals: 2
	    },
	    
	    series: seriesOptions
	});
}


function createColumnChart(divId,title,xDataArray,seriesDataArray){
	 $('#'+divId).highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: title
	        },
	        xAxis: {
	            categories:xDataArray
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: ''
	            }
	        },
	        tooltip: {
	            formatter: function() {
	                return this.y;
	            }
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: seriesDataArray
	    });
}
