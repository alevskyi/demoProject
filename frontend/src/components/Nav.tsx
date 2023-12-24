import React, {SyntheticEvent} from "react";
import {get} from "../client";
import {Link, useNavigate} from "react-router-dom";

export function Nav(props: { authenticated: boolean, logoutHandler: () => void }) {

    const navigate = useNavigate();
    const logout = (e: SyntheticEvent) => {
        e.preventDefault();
        get<void>('logout', (data) => {
            props.logoutHandler();
            navigate('/');
        });
    }

    return <>
        <div className="linkDiv left">
            {props.authenticated
                ? <Link to="profile">Profile</Link>
                : <Link to="register">Register</Link>
            }
        </div>

        <div className="linkDiv right">
            {props.authenticated
                ? <a href="#" onClick={logout}>Logout</a>
                : <Link to="login">Login</Link>
            }
        </div>

        <div className="linkDiv middle">
            <Link to="/">Main</Link>
        </div>
    </>;
}