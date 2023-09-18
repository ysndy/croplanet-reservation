const editor = document.getElementById("editor");

function alignText(align) {
    document.execCommand("justify" + align, false, null);

}

function changeFontSize() {
    const fontSize = document.getElementById("fontSize").value;
    document.execCommand("fontSize", false, fontSize);
}

function increaseFontSize() {
    const currentSize = document.queryCommandValue("fontSize");
    if (currentSize) {
        const newSize = parseInt(currentSize) + 1;
        document.execCommand("fontSize", false, newSize + "px");
    }
}

function decreaseFontSize() {
    const currentSize = document.queryCommandValue("fontSize");
    if (currentSize) {
        const newSize = parseInt(currentSize) - 1;
        document.execCommand("fontSize", false, newSize + "px");
    }
}

function toggleBold() {
    document.execCommand("bold", false, null);
}

function save(){
    var html = editor.innerHTML;

    $.ajax({
        type: "POST",
        url: "/admin/editor",
        data: html,
        contentType: "text/html",
        success: function(response) {
            console.log("서버 응답:", response);
        },
        error: function(err) {
            console.error("오류 발생:", err);
        }
    });

}

function openImageUploader() {
    const imageUpload = document.getElementById("imageUpload");
    imageUpload.click();
}

function insertImage() {
    const imageUpload = document.getElementById("imageUpload");
    const editor = document.getElementById("editor");

    if (imageUpload.files && imageUpload.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {

            // 이미지를 서버로 업로드
            const formData = new FormData();
            formData.append("file", imageUpload.files[0]);

            $.ajax({
                url: "/files/images",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (fileUri) {
                    console.log("이미지 업로드 성공, 파일 경로:", fileUri);

                    const img = new Image();
                    img.src = "http://localhost:8080/files/images/"+fileUri;

                    // 이미지 삽입
                    editor.focus();
                    document.execCommand("insertImage", false, img.src);
                },
                error: function (error) {
                    console.error("이미지 업로드 실패:", error);
                },
            });

        };

        reader.readAsDataURL(imageUpload.files[0]);
    }
}