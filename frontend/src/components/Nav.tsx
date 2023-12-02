export function Nav() {
    return <>
        <div className="linkDiv left">
            <a href="/">Main</a>
        </div>

        <div className="linkDiv right">
            {/*sec:authorize="isAuthenticated()"*/}
            <a href="logout">Logout</a>

            <a href="login">Login</a>

            {/*sec:authorize="isAnonymous()"*/}
            <a href="register">Register</a>
        </div>

        {/*sec:authorize="isAuthenticated()"*/}
        <div className="linkDiv middle">
            <a href="profile">Profile</a>
        </div>
    </>;
}