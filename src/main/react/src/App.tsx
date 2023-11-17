import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Root from "./routes/root.tsx";
import ErrorPage from "./components/error-page.tsx";
import {PersonalInformation} from "./routes/personal-information.tsx";
import {GenerateResumeComponent} from "./components/GenerateResumeComponent.tsx";

const router = createBrowserRouter([
        {
            path: "/",
            element: <Root/>,
            errorElement: <ErrorPage/>
        },
        {
            path: "/personal-information",
            element: <PersonalInformation/>
        },
    {
        path: "/generate-resume-item",
        element: <GenerateResumeComponent/>
    }
    ]
);

export function App() {
    return <RouterProvider router={router}/>
}
