$(function (){
    $.get("header3.html",function (data) {
        $("#header3").html(data);
    });
	$.get("header1.html",function (data) {
	    $("#header1").html(data);
	});
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
});