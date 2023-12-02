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
                        <div className="emphasizedText">Login</div>
                    </div>

                    <div>
                        <form action="login" method="post">
                            <table className="table">
                                <col width="100px"/>
                                <col width="200px"/>

                                <tr>
                                    <td><span>Username:</span></td>
                                    <td colSpan={2}><input type="text" name="username"/></td>
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
                                        {/*style="display: inline-block;"*/}
                                        <span>Stay logged in</span>
                                        {/*style="display: inline-block;" */}
                                        <input type="checkbox" name="remember"/>
                                        {/*style="display: inline-block; width: 155px;"*/}
                                        <span></span>
                                        {/*style="font-family: cabazon; font-size: 1em;"*/}
                                        <input type="submit" value="Login"/>
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