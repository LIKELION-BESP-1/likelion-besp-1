$(() => {
    $("#sendButton").click(() => {
        $.ajax({
            url: "/member/login",
            type: "POST",
            data: $("#myform").serialize()
        })
            .done((res) => {
                location.href = res;
            })
            .fail((res, status, error) => {
                alert("잘못된 요청입니다.");
                $("#userId").val('').focus();
                $("#password").val('');
            });
    })
})