import {useRouteError} from "react-router";

export default function ErrorPage() {
    const error = useRouteError()
    console.error(error)

    return (
        <div id="error-page">
            <h1>Oops!</h1>
            <p>Something unexpected happened</p>
            <p>
            <i>{ (error as { statusText?: string })?.statusText || (error as Error).message}</i></p>
        </div>
    )
}