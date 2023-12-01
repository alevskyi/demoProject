interface Quote {
}

export const Profile = (props:{quotes:Quote[]}) => {
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

                <span style="display: table-row;">Quotes posted by you:</span>

                <div className="quotesList">
                    <a style="display:block" href="#" th:href="@{'/quotes/id/' + ${quote.id}}"
                       th:each="quote : ${quotes}" className="quoteContainer">

                        <div className="quoteDiv" th:text="${quote.text}"
                             th:classappend="${quote.getLang().value()=='Russian' ? 'quoteRus':'quoteEng'}">
                            This is quote textThis is quote textThis is quote text gfdg gfdgdfgsssssssss
                        </div>

                        <div className="autorDiv" th:classappend="${quote.getLang().value()=='Russian' ? 'rus':'eng'}"
                             th:text="'&mdash; ' + ${quote.person}">-this is Autor
                        </div>

                    </a>

                    <h4 th:if="${quotes.isEmpty()}">Nothing here</h4>

                </div>


                <span>Post a new quote:</span>

                <form action="quotes/new" method="post">
                    <table className="table">
                        <col width="40px"/>
                        <col width="50px"/>
                        <col width="200px"/>
                        <tr>
                            <td><span>Text:</span></td>
                            <td colSpan={2}><textarea name="text" style="width:700px;height:80px;resize:none;"
                                                      th:field="*{text}"></textarea></td>
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
                              <span style="margin:0 0 0 0;">Language: English<input type="radio" name="lang"
                                                                                    value="ENGLISH" checked={true}/>Russian<input
                                  type="radio" name="lang" value="RUSSIAN"/></span>
                            </td>
                        </tr>

                        <tr>
                            <td colSpan={3}>
                                {/*th:if="${#fields.hasErrors('person')}"*/}
                                <div className="fieldError">Password must be 5 to 20 characters long, only digits and
                                    letters permitted.
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><input type="submit" value="Submit" style="font-family: cabazon; font-size: 1em;"/></td>
                        </tr>
                    </table>
                </form>

                <span>or upload a file:</span>

                <form action="quotes/xml" encType="multipart/form-data" method="post">
                    <table className="table">

                        <tr>
                            <td><span>File:</span></td>
                            <td><input type="file" accept="application/xml" name="file"
                                       style="font-family: cabazon; font-size: 1em;"/></td>
                            <td><input type="submit" value="Submit" style="font-family: cabazon; font-size: 1em;"/></td>
                        </tr>

                    </table>
                </form>
                <a href="/download/quote_template" target="_blank" style="font-size: 1.3em;">Download template</a>
            </div>
        </div>

        <div className="footer font"/>
    </div>
    );
}