/* 
 * @Author	: Song Ji Yong
 * @Date	: 2017. 06. 11
 * @Modify	: 2017. 06. 17
 * @Details	: 2017. 06. 17 - comment 추가
 */

function fileCheck(obj) {
	var filePoint = obj.value.lastIndexOf(".");
	var fileName = obj.value.substring(filePoint + 1, obj.length);
	var fileType = fileName.toLowerCase();
	var maxSize = 2 * 1024 * 1024;
	var fileSize = obj.files[0].size;
	
	if (fileSize > maxSize) {
		alert("2MB 이하의 파일만 가능합니다");
		$("#image").val("");
		return false;
	}
	
	if (!(fileType == "jpg" || fileType == "gif" || fileType == "png" || fileType == "jpeg" || fileType == "bmp")) {
		alert("이미지 파일(jpg, gif, png, jpeg, bmp)만 선택 가능합니다");
		$("#image").val("");
		return false;
	}
}

function startDateCheck(obj) {
	var now = new Date();
	var usDate = obj.value;
	var ueDate = $("#endDate").val();
	var temps = usDate.split("-");
	var tempe = ueDate.split("-");
	var usersDate = new Date(Number(temps[0]), Number(temps[1]) - 1, Number(temps[2]));
	var usereDate = new Date(Number(tempe[0]), Number(tempe[1]) - 1, Number(tempe[2]));
	
	if (usereDate <= usersDate) {
		alert("시작날짜가 종료날짜와 같거나  늦을 수 없습니다");
		$("#startDate").val("");
	}
	else {
		if (usersDate > now) {
			;
		}
		else {
			alert("오늘이거나 오늘보다 이전 날짜는 선택할 수 없습니다");
			$("#startDate").val("");
		}	
	}
}

function endDateCheck(obj) {
	var sDate = $("#startDate").val();
	var eDate = obj.value;
	if (sDate == "" || sDate == null) {
		alert("시작날짜를 먼저 선택해주세요");
		$("#endDate").val("");
	}
	else {
		if (sDate >= eDate) {
			alert("시작날짜이거나 시작날짜보다 이전 날짜는 선택할 수 없습니다");
			$("#endDate").val("");
		}
		else {
			;
		}
	}
}

function maxPeopleCheck(obj) {
	var min = parseInt($("#minPeople").val());
	var max = parseInt(obj.value);
	if (isNaN(min)) {
		alert("최소인원을 먼저 선택해주세요");
		$("#maxPeople").val("");
	}
	else {
		if (max < min) {
			alert("최대인원은 최소인원보다 작을 수 없습니다");
			$("#maxPeople").val("");
		}
		else {
			;
		}
	}
}

function minPeopleCheck(obj) {
	var min = parseInt(obj.value);
	var max = parseInt($("#maxPeople").val());
	if (isNaN(max)) {
		;
	}
	else {
		if (max < min) {
			alert("최소인원은 최대인원보다 클 수 없습니다")
			$("#minPeople").val("");
		}
		else {
			;
		}
	}
}