$(function(){
    var width=document.documentElement.clientWidth;
    var height=document.documentElement.clientHeight;
    $("#map").width(width-100);
   $("#map,.content_box").height(height-79-55);
    //头部动画
    setTimeout(function () {
        $(".headline").show().addClass("zoomInDown")
    },500);
    //左边动画
    setTimeout(function () {
        $(".event_bg").show().addClass("a-fadeinL");
    },1000);

    setTimeout(function () {
    	$(".event_list1 li").each(function(i){
            var len=$(".event_list li").length;
            $(this).delay(i*(2000/len)).fadeIn();
        });
        $(".event_list2 li").each(function(i){
            var len=$(".event_list li").length;
            $(this).delay(i*(2000/len)).fadeIn();
        })
        $(".event_list1,.event_list2").niceScroll({
            touchbehavior:false,
            cursorborderradius:0,
            cursorcolor:"#acabab",
            cursorwidth:8
           // autohidemode:false    //true为隐藏
        });
    },1300);
    
  //下面隐藏
    setTimeout(function () {
        $(".problem").css("bottom","-127px");
        $(".slide_bottom").show().addClass("a-flipinX").css("top","-165px");
    },6000);
    
    $("#slide_bottom").toggle(
        function () {
            $(".problem").css("bottom","0px");
            $(this).find("div").removeClass("arrow_t");
            $(this).find("div").addClass("arrow_b");
        },
        function () {
            $(".problem").css("bottom","-127px");
            $(this).find("div").removeClass("arrow_b");
            $(this).find("div").addClass("arrow_t");
        }
    );


    //右边动画
    setTimeout(function () {
        $(".jc_bg").show().addClass("a-fadeinR");
    },1200);

    setTimeout(function () {
        $(".jc_head").show().addClass("a-fadeinR");
        $(".jc_head2").show().addClass("a-fadeinR");
    },1400);

    setTimeout(function () {
        $(".jc_list1 li").each(function(i){
            var len=$(".jc_list1 li").length;
            $(this).delay(i*(2000/len)).fadeIn();
        });
        $(".jc_list2 li").each(function(i){
            var len=$(".jc_list2 li").length;
            $(this).delay(i*(2000/len)).fadeIn();
        });
        $(".jc_list1,.jc_list2").niceScroll({
            touchbehavior:false,
            cursorborderradius:0,
            cursorcolor:"#acabab",
            cursorwidth:8
           // autohidemode:false    //true为隐藏
        });
    },1700);

    //底部动画
    setTimeout(function () {
        $(".pro_bg").show().addClass("a-bounceinB");
    },1500);

    setTimeout(function () {
        $(".pro_head").show().addClass("a-bounceinB");
    },1800);

  setTimeout(function () {
        $(".pro_list li").each(function(i){
            var len=$(".pro_list li").length;
            $(this).delay(i*(4000/len)).fadeIn();
        })
    },1500);

    setTimeout(function () {
        $(".sea_bg").show().addClass("a-bounceinL");
    },2500);
    setTimeout(function () {
        $(".arrow_l").show();
        //箭头点击事件

    },2600);
    setTimeout(function () {
    	$(".top_input").show().addClass("a-bounceinL");
        $(".sea_btn").show().addClass("a-bounceinR");
    },2700);

    //中间地图
//    setTimeout(function () {
//        $("#map").show().addClass("a-bouncein");
//    },8000);

    setTimeout(function () {
        $(".top_input").show().addClass("a-bounceinL");
        $(".sea_btn").show().addClass("a-bounceinR");
    },6000);
    //左边隐藏
    setTimeout(function () {
        $(".left1").css("left","-260px");
//        $(".left2").css("left","-260px");
        $(".slide_left").show().addClass("a-flipinX").css("right","-20px");
    },6000);
    $("#slide_left1").toggle(
        function () {
            $(".left1").css("left","0px");
            $(this).find("div").removeClass("arrow_r");
            $(this).find("div").addClass("arrow_l");
        },
        function () {
            $(".left1").css("left","-260px");
            $(this).find("div").removeClass("arrow_l");
            $(this).find("div").addClass("arrow_r");
        }
    );
//    $("#slide_left2").toggle(
//            function () {
//                $(".left2").css("left","0px");
//                $(this).find("div").removeClass("arrow_r");
//                $(this).find("div").addClass("arrow_l");
//            },
//            function () {
//                $(".left2").css("left","-260px");
//                $(this).find("div").removeClass("arrow_l");
//                $(this).find("div").addClass("arrow_r");
//            }
//        );

    //右边隐藏
    setTimeout(function () {
        $(".right1").css("right","-282px");
        $(".right2").css("right","-282px");
        $(".slide_right").show().addClass("a-flipinX").css("right","-21px");
    },6000);
    
    $("#slide_right1").click(function(){
    	if($(this).find("div").attr("class")=="arrow_l"){
   		 $(".right1").css("right","0px");
            $(this).find("div").removeClass("arrow_l");
            $(this).find("div").addClass("arrow_r");
    	}else{
    		 $(".right1").css("right","-282px");
             $(this).find("div").removeClass("arrow_r");
             $(this).find("div").addClass("arrow_l");
    	}
    });
    
    $("#slide_right2").click(function(){
    	if($(this).find("div").attr("class")=="arrow_l"){
   		 $(".right2").css("right","0px");
            $(this).find("div").removeClass("arrow_l");
            $(this).find("div").addClass("arrow_r");
    	}else{
    		 $(".right2").css("right","-282px");
             $(this).find("div").removeClass("arrow_r");
             $(this).find("div").addClass("arrow_l");
    	}
    });
    
//    $("#slide_right1").toggle(
//        function () {
//            $(".right1").css("right","0px");
//            $(this).find("div").removeClass("arrow_l");
//            $(this).find("div").addClass("arrow_r");
//        },
//        function () {
//            $(".right1").css("right","-282px");
//            $(this).find("div").removeClass("arrow_r");
//            $(this).find("div").addClass("arrow_l");
//        }
//    );
//    $("#slide_right2").toggle(
//        function () {
//            $(".right2").css("right","0px");
//            $(this).find("div").removeClass("arrow_l");
//            $(this).find("div").addClass("arrow_r");
//        },
//        function () {
//            $(".right2").css("right","-282px");
//            $(this).find("div").removeClass("arrow_r");
//            $(this).find("div").addClass("arrow_l");
//        }
//    );

    // tab切换

    $(".fir_li").click(function(){
        $("#pro_list1").css("display","block");
        $("#pro_list2").css("display","none");
        $(".pro_head").removeClass("pro_head2");

    })
    $(".last_li").click(function(){
        $("#pro_list1").css("display","none");
        $("#pro_list2").css("display","block");
        $(".pro_head").addClass("pro_head2");

    })

})
