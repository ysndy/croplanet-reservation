// 오늘 날짜를 생성
// var today = new Date();
//
// // 시작 날짜를 오늘로부터 7일 전으로 설정
// var startDate = new Date(today);
// startDate.setDate(today.getDate() - 7);
//
// // 시작 날짜 input 태그의 value 속성에 설정
// var startDateInput = document.getElementById("start_date");
// startDateInput.valueAsDate = startDate;
//
// // 종료 날짜 input 태그의 value 속성에 오늘 날짜를 설정
// var endDateInput = document.getElementById("end_date");
// endDateInput.valueAsDate = today;


const datasets = [];
const actions = [[${actions}]]; // 버튼 이름 배열
const dates = [[${dates}]];
const map = [[${map}]];
console.log(actions);
console.log(map);
for (let i = 0; i < actions.length; i++) {

    for (let j = 0; j < dates.length; j++){
        console.log(map[dates[j]]);
    }

    const buttonData = {
        label: actions[i],
        data: [[${map}]],
        borderWidth: 1
    };
    datasets.push(buttonData);
}


new Chart(line, {
    type: 'line',
    data: {
        labels: dates,
        datasets: datasets
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});