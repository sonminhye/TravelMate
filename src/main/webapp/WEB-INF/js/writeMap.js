var count = 0;
var mapContainer = document.getElementById('map'),
mapOption = {
	center : new daum.maps.LatLng(37.55393836433871, 126.97119796121817),
	level : 7
};

var map = new daum.maps.Map(mapContainer, mapOption);

daum.maps.event.addListener(map, 'click', function(mouseEvent) {
	addMarker(mouseEvent.latLng);
});

var markers = [];

function addMarker(position) {
	var marker = new daum.maps.Marker({
		position : position
	});

	marker.setMap(map);
		
	var iwContent = '<div id=' + 'loc_' +  count + ' style="padding:5px;">' + (count + 1) + '번째 장소</div>',
    iwPosition = new daum.maps.LatLng(posLat, posLng);

	var infowindow = new daum.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	  
	infowindow.open(map, marker);

	markers.push(marker);
	count++;

	var pos = marker.getPosition();
	var posLat = pos.getLat();
	var posLng = pos.getLng();
	var span = document.createElement('span');
	span.id = count;
	var hiddenInput1 = document.createElement('input');
	var hiddenInput2 = document.createElement('input');
	var textInput3 = document.createElement('input');
	var hiddenInput4 = document.createElement('input');
	var countIdx = count - 1;
	hiddenInput1.name = "trlist[" + countIdx + "].lat";
	hiddenInput2.name = "trlist[" + countIdx + "].lng";
	textInput3.name = "trlist[" + countIdx + "].location";
	hiddenInput4.name = "trlist[" + countIdx + "].locOrder";

	hiddenInput1.type = "hidden";
	hiddenInput2.type = "hidden";
	textInput3.type = "text";
	textInput3.placeholder = count + "번째 장소명";
	textInput3.size = 10;
	textInput3.maxlength = 10;
	hiddenInput4.type = "hidden";
	
	hiddenInput1.value = posLat;
	hiddenInput2.value = posLng;
	textInput3.value = '';
	hiddenInput4.value = countIdx;

	document.getElementById("add").appendChild(span);
	document.getElementById(count).appendChild(hiddenInput1);
	document.getElementById(count).appendChild(hiddenInput2);
	document.getElementById(count).appendChild(textInput3);
	document.getElementById(count).appendChild(hiddenInput4);
	document.getElementById(count).innerHTML += ("<button onclick='deleteAction(this);'>X</button>");
}

function deleteMarker(idx) {
	var removeMark = markers.splice(idx - 1, 1);
	var hideMark = removeMark[0];

	if (hideMark == undefined) {
		alert("더 이상 없음");
	}
	else {
		hideMark.setVisible(false);
	}
}

function setMarkers(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

function deleteAction(object) {
	var deleteId = object.parentNode.id;
	var needUpdates = document.getElementById("add").childNodes;
	for (var i = 0; i < needUpdates.length; i++) {
		if (deleteId - 1 < i) {
			needUpdates[i].id = i;
			var nu = needUpdates[i].childNodes;
			var nuIdx = i - 1;
			nu[0].name = "trlist[" + nuIdx + "].lat";
			nu[1].name = "trlist[" + nuIdx + "].lng";
			nu[2].name = "trlist[" + nuIdx + "].location";
			nu[2].placeholder = (nuIdx + 1) + "번째 장소명"
			nu[3].name = "trlist[" + nuIdx + "].locOrder";
			nu[3].value = nuIdx;
			
			document.getElementById("loc_"+i).innerHTML = i + "번째장소";
			document.getElementById("loc_"+i).id = "loc_" + nuIdx;
		}
	}
	
	document.getElementById(deleteId).remove();
	$("#loc_"+(deleteId-1)).parent().parent().remove();
	
	deleteMarker(deleteId);
	count--;
}