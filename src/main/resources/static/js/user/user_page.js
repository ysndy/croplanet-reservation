<!-- swiper -->
const swiper_image = new Swiper('#swiper_image', {
    // Optional parameters
    direction: 'horizontal',
    loop: true,

    // If we need pagination
    pagination: {
        el: '#swiper_image_pagination',
        type: 'fraction',
    },
    on: {
        slideChange: function () {
            $.ajax({
                url: "/users/swipe",
                type: "POST"
            })
        }
    }

});

const swiper_survey = new Swiper('#swiper_survey', {
    // Optional parameters
    direction: 'horizontal',
    loop: true,

    // If we need pagination
    pagination: {
        el: '#swiper_survey_pagination',
        type: 'fraction',
    },

    // Navigation arrows
    navigation: {
        nextEl: '#swiper_survey_next',
        prevEl: '#swiper_survey_prev',
    },

});

<!-- 카카오 관련 -->
Kakao.init('ffb1ac4b290c42a760a2af6a06801cba'); // 사용하려는 앱의 JavaScript 키 입력

function share(query){
    $.ajax({
        url: "/users/share",
        type: "POST"
    })

    // 현재 주소 가져오기
    var currentURL = document.location.href;

    // Clipboard API를 사용하여 현재 주소를 클립보드에 복사
    var tempInput = document.createElement("input");
    tempInput.value = currentURL+"?query="+query;
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

    /**
     * 1. 카카오 로그인
     * 2. 해당 카카오 ID가 사전예약 DB에 있는지 조회
     * 2-1. 없으면) 사전예약 DB에 저장
     * 3. 사전예약 대기 목록 뷰를 반환.
     */

    $.ajax({
        url: "/users/basket",
        type: "GET"
    })

    //사용자 닉네임 확인
    Kakao.API.request({
        url: '/v1/api/talk/profile',
    })
        .then(function(response) {
            console.log(response);
        })
        .catch(function(error) {
            console.log(error);
        });

}

function buy(){
    $.ajax({
        url: "/users/buy",
        type: "POST"
    });

    modalBackDrop();

}

function modalBackDrop(){

    const bottomSheet = document.getElementById("bottomSheet");
    const modalBackdrop = document.getElementById("modalBackdrop");

    bottomSheet.classList.toggle("active");
    modalBackdrop.style.display = modalBackdrop.style.display === "block" ? "none" : "block";

    // 바텀시트를 아래로 스와이프하여 닫기
    let startY, endY;

    //터치 이벤트 등록
    bottomSheet.addEventListener("touchstart", function (e) {
        startY = e.touches[0].clientY;
    });

    bottomSheet.addEventListener("touchmove", function (e) {
        endY = e.touches[0].clientY;
    });

    bottomSheet.addEventListener("touchend", function () {
        if (startY < endY && bottomSheet.classList.contains("active")) {
            buy();
        }
    });

    //마우스 이벤트 등록
    bottomSheet.addEventListener("mousedown", function (e) {
        startY = e.clientY;
    });

    bottomSheet.addEventListener("mousemove", function (e) {
        endY = e.clientY;
    });

    bottomSheet.addEventListener("mouseup", function () {
        if (startY < endY && bottomSheet.classList.contains("active")) {
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

