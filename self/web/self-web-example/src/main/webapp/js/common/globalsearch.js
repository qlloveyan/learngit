/*********************************
 * 用于全局搜索:目前只针对当前用户所拥有的菜单搜索
 * @author wgh
 ********************************/

var dataArray = new Array();
function showdata(label,value,url,frameName,showvalue,id){
	var obj =  new Object();
	obj.label = label;
	obj.value = value;
	obj.url = url;
	obj.frameName = frameName;
	obj.showvalue = showvalue;
	obj.id = id;
	return obj;
}

function redirect(){
	var type = $('#myselect').val();
	if(type == "菜单"){
		if($('#globalsearch').attr("url")!=""){
			$("#globalsearch").removeClass("input-warning");
			var temp=$('#globalsearch').attr("url");
			toPage(temp, $('#globalsearch').attr("framename"));
			return;
		}else{
			$("#globalsearch").addClass("input-warning");
		}
//		console.info($('#globalsearch').attr("url"));
	}else if(type == "网页"){
		var url = "system/redirect/redirectHomePage.do?path=br/websiteContentSearch";
		var keyword = $.trim($('#globalsearch').val());
		if(keyword != null && keyword != ''){
			url += ('&keyword=' + encodeURI(encodeURIComponent(keyword)));
		}
		//alert(url);
		toPage(url, "f_WebsiteContentSearch");
	}else if(type == "业务"){
		var text=$("#globalsearch").val();
		if(!text){
			$("#globalsearch").addClass("input-warning");
			return;
		}
		
		//跳到IP溯源
		var ipRegex = "^([1-9]|([1-9][0-9])|(1[0-9]{2})|(2([0-4][0-9])|(5[0-5])))\\.(([0-9]|([1-9][0-9])|(1[0-9]{2})|(2([0-4][0-9])|(5[0-5])))\\.){2}([1-9]|([1-9][0-9])|(1[0-9]{2})|(2([0-4][0-9])|(5[0-5])))$";
		var reg = new RegExp(ipRegex);
		var path="system/redirect/redirectHomePage.do?path=is/ipHistory&ip="+text;
		if(reg.test(text)){
			$("#globalsearch").removeClass("input-warning");
			toPage(path, "f_ipHistory");
			return;
		}
		var domainReg="^[www\\.]?([a-z0-9\\-]{1,62}\\.){1,6}[a-z]{2,4}$";
		reg = new RegExp(domainReg);
		path="system/redirect/redirectHomePage.do?path=is/domainHistory_baseinfo&domain="+text;
		if(reg.test(text)){
			$("#globalsearch").removeClass("input-warning");
			toPage(path, "f_domainHistory_baseinfo");
			return;
		}
		$("#globalsearch").addClass("input-warning");
	}
}

$(function() {
	var type = $('#myselect').val();
	if(type == "菜单"){
		$.ajax({	
			url:"system/redirect/getkeyWordMenu.do",
			type:'post',
			listStyle: 'custom',
			dataType:'json',
			success: function(data){
//				console.log(eval(data.result));
				$.each(data.result,function(index,keyword){
					//去除网安菜单
					if(keyword.frameName.indexOf("f_NS")>=0){
						return;
					}
					var valuetemp = keyword.simplePinyin+keyword.fullPinyin+keyword.word;
					var showdatatemp = new showdata(keyword.word,valuetemp,keyword.url,keyword.frameName,keyword.word,keyword.id);
					dataArray[index]=showdatatemp;
					//dataMap.put(keyword.fullPinyin+keyword.simplePinyin+keyword.word,keyword.word);
					$('#globalsearch').AutoComplete({
						data :dataArray,
						itemHeight: 20,
						width: 'auto',
						maxHeight: 100,
						afterSelectedHandler: function(data) {
							//alert(data.url + ";" + data.frameName);
							toPage(data.url, data.frameName,data.id);
						},
					}).AutoComplete('show');
				});
				
			}
		});
	}
	
	//点击select效果模拟
	$(".search-drop input").click(function(e){
		e?e.stopPropagation():event.cancelBubble = true;
	});
	
	$(".search-drop input").click(function(){
		 var X = $('#myselect').offset().top;
		 var Y = $('#myselect').offset().left;
		 $(".searchTop-drop").css("top",X);
		 $(".searchTop-drop").css("left",Y);
		 $(".searchTop-drop").show();
	})
	
	
	
	//function aa(){
	  $(document).click(function() { //点击除指定input外，隐藏UL
	    $(".searchTop-drop").hide();
	  });
	//}
	//$.parser.parse(aa);
	
	
	 $(".searchTop-drop li").click(function(){
			 var $name = $(this).text();
			 $(".select-input").attr("value","");
			 $(".select-input").attr("value",$name);
			 $(".searchTop-drop").hide();
			 var type = $('#myselect').val();
				if(type == "菜单"){
					$.ajax({	
						url:"system/redirect/getkeyWordMenu.do",
						type:'post',
						listStyle: 'custom',
						dataType:'json',
						success: function(data){
//						3		console.log(eval(data.result));
							$.each(data.result,function(index,keyword){
								//去除网安菜单
//								if(keyword.frameName.indexOf("f_NS")>=0){
//									return;
//								}
								var valuetemp = keyword.simplePinyin+keyword.fullPinyin+keyword.word;
								var showdatatemp = new showdata(keyword.word,valuetemp,keyword.url,keyword.frameName,keyword.word,keyword.id);
								dataArray[index]=showdatatemp;
								//dataMap.put(keyword.fullPinyin+keyword.simplePinyin+keyword.word,keyword.word);
								$('#globalsearch').AutoComplete({
									data :dataArray,
									itemHeight: 20,
									width: 'auto',
									maxHeight: 100,
									afterSelectedHandler: function(data) {
										//alert(data.url + ";" + data.frameName);
										toPage(data.url, data.frameName,data.id);
									},
								}).AutoComplete('show');
							});
							
						}
					});
				}else if(type == "网页"){
					$('#globalsearch').AutoComplete("destroy");
				}else if(type == "业务"){
					$('#globalsearch').AutoComplete("destroy");
				}
	})
	
	$("#btnglobalsearch").click(function(){
		redirect();
	});
	
	$("#globalsearch").keypress(function (e) {
		var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
		if (keyCode == 13){
			redirect();
			e.preventDefault();
		}
	});
});
