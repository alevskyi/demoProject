interface NavProps {
    authenticated?: boolean
}

export function Nav(props: NavProps = {authenticated:true}) {
    // TODO dynamic values based on url
    return <>
        <div className="linkDiv left">
            {props.authenticated
                ?  <a href="profile">Profile</a>
                : <a href="register">Register</a>
            }
        </div>

        <div className="linkDiv right">
            {props.authenticated
                ? <a href="logout">Logout</a>
                :  <a href="login">Login</a>
            }
        </div>

        <div className="linkDiv middle">
            <a href="/">Main</a>
        </div>
    </>;
}