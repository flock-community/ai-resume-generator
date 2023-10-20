let stompClient = null;
const topicName = "chat";

const setConnected = connected => {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  $("#sendMessage").prop("disabled", !connected);
  $("#messageInput").prop("disabled", !connected);
};

const showMessage = message => {
  $("#messages").append(`<li>${message}</li>`);
};

const connect = () => {
  const socket = new WebSocket("ws://localhost:8080/chat")
  stompClient = Stomp.over(socket);
  stompClient.connect({}, frame => {
    setConnected(true);
    console.log(`Connected: ${frame}`);
    stompClient.subscribe(`/topic/${topicName}`, response => {
      showMessage(JSON.parse(response.body).contents);
    });
  });
};

const disconnect = () => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
};

const sendMessage = () => {
  const message = $('#messageInput').val()
  stompClient.send(
      "/app/chat",
      {},
      message
  )
  $('#messageInput').val("")
}

$(() => {
  $("form").on('submit', e => e.preventDefault());
  $("#connect").click(() => {
    connect();
  });
  $("#disconnect").click(() => {
    disconnect();
  });
  $("#sendMessage").click(() => {
    sendMessage();
  });

});
