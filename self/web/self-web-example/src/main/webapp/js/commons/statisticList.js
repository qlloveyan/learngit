function createCommonTablePaiming(result,tableId){
	// 创建数量显示表格
 	var xData = [];
  	var yData = [];
  	//迭代，把异步获取的数据放到数组中
  	$.each(result,function(i,d){
	  	xData.push(d[0]);
  		yData.push(d[1]);
  	});
  	
  	createTableByPage(tableId, xData, yData, 11);
}


/**
 * 
 * @param {} divId 区块最外层ID
 * @param {} firstColData 第一列的数据数组
 * @param {} secColData 第二列的数据数组
 * @param {} pageSize 每页条数
 */
function createTableByPage(divId, firstColData, secColData ,pageSize){
	// 创建数据表格
	$('#'+divId+' div').text('');
	var html = '<ul style="dispaly:block;">';
	if(firstColData && firstColData.length>0){
		var dataNum = 0;
		$.each(firstColData, function(i, d){
			var sortCss="sort sort-blue";
			if(i==0){
				sortCss="sort sort-orange";
			}else if(i==1){
				sortCss="sort sort-green";
			}else if(i==2){
				sortCss="sort sort-acidblue";
			}
			html += '<li>' +
						'<span class="jczy-data-font font-more"><i class="'+sortCss+'">'+(i+1)+'</i>'+firstColData[i]+'</span>'+
				        '<span class="jczy-data-num" id='+secColData[i]+'>'+secColData[i]+'</span>' +
	        		'</li>';
	        
	        // 省市县每页只显示11条数据，模拟分页
        	dataNum ++;
        	if(dataNum%pageSize == 0){
        		html += '</ul><ul>';
        	}
		});
		html += '</ul>';
	}
	
	$('#'+divId+' div').append(html);
	// 创建分页
	createPageBtn(divId, firstColData.length, pageSize);
}



/**
 * 创建分页圆点
 * @param {} dataLength
 */
function createPageBtn(divId, dataLength, pageSize){
	$('#'+divId+' h3.jczy-data-more').empty();
	if(dataLength > pageSize){
		var pageNo = (dataLength - (dataLength % pageSize)) / pageSize;
		if(pageNo >= 1){
			$('#'+divId+' h3.jczy-data-more').append('<span class="data-more-hover"></span>');
			var num = 0;
			while(pageNo>num){
				num ++;
				$('#'+divId+' h3.jczy-data-more').append('<span></span>');
			}
			//基础资源右边数据切换
			$('#'+divId+' div ul').hide();
			$('#'+divId+' div ul:first').show();
			$('#'+divId+' .jczy-data-more span').bind("click",function(){
		 	$('#'+divId+' .jczy-data-more span').removeClass("data-more-hover");
				$(this).addClass("data-more-hover");
				$('#'+divId+' div ul').hide().eq($('#'+divId+' .jczy-data-more span').index(this)).fadeIn("slow"); 
			});
		}
	}
}