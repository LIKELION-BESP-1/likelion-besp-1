import {createAlert} from './createNode.js';

$(() => {
    $("#sendButton").click(() => {
        $.ajax({
            url: "/member/login",
            type: "POST",
            data: $("#myform").serialize()
        })
            .done((res) => {
                const $blurDiv = document.createElement('div');
                $blurDiv.classList.add("blur-background");

                const $div = createAlert('로그인 성공', res);
                const $body = $('body');
                $($body).append($blurDiv);
                $($body).append($div);

                setTimeout(() => {
                    location.href = res;
                }, 3000);
            })
            .fail((res, status, error) => {
                alert("잘못된 요청입니다.");
                $("#userId").val('').focus();
                $("#password").val('');
            });
    })
})