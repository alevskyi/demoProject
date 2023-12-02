import {Nav} from "../components/Nav";

export const Main = () => {
    return (
    <div className="container">
        <div className="header"/>

        <div className="content">
            <div className="bodyDiv font">
                <Nav/>

                <div className="homeEntry">
                    <a href="quotes/random">Random quotes</a>
                </div>

                <div className="homeEntry">
                    <a href=".#quotes/en">Random quotes in english</a>
                </div>

                <div className="homeEntry">
                    <a href="quotes/ru">Random quotes in russian</a>
                </div>

                <div className="homeEntry">
                    <a href="about">About</a>
                </div>
            </div>
        </div>
        <div className="footer"/>
    </div>);
}