export const Register = () => {
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
                        <div className="emphasizedText">Registration</div>
                    </div>
                    <div>
                        <form action="register" method="post">
                            <table className="table">
                                <col width="100px"/>
                                <col width="250px"/>
                                <col width="30px"/>
                                <tr>
                                    <td><span>Username:</span></td>
                                    <td><input type="text" name="username" id="username"/></td>
                                    {/*th:src="@{'/images/' + ${#fields.hasErrors('username') ? 'cross.png' : 'check.png'}}"
                                             src="/home/asdf/Java/webapp/src/main/webapp/images/check.png"*/}
                                    <td><img className="validImg" id="username_icon"/></td>
                                </tr>
                                <tr>
                                    <td colSpan={3}>
                                        <div className="fieldError" id="username_error">Username must be 5 to 20
                                            characters long, only digits, letters(Latin only) and underscore permitted.
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><span>Password:</span></td>
                                    <td><input type="password" name="passwd" id="passwd"/></td>
                                    {/*th:src="@{'/images/' + ${#fields.hasErrors('passwd') ? 'cross.png' : 'check.png'}}"
                                             src="/home/asdf/Java/webapp/src/main/webapp/images/check.png"*/}
                                    <td><img className="validImg"
                                             id="passwd_icon"/></td>
                                </tr>
                                <tr>
                                    <td colSpan={3}>
                                        <div className="fieldError" id="passwd_error">Password must be 5 to 20
                                            characters long, only digits and letters permitted.
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><span>Confirm password:</span></td>
                                    <td><input type="password" name="confirmPasswd" id="confirm"/></td>
                                    {/* th:src="@{'/images/' + ${#fields.hasErrors('passwordsMatch') ? 'cross.png' : 'check.png'}}"
                                             src="/home/asdf/Java/webapp/src/main/webapp/images/check.png"*/}
                                    <td><img className="validImg" id="confirm_icon"/></td>
                                </tr>
                                <tr>
                                    <td colSpan={3} id="confirm_error_parrent">
                                        <div  className="fieldError" id="confirm_error">Passwords don't match.</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        {/* style="font-family: cabazon; font-size: 1em;"*/}
                                        <input type="submit" value="Register"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            <div className="footer font"/>
        </div>
    );
}