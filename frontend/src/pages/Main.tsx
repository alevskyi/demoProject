import {Link} from "react-router-dom";

export const Main = () => {
    return (
        <div>
            <Link to="quote/random">Random quotes</Link>
            <Link to="quote/lang/en">Random quotes in english</Link>
            <Link to="quote/lang/ru">Random quotes in russian</Link>
            <Link to="about">About</Link>
        </div>
    );
}