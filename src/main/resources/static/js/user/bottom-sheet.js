// Copyright (c) 2022 Ivan Teplov

const $$ = document.querySelector.bind(document)

const openSheetButton = $$("#open-sheet")
const sheet = $$("#sheet")
const sheetContents = sheet.querySelector(".contents")
const draggableArea = sheet.querySelector(".draggable-area")
const body = document.body;

let sheetHeight // in vh

const setSheetHeight = (value) => {
    sheetHeight = Math.max(0, Math.min(100, value))
    sheetContents.style.height = `${sheetHeight}vh`

    if (sheetHeight === 100) {
        sheetContents.classList.add("fullscreen")
    } else {
        sheetContents.classList.remove("fullscreen")
    }
}

const setIsSheetShown = (value) => {
    sheet.setAttribute("aria-hidden", String(!value))

    if (value) {
        body.style.overflow = "hidden";
    } else {
        body.style.overflow = "auto";
    }

}

// Open the sheet when clicking the 'open sheet' button
openSheetButton.addEventListener("click", () => {
    setSheetHeight(Math.min(60, 720 / window.innerHeight * 100))
    setIsSheetShown(true)
})

// Hide the sheet when clicking the 'close' button
sheet.querySelector(".close-sheet").addEventListener("click", () => {
    setIsSheetShown(false)
})

// Hide the sheet when clicking the background
sheet.querySelector(".overlay").addEventListener("click", () => {
    setIsSheetShown(false)
})

const touchPosition = (event) =>
    event.touches ? event.touches[0] : event

let dragPosition

const onDragStart = (event) => {
    dragPosition = touchPosition(event).pageY
    sheetContents.classList.add("not-selectable")
    draggableArea.style.cursor = document.body.style.cursor = "grabbing"
}

const onDragMove = (event) => {
    if (dragPosition === undefined) return

    const y = touchPosition(event).pageY
    const deltaY = dragPosition - y
    const deltaHeight = deltaY / window.innerHeight * 100

    setSheetHeight(sheetHeight + deltaHeight)
    dragPosition = y
}

const onDragEnd = () => {
    dragPosition = undefined
    sheetContents.classList.remove("not-selectable")
    draggableArea.style.cursor = document.body.style.cursor = ""

    if (sheetHeight < 45) {
        setIsSheetShown(false)
    } else if (sheetHeight > 75) {
        setSheetHeight(100)
    } else {
        setSheetHeight(60)
    }
}

draggableArea.addEventListener("mousedown", onDragStart)
draggableArea.addEventListener("touchstart", onDragStart)

window.addEventListener("mousemove", onDragMove)
window.addEventListener("touchmove", onDragMove)

window.addEventListener("mouseup", onDragEnd)
window.addEventListener("touchend", onDragEnd)


//버튼 변경
document.addEventListener('DOMContentLoaded', function () {
    var radioGroups = {}; // 라디오 그룹을 저장할 객체

    var inputs = document.querySelectorAll('.circle'); // 해당 클래스를 가진 모든 input 요소 선택

    inputs.forEach(function (input) {
        input.addEventListener('change', function () {
            var label = this.parentElement.querySelector('label');
            var img = label.querySelector('img');

            // 라디오 버튼 처리
            if (this.type === 'radio') {
                var groupName = this.name;

                // 같은 라디오 그룹 내의 다른 라디오 버튼들을 비활성화
                if (radioGroups[groupName]) {
                    radioGroups[groupName].forEach(function (radio) {
                        var radioLabel = radio.parentElement.querySelector('label');
                        var radioImg = radioLabel.querySelector('img');
                        radioImg.src = 'images/icon/checkbox/off.png';
                    });
                }

                // 현재 선택한 라디오 버튼을 활성화
                img.src = 'images/icon/checkbox/on.png';

                // 라디오 그룹에 현재 라디오 버튼 추가
                if (!radioGroups[groupName]) {
                    radioGroups[groupName] = [];
                }
                radioGroups[groupName].push(this);
            }

            // 체크박스 처리
            else if (this.type === 'checkbox') {
                // 체크 상태에 따라 이미지 변경
                img.src = this.checked ? 'images/icon/checkbox/on.png' : 'images/icon/checkbox/off.png';
            }
        });
    });
});

//scollspy 적용
document.addEventListener('DOMContentLoaded', function () {
    var surveys = document.querySelectorAll('.survey_container');

    surveys.forEach(function (survey, index) {
        var inputs = survey.querySelectorAll('.circle, input[type="text"]');
        inputs.forEach(function (input) {
            input.addEventListener('change', function () {
                // 현재 survey에서 입력이 발생하면 다음 survey로 스크롤
                if (index + 1 < surveys.length) {
                    surveys[index + 1].scrollIntoView({behavior: 'smooth'});
                }
            });

            // 엔터 키를 눌렀을 때 폼 제출 방지
            input.addEventListener('keydown', function (event) {
                if (event.key === 'Enter') {
                    event.preventDefault();

                    // 현재 survey에서 입력이 발생하면 다음 survey로 스크롤
                    if (index + 1 < surveys.length) {
                        surveys[index + 1].scrollIntoView({behavior: 'smooth'});
                    }
                }
            });
        });
    });
});