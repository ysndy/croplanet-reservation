<!-- 카카오 관련 -->
Kakao.init('ffb1ac4b290c42a760a2af6a06801cba'); // 사용하려는 앱의 JavaScript 키 입력

function share(){
    $.ajax({
        url: "/users/share",
        type: "POST"
    })

    // 현재 주소 가져오기
    var currentURL = document.location.href;

    // Clipboard API를 사용하여 현재 주소를 클립보드에 복사
    var tempInput = document.createElement("input");
    tempInput.value = currentURL+"?query=ASDASD";
    document.body.appendChild(tempInput);
    tempInput.select();
    document.execCommand("copy");
    document.body.removeChild(tempInput);

    alert("링크가 복사되었습니다.");

}

function like_top(){
    $.ajax({
        url: "/users/like_top",
        type: "POST"
    })
}

function like_bottom(){
    $.ajax({
        url: "/users/like_bottom",
        type: "POST"
    })
}

function reservation() {

    //채널 추가 되었는지 확인
    Kakao.API.request({
        url: '/v1/api/talk/channels',
    })
        .then(function(response) {
            console.log(response);
        })
        .catch(function(error) {
            console.log(error);
        });

    //채널 추가
    Kakao.Channel.addChannel({
        channelPublicId: '_xavxjMs',
    });
    //
}

function buy(){
    $.ajax({
        url: "/users/buy",
        type: "POST"
    })
    const bottomSheet = document.getElementById("bottomSheet");
    const modalBackdrop = document.getElementById("modalBackdrop");

    bottomSheet.classList.toggle("active");
    modalBackdrop.style.display = modalBackdrop.style.display === "block" ? "none" : "block";

    // 바텀시트를 아래로 스와이프하여 닫기
    let startY, endY;

    bottomSheet.addEventListener("touchstart", function (e) {
        startY = e.touches[0].clientY;
    });

    bottomSheet.addEventListener("touchmove", function (e) {
        endY = e.touches[0].clientY;
    });

    bottomSheet.addEventListener("touchend", function () {
        if (startY > endY && bottomSheet.classList.contains("active")) {
            buy();
        }
    });

}

function post(){
    $.ajax({
        url: "/users/post",
        type: "POST"
    })
}

<!-- 스와이프 관련 코드 -->
var slideIndex = 0;
showSlides(slideIndex);

var slideshowContainer = document.getElementById('slideshow');
var slideNumberElement = document.getElementById('slide-number');

// 스와이프 이벤트 감지
var startX, startY, endX, endY;
slideshowContainer.addEventListener('touchstart', function (e) {
    startX = e.touches[0].clientX;
    startY = e.touches[0].clientY;
});

slideshowContainer.addEventListener('touchend', function (e) {
    endX = e.changedTouches[0].clientX;
    endY = e.changedTouches[0].clientY;
    handleSwipe();
});

// 스와이프로 슬라이드 변경
function handleSwipe() {
    var deltaX = startX - endX;
    var deltaY = startY - endY;
    var sensitivity = 50; // 스와이프 감도 (조절 가능)

    if (Math.abs(deltaX) > sensitivity && Math.abs(deltaX) > Math.abs(deltaY)) {
        if (deltaX > 0) {
            // 오른쪽에서 왼쪽으로 스와이프 (다음 슬라이드 표시)
            plusSlides(1);
        } else {
            // 왼쪽에서 오른쪽으로 스와이프 (이전 슬라이드 표시)
            plusSlides(-1);
        }
    }
}

// 이전/다음 슬라이드 표시 함수
function plusSlides(n) {
    showSlides(slideIndex += n);
    $.ajax({
        url: "/users/swipe",
        type: "POST"
    })
}

// 현재 슬라이드 인덱스 설정 및 슬라이드 표시 함수
function showSlides(n) {
    var slides = document.getElementsByClassName("mySlides");
    if (n >= slides.length) {
        slideIndex = 0;
    }
    if (n < 0) {
        slideIndex = slides.length - 1;
    }
    for (var i = 0; i < slides.length; i++) {

        //slides[i].style.left = "-100%"; // 모든 이미지를 왼쪽으로 숨김
        slides[i].style.display = "none";
    }
    slides[slideIndex].style.display = "block";
    //slides[slideIndex].style.left = "0"; // 현재 슬라이드를 보이도록 left 속성 변경

    // 현재 사진 번호와 전체 사진 갯수 업데이트
    if(slideNumberElement != undefined) {
        slideNumberElement.textContent = (slideIndex + 1) + " / " + slides.length;
    }
}

<!-- 스크롤 관련 -->

// 스크롤 이벤트 리스너 등록
window.addEventListener('scroll', function() {
    // 현재 스크롤 위치
    var scrollY = window.scrollY;
    // 문서의 전체 높이
    var documentHeight = document.documentElement.scrollHeight;
    // 브라우저의 뷰포트 높이
    var windowHeight = window.innerHeight;

    // 스크롤이 끝까지 내려갔을 때 POST 요청 보내기
    if (scrollY + windowHeight >= documentHeight) {
        $.ajax({
            url : "/users/scroll_bottom",
            type: "POST"
        });
    }
});

