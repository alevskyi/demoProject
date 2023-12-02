import {Nav} from "./Nav";
import {ReactElement} from "react";

export const PageSkeleton = (props: { children: ReactElement | ReactElement[] }) => {
    return (
        <div className="container">
            <div className="header">
                <Nav/>
            </div>
            <div className="content">
                {props.children}
            </div>
            <div className="footer"/>
        </div>
    );
}