import {CompatClient, Stomp} from "@stomp/stompjs";
import {useEffect, useState} from "react";

export const GenerateResumeComponent = () => {
    const [client, setClient] = useState<CompatClient>()
    const [message, setMessage] = useState('');
    const [messages, setMessages] = useState<string[]>([]);

    const appendMessage = (newMessage: string) => {
        setMessages((messages) => [...messages, newMessage])
    }
    const connect: () => void = () => {
        const topicName = "chat";
        const socket = new WebSocket("ws://localhost:5173/chat")
        const stompClient = Stomp.over(socket)
        stompClient.connect({}, frame => {
            console.log(`Connected: ${frame}`);
            stompClient.subscribe(`/topic/${topicName}`, response => {
                let newMessage = JSON.parse(response.body).contents;
                appendMessage(newMessage)
            });
        });
        setClient(stompClient);
    };

    const sendMessage = (message: string) => {
        client?.send(
            "/app/chat",
            {},
            message
        )
    }

    useEffect(() => connect(), [])
    return (
        <>
            <h1>Talk to ChatGPT:</h1>
            <input
                value={message}
                onChange={e => setMessage(e.target.value)}
            />
            <button onClick={() => {
                sendMessage(message)
                setMessages([...messages, message])
            }}>Send</button>
            <ul>
                {messages.map((message, index) => (
                    <li key={index}> {message}</li>
                ))}
            </ul>
    </>)
}

