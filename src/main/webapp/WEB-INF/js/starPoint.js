$(".star_rating a").click(function() {
	$(this).parent().children("a").removeClass("on");
	$(this).prevAll("a").addClass("on");
	$(this).addClass("on");
	var pointNum = this.id;
	var point = pointNum.split("point")[1];
	$("input[name=point]").val(point);
});