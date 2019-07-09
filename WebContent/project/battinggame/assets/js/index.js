var xmlHttpRequest;

function sendMakeRoom() {
    // 部屋番号と名前をセッションで保存
    var nameElement = document.getElementById("name");
    window.sessionStorage.setItem("name", nameElement.value);
    var roomElement = document.getElementById("room");
    window.sessionStorage.setItem("room", roomElement.value);
    // 部屋作成主かどうか
    window.sessionStorage.setItem("make", true);

    document.getElementById('form').action = 'makeRoom';
}

function sendJoinRoom() {
    // 部屋番号と名前をセッションで保存
    var nameElement = document.getElementById("name");
    window.sessionStorage.setItem("name", nameElement.value);
    var roomElement = document.getElementById("room");
    window.sessionStorage.setItem("room", roomElement.value);
    // 部屋作成主かどうか
    window.sessionStorage.setItem("make", false);

    document.getElementById('form').action = 'joinRoom';
}