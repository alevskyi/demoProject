
export const Main = () => {
    return (
    <div className="container">
        <div className="header"/>

        <div className="content">
            <div className="bodyDiv font">
                <div className="homeEntry">
                    {/*not isAuthenticated()*/}
                    <a href="login">Login</a>

                    {/*isAuthenticated()*/}
                    <a href="user/id">Profile</a>
                </div>
                {/*isAuthenticated()*/}
                <div className="homeEntry">
                    <a href="logout">Logout</a>
                </div>

                {/*not isAuthenticated()*/}
                <div className="homeEntry">
                    <a href="register">Register</a>
                </div>

                <div className="homeEntry">
                    <a href="quotes/random">Random quotes</a>
                </div>

                <div className="homeEntry">
                    <a href="#quotes/en">Random quotes in english</a>
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