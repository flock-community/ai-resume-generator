import {Stack} from "@mui/material";
import {Message} from "./GenerateResumeComponent.tsx";
import ChatMessage from "./ChatMessage.tsx";

type ChatBoxProps = {
    messages: Message[]
}
export default function ChatBox({messages} : ChatBoxProps) {
    return (
        <Stack spacing={2}>
            {messages.map((message: Message, index) => (
               <ChatMessage key={index} message={message} />
            ))}
        </Stack>
    )
}