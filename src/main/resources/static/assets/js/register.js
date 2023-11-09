import {createAlert} from './createNode.js';

$(() => {
    $("#sendButton").click(() => {

        if (!checkEmpty()) {
            alert("해당 값들은 필수입니다");
            return;
        }

        $.ajax({
            url: "/member/register",
            type: "POST",
            data: $("#myform").serialize(),
        })
            .done((res) => {
                const $blurDiv = document.createElement('div');
                $blurDiv.classList.add("blur-background");

                const $div = createAlert('회원 가입 성공', res);
                const $body = $('body');
                $($body).append($blurDiv);
                $($body).append($div);

                setTimeout(() => {
                    location.href = res;
                }, 3000);
            })
            .fail((res, status, error) => {
                alert("이미 존재하는 아이디 또는 잘못된 비밀번호입니다.");
                let $userId = $('#userId');
                let $password = $('#password');
                $userId.val('').focus();
                $password.val('');
                toggleInputClass($userId, false);
                toggleInputClass($password, false);
            });
    })
})

function checkEmpty() {
    let check = true;
    $('#myform input').each(function () {
        const input = $(this);
        const isValid = checkInput(input);
        toggleInputClass(input, isValid);
        check = check && isValid;
    });

    return check;
}

function checkInput(input) {
    return input.get(0).checkValidity();
}

function toggleInputClass(input, isValid,) {
    const customClass = 'border border-danger';
    input.toggleClass(customClass, !isValid);

}