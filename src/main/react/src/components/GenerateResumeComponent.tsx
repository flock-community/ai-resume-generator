import {CompatClient, Stomp} from "@stomp/stompjs";
import React, {useEffect, useState} from "react";
import ChatBox from "./ChatBox.tsx";
import {Box, IconButton, Input, Stack} from "@mui/material";
import {Send} from "@mui/icons-material";

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
        stompClient.connect({}, (frame: any) => {
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

    const handleSubmit = () => {
        sendMessage(message)
        setMessages([...messages, {
            type: "USER",
            contents: message
        }])
    }

    const handleKeyDown = (event: React.KeyboardEvent) => {
        if (event.code == "Enter") {
            handleSubmit()
        }
    }

    const disconnect = () => client?.disconnect()

    useEffect(() => {
        connect()
        return () => disconnect()
    }, [])

    return (
        <Stack spacing={3}>
            <ChatBox messages={messages}/>
            <Box>
                <Stack spacing={3} direction={"row"} padding={5}>
                    <Input fullWidth={true}
                           value={message}
                           onChange={e => setMessage(e.target.value)}
                           onSubmit={handleSubmit}
                           onKeyDown={e => handleKeyDown(e)}
                    />
                    <IconButton aria-label="send" onClick={handleSubmit}>
                        <Send />
                    </IconButton>
                </Stack>
            </Box>
        </Stack>
    )
}

