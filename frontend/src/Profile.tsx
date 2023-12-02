interface Quote {
}

export const Profile = (props: { quotes: Quote[] }) => {
    return (<div className="container">
            <div className="header font">
                <div className="linkDiv left">
                    <a href="/">Main</a>
                </div>

                <div className="linkDiv right">
                    <a href="logout">Logout</a>
                </div>
            </div>

            <div className="content">
                <div className="bodyDiv font">

                    {/*style="display: table-row;"*/}
                    <span>Quotes posted by you:</span>

                    <div className="quotesList">
                        <a href="#" className="quoteContainer">
                            <div className="quoteDiv">
                                This is quote textThis is quote textThis is quote text gfdg gfdgdfgsssssssss
                            </div>

                            <div className="autorDiv">-this is Autor</div>
                        </a>
                        <h4>Nothing here</h4>
                    </div>

                    <span>Post a new quote:</span>

                    <form action="quotes/new" method="post">
                        <table className="table">
                            <col width="40px"/>
                            <col width="50px"/>
                            <col width="200px"/>
                            <tr>
                                <td><span>Text:</span></td>
                                {/*style="width:700px;height:80px;resize:none;"*/}
                                <td colSpan={2}><textarea name="text"></textarea></td>
                            </tr>

                            <tr>
                                <td colSpan={3}>
                                    {/*th:if="${#fields.hasErrors('text')}"*/}
                                    <div className="fieldError">Username must be 5 to 20 characters long, only digits,
                                        letters(Latin only) and underscore permitted.
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td><span>Person:</span></td>
                                <td><input type="text" name="person" size={15}/></td>
                                <td>
                                    {/*style="margin:0 0 0 0;"*/}
                                    <span>Language: English<input type="radio" name="lang"
                                                                  value="ENGLISH" checked={true}/>Russian<input
                                        type="radio" name="lang" value="RUSSIAN"/></span>
                                </td>
                            </tr>

                            <tr>
                                <td colSpan={3}>
                                    {/*th:if="${#fields.hasErrors('person')}"*/}
                                    <div className="fieldError">Password must be 5 to 20 characters long, only digits
                                        and
                                        letters permitted.
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                {/*style="font-family: cabazon; font-size: 1em;"*/}
                                <td><input type="submit" value="Submit"/></td>
                            </tr>
                        </table>
                    </form>

                    <span>or upload a file:</span>

                    <form action="quotes/xml" encType="multipart/form-data" method="post">
                        <table className="table">

                            <tr>
                                <td><span>File:</span></td>
                                {/*style="font-family: cabazon; font-size: 1em;"*/}
                                <td><input type="file" accept="application/xml" name="file"/></td>
                                {/*style="font-family: cabazon; font-size: 1em;"*/}
                                <td><input type="submit" value="Submit"/></td>
                            </tr>

                        </table>
                    </form>
                    {/*style="font-size: 1.3em;"*/}
                    <a href="/download/quote_template" target="_blank">Download template</a>
                </div>
            </div>

            <div className="footer font"/>
        </div>
    );
}