import {Link} from "react-router-dom";

export const Main = () => {
    return (
        <ul>
            <li>
                <h1><Link to="quote/random">Random quotes</Link></h1>
            </li>
            <li>
                <h1><Link to="quote/lang/en">Random quotes in english</Link></h1>
            </li>
            <li>
                <h1><Link to="quote/lang/ru">Random quotes in russian</Link></h1>
            </li>
            <li>
                <h1><Link to="about">About</Link></h1>
            </li>
        </ul>
    );
}