/* 
 * @Author	: Song Ji Yong
 * @Date	: 2017. 06. 05
 * @Modify	: 2017. 06. 17
 * @Details	: 2017. 06. 17 - comment 추가
 */

function getToday() {
	var today = new Date();
	var year = today.getFullYear();
	var month = new String(today.getMonth() + 1);
	var day = new String(today.getDate());
	// 한 자리 수 일때 0채우기
	if (month.length == 1) {
		month = "0" + month;
	}
	if (day.length == 1) {
		day = "0" + day;
	}
	// console.log(year + " " + month + " " + day);
	var now = year + "-" + month + "-" + day;
	return now;
}

function getDiffDay(sday, now) {
	var start = sday;
	var nowArr = now.split("-");
	var sDateArr = start.split("-");
	var todayObj = new Date(nowArr[0], Number(nowArr[1]) - 1, nowArr[2]);
	var sObj = new Date(sDateArr[0], Number(sDateArr[1]) - 1, sDateArr[2]);
	var between = (todayObj.getTime() - sObj.getTime()) / (1000 * 60 * 60 * 24);
	var result = "";
	if (between > 0) {
		result = "D+" + between;
	}
	else {
		result = "D" + between;
	}
	return result;
}