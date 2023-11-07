$(() => {
    $("#sendButton").click(() => {
        $.ajax({
            url: "/member/register",
            type: "POST",
            data: $("#myform").serialize(),
        })
            .done((res) => {
                alert("성공");
                location.href = res;
            })
            .fail((res, status, error) => {
                alert("잘못된 요청입니다.");
            });
    })
})