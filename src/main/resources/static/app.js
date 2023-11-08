let stompClient = null;
var roomId = null; // roomId 변수 초기화

$(document).ready(function() {
    roomId = [[${roomId}]];
});

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws-stomp');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/room/'+roomId, function (chatMessage) {
            showGreeting(JSON.parse(chatMessage.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/room/" + roomId, {}, JSON.stringify({
        'username': $("#username").val(),
        'content': $("#content").val()
    }));
}

function showGreeting(chatMessage) {
    console.log(chatMessage.username)
    $("#chatting").append("<tr><td>" + "[" + chatMessage.username + "]" + chatMessage.content + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

