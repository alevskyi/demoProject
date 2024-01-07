import * as React from "react";
import {Navigate} from "react-router-dom";

interface ProtectedRouteProps {
    children: any,
    isAuthenticated: boolean
}

export const AuthWrapper = (props: ProtectedRouteProps) => {
    return <>
        {props.isAuthenticated ? {...props.children} : <Navigate to='/login'/>}
    </>
}