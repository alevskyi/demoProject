import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {About} from "./pages/About";
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import {Main} from "./pages/Main";
import {Login} from "./pages/Login";
import {Register} from "./pages/Register";
import {Profile} from "./pages/Profile";
import {Random} from "./pages/Random";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);

const router = createBrowserRouter([
    {
        path: "/",
        element: <Main/>,
    },
    {
        path: "quotes/random",
        element: <Random/>,
    },
    {
        path: "login",
        element: <Login/>,
    },
    {
        path: "register",
        element: <Register/>,
    },
    {
        path: "profile",
        element: <Profile/>,
    },
    {
        path: "about",
        element: <About/>,
    },
]);

root.render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
