import {CompatClient, Stomp} from "@stomp/stompjs";
import {useEffect, useState} from "react";
import ChatBox from "./ChatBox.tsx";

export type Message = {
    contents: string;
    type: "AGENT" | "USER" | "NOTIFICATION"
}

export const GenerateResumeComponent = () => {
    const [client, setClient] = useState<CompatClient>()
    const [message, setMessage] = useState('');
    const [messages, setMessages] = useState<Message[]>([]);

    const appendMessage = (newMessage: Message) => {
        setMessages((messages) => [...messages, newMessage])
    }
    const connect: () => void = () => {
        const topicName = "chat";
        const socket = new WebSocket("ws://localhost:5173/chat")
        const stompClient = Stomp.over(socket)
        stompClient.connect({}, frame => {
            console.log(`Connected: ${frame}`);
            stompClient.subscribe(`/topic/${topicName}`, response => {
                let newMessage: Message = JSON.parse(response.body)
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
                setMessages([...messages, {
                    type: "USER",
                    contents: message
                }])
            }}>Send
            </button>
            <ChatBox messages={messages} />
        </>)
}

