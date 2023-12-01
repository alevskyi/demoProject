export const Random = () => {
  return(
      <div className="container">
          <div className="header font">

              <div className="linkDiv left">
                  <a href="/">Main</a>
              </div>

              <div className="linkDiv right">
                  {/*sec:authorize="isAuthenticated()"*/}
                  <a href="logout">Logout</a>

                  {/*sec:authorize="isAnonymous()"*/}
                  <a href="register">Register</a>
              </div>

              {/*sec:authorize="isAuthenticated()"*/}
              <div className="linkDiv middle">
                  <a th:href="@{'/users/' + ${#authentication.principal.getUsername()}}">Profile</a>
              </div>

          </div>

          <div className="content">
              <div className="bodyDiv font">

                  <a style="display:block" href="#" th:href="@{'id/' + ${quote.id}}"
                     th:each="quote : ${randomQuotes}" className="quoteContainer">

                      <div className="quoteDiv" th:text="${quote.text}"
                           th:classappend="${quote.getLang().value()=='Russian' ? 'quoteRus':'quoteEng'}">
                          This is quote textThis is quote textThis is quote text gfdg gfdgdfgsssssssss
                      </div>

                      <div className="autorDiv" th:classappend="${quote.getLang().value()=='Russian' ? 'rus':'eng'}"
                           th:text="'&mdash; ' + ${quote.person}">-this is Autor
                      </div>
                  </a>
              </div>
          </div>

          <div className="footer font"/>
      </div>
  );
}