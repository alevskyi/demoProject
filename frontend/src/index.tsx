import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {About} from "./pages/About";
import {
    BrowserRouter,
    createBrowserRouter, Route,
    RouterProvider, Routes,
} from "react-router-dom";
import {Main} from "./pages/Main";
import {Login} from "./pages/Login";
import {Register} from "./pages/Register";
import {Profile} from "./pages/Profile";
import {Quotes} from "./pages/Quotes";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);

// const router = createBrowserRouter([
//     {
//         path: "/",
//         element: <Main/>,
//     },
//     {
//         path: "quotes/*",
//         element: <Quotes/>,
//     },
//     {
//         path: "login",
//         element: <Login/>,
//     },
//     {
//         path: "register",
//         element: <Register/>,
//     },
//     {
//         path: "profile",
//         element: <Profile/>,
//     },
//     {
//         path: "about",
//         element: <About/>,
//     },
// ]);

root.render(
    <React.StrictMode>
        {/*<RouterProvider router={router}/>*/}
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="quote/*" element={<Quotes/>}/>
                <Route path="login" element={<Login/>}/>
                <Route path="register" element={<Register/>}/>
                <Route path="profile" element={<Profile/>}/>
                <Route path="about" element={<About/>}/>
            </Routes>
        </BrowserRouter>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
