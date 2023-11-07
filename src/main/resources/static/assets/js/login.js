$(() => {
    $("#sendButton").click(() => {
        $.ajax({
            url: "/member/login",
            type: "POST",
            data: $("#myform").serialize()
        })
            .done((res) => {
                alert('로그인 성공!');
                location.href = res;
            })
            .fail((res, status, error) => {
                alert("잘못된 요청입니다.");
                $("#userId").val('').focus();
                $("#password").val('');
            });
    })
})