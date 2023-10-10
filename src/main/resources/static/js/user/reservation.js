<!-- 카카오 채널 추가 -->

Kakao.init('ffb1ac4b290c42a760a2af6a06801cba'); // 사용하려는 앱의 JavaScript 키 입력
Kakao.Channel.addChannel({
    channelPublicId: '_gixiMG',
});

function addChannel(){
    //채널 추가
    Kakao.Channel.addChannel({
        channelPublicId: '_gixiMG',
    });
}