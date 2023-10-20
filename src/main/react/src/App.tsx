import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Root from "./routes/root.tsx";
import ErrorPage from "./error-page.tsx";
import {PersonalInformation} from "./routes/personal-information.tsx";

const router = createBrowserRouter([
        {
            path: "/",
            element: <Root/>,
            errorElement: <ErrorPage/>
        },
        {
            path: "/personal-information",
            element: <PersonalInformation/>
        }
    ]
);

export function App() {
    return <RouterProvider router={router}/>
}
