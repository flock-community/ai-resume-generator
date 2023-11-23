import {Card} from "@mui/material";
import {Message} from "./GenerateResumeComponent.tsx";

type ChatBoxProps = {
    messages: Message[]
}
export default function ChatBox({messages} : ChatBoxProps) {
    return (
        <Card>
            <ul>{messages.map((message: Message, index) => (
                <li style={{color: message.type == "AGENT" ? "green" : "red"}}
                    key={index}>{message.type} says: {message.contents}</li>
            ))}
            </ul>
        </Card>
    )
}