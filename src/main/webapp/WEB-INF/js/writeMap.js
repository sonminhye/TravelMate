var count = 0;
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
mapOption = {
	center : new daum.maps.LatLng(37.55393836433871, 126.97119796121817), // 지도의 중심좌표
	level : 7
// 지도의 확대 레벨
};

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도를 클릭했을때 클릭한 위치에 마커를 추가하도록 지도에 클릭이벤트를 등록합니다
daum.maps.event.addListener(map, 'click', function(mouseEvent) {
	// 클릭한 위치에 마커를 표시합니다
	addMarker(mouseEvent.latLng);
});

// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
var markers = [];

// 마커 하나를 지도위에 표시합니다
// addMarker(new daum.maps.LatLng(33.450701, 126.570667));

// 마커를 생성하고 지도위에 표시하는 함수입니다
function addMarker(position) {

	// 마커를 생성합니다
	var marker = new daum.maps.Marker({
		position : position
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);

	// 생성된 마커를 배열에 추가합니다
	markers.push(marker);
	count++;
	// 하단에 좌표 값을 등록
	var pos = marker.getPosition();
	var posLat = pos.getLat();
	var posLng = pos.getLng();
	var span = document.createElement('span');
	span.id = count;
	var hiddenInput1 = document.createElement('input');
	var hiddenInput2 = document.createElement('input');
	var hiddenInput3 = document.createElement('input');
	var hiddenInput4 = document.createElement('input');
	var countIdx = count - 1;
	hiddenInput1.name = "trlist[" + countIdx + "].lat";
	hiddenInput2.name = "trlist[" + countIdx + "].lng";
	hiddenInput3.name = "trlist[" + countIdx + "].location";
	hiddenInput4.name = "trlist[" + countIdx + "].locOrder";

	hiddenInput1.type = "hidden";
	hiddenInput2.type = "hidden";
	hiddenInput3.type = "hidden";
	hiddenInput4.type = "hidden";
	
	hiddenInput1.value = posLat;
	hiddenInput2.value = posLng;
	hiddenInput3.value = '테스트장소명';
	hiddenInput4.value = countIdx;

	document.getElementById("add").appendChild(span);
	document.getElementById(count).appendChild(hiddenInput1);
	document.getElementById(count).appendChild(hiddenInput2);
	document.getElementById(count).appendChild(hiddenInput3);
	document.getElementById(count).appendChild(hiddenInput4);
	document.getElementById(count).innerHTML += ("<span>"
			+ count + "</span>번째 장소" + "<button onclick='deleteAction(this);'>X</button>");
}

// 마커 삭제
function deleteMarker(idx) {
	var removeMark = markers.splice(idx - 1, 1);
	var hideMark = removeMark[0];

	if (hideMark == undefined) {
		alert("더 이상 없음");
	} else {
		// 마지막 마커 안보이게 하고
		hideMark.setVisible(false);
	}
}

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

// x 버튼
function deleteAction(object) {
	var deleteId = object.parentNode.id;
	// id 와 count가 같으므로 현재 id(count)보다 큰 것들은 하나씩 감소시키고
	// id 보다 작은것들은 그대로
	var needUpdates = document.getElementById("add").childNodes;
	for (var i = 0; i < needUpdates.length; i++) {
		// 1(id)번째는 0(i)
		// 2번째는 1
		// 3번째는 2
		// 4번째는 3
		//
		// 3(deleteId)번째를 지운다면 deleteId-1인 i를 포함한 i보다 큰 값을 모두 업데이트

		if (deleteId - 1 < i) {
			// id update
			needUpdates[i].id = i;
			// innerhtml update
			// span id update
			var nu = needUpdates[i].childNodes;
			var nuIdx = i - 1;
			nu[0].name = "trlist[" + nuIdx + "].lat";
			nu[1].name = "trlist[" + nuIdx + "].lng";
			nu[2].name = "trlist[" + nuIdx + "].location";
			nu[3].name = "trlist[" + nuIdx + "].locOrder";
			nu[3].value = nuIdx;
			nu[4].innerHTML = i;
		}
	}
	// add 밑에 작성한 좌표 지우고
	document.getElementById(deleteId).remove();
	// 마커 삭제
	deleteMarker(deleteId);
	// count 감소
	count--;
}