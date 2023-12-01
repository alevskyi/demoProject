export const Login = () => {
    return (
        <div className="container">
            <div className="header font">
                <div className="linkDiv middle">
                    <a href="/">Main</a>
                </div>
            </div>

            <div className="content">
                <div className="bodyDiv font">
                    <div className="titleBlock">
                        <div className="emphesizedText">Login</div>
                    </div>

                    <div>
                        <form action="login" method="post">
                            <table className="table">
                                <col width="100px"/>
                                <col width="200px"/>

                                <tr>
                                    <td><span>Username:</span></td>
                                    <td colSpan={2}><input type="text" name="username" align="right"/></td>
                                </tr>

                                <tr>
                                    <td><span>Password:</span></td>
                                    <td><input type="password" name="password"/></td>
                                </tr>

                                {/*th:if="${error ne null}"*/}
                                <tr>
                                    <td colSpan={2}>
                                        <div className="fieldError">This is error mesg</div>
                                    </td>
                                </tr>

                                <tr>
                                    <td colSpan={2}>
                                        <span style="display: inline-block;">Stay logged in</span>
                                        <input style="display: inline-block;" type="checkbox" name="remember"/>
                                        <span style="display: inline-block; width: 155px;"></span>
                                        <input type="submit" value="Login"
                                               style="font-family: cabazon; font-size: 1em;"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>

                </div>
            </div>
            <div className="footer font"/>
        </div>);
}