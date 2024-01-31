import {Message} from "./GenerateResumeComponent.tsx";
import {Card, Typography} from "@mui/material";


type ChatMessageProps = {
    message: Message
}
export default function ChatMessage({message}: ChatMessageProps) {
    const getStyle = (actor: Message["type"]): React.CSSProperties => {
        switch (actor) {
            case "AGENT":
                return {
                    backgroundColor: "lightgrey",
                    marginRight: 10
                };
            case "USER": {
                return {
                    backgroundColor: "blue",
                    color: "white",
                    marginLeft: 10
                }
            }
            case "NOTIFICATION": {
                return {
                    backgroundColor: "lightgrey"
                }
            }
        }
    }
    return (
        <Card style={{
            padding: 10,
            ...getStyle(message.type)
        }}><Typography>{message.contents}</Typography></Card>
    )
}