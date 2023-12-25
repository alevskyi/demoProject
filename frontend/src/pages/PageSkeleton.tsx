import {Nav} from "../components/Nav";
import React, {useEffect, useState} from "react";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import {Main} from "./Main";
import {Quotes} from "./Quotes";
import {Login} from "./Login";
import {Register} from "./Register";
import {Profile} from "./Profile";
import {About} from "./About";
import {get} from "../client";
import {AuthWrapper} from "../components/AuthWrapper";

export const PageSkeleton = () => {
    const [currentUser, setCurrentUser] = useState<string | undefined>();
    const [loading, setLoading] = useState<boolean>(true);

    const isAuthenticated = (): boolean => !!currentUser;
    const handleLogin = (username: string): void => setCurrentUser(username);
    const handleLogout = (): void => setCurrentUser(undefined);

    useEffect(() => {
        get<string>('auth', (res) => {
            setCurrentUser(res);
            setLoading(false);
        }, (res) => {
            setCurrentUser(undefined);
            setLoading(false);
        });
    }, []);

    if (loading) {
        return <></>;
    }

    return (
        <BrowserRouter>
            <div className="content-bg">
                <div className="content">
                    <Nav authenticated={isAuthenticated()} logoutHandler={handleLogout}/>
                    <Routes>
                        <Route path="/" element={<Main/>}/>
                        <Route path="quote/*" element={<Quotes/>}/>
                        <Route path="login" element={<Login loginHandler={handleLogin}/>}/>
                        <Route path="register" element={<Register/>}/>
                        <Route path="profile" element={
                            <AuthWrapper isAuthenticated={isAuthenticated()}>
                                <Profile currentUser={currentUser!!}/>
                            </AuthWrapper>
                        }/>
                        <Route path="about" element={<About/>}/>
                        <Route path="*" element={<Navigate replace to="/"/>}/>
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    );
}