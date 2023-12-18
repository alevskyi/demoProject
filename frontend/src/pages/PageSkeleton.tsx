import {Nav} from "../components/Nav";
import React, {useEffect, useState} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Main} from "./Main";
import {Quotes} from "./Quotes";
import {Login} from "./Login";
import {Register} from "./Register";
import {Profile} from "./Profile";
import {About} from "./About";
import {get} from "../client";
import {AuthWrapper} from "../components/AuthWrapper";
import {useAuth} from "../auth";

export const PageSkeleton = () => {
    const [authenticated, setAuthenticated] = useState(false);

    useEffect(() => {
        get<void>('auth', (res) => {
            setAuthenticated(true);
        }, (res) => {
            setAuthenticated(false);
        });
    }, []);

    // const [authenticated] = useAuth();

    return (
        <BrowserRouter>
            <div className="container">
                <div className="header">
                    <Nav authenticated={authenticated} logoutHandler={() => setAuthenticated(false)}/>
                </div>
                <div className="content">
                    <Routes>
                        <Route path="/" element={<Main/>}/>
                        <Route path="quote/*" element={<Quotes/>}/>
                        <Route path="login" element={<Login loginHandler={() => setAuthenticated(true)}/>}/>
                        <Route path="register" element={<Register/>}/>
                        <Route path="profile" element={
                            <AuthWrapper isAuthenticated={authenticated}>
                                <Profile/>
                            </AuthWrapper>
                        }/>
                        <Route path="about" element={<About/>}/>
                    </Routes>
                </div>
                <div className="footer"/>
            </div>
        </BrowserRouter>
    );
}