import {Link} from "react-router-dom";

export const Main = () => {
    return (
            <div className="bodyDiv font">
                <div className="homeEntry">
                    <Link to="quote/random">Random quotes</Link>
                </div>

                <div className="homeEntry">
                    <Link to="quote/lang/en">Random quotes in english</Link>
                </div>

                <div className="homeEntry">
                    <Link to="quote/lang/ru">Random quotes in russian</Link>
                </div>

                <div className="homeEntry">
                    <Link to="about">About</Link>
                </div>
            </div>
    );
}