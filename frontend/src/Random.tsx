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
                  <a href="profile">Profile</a>
              </div>
          </div>

          <div className="content">
              <div className="bodyDiv font">
                  {/*style="display:block" th:href="@{'id/' + ${quote.id}}" th:each="quote : ${randomQuotes}"*/}
                  <a href="#"  className="quoteContainer">
                      <div className="quoteDiv">
                          This is quote textThis is quote textThis is quote text gfdg gfdgdfgsssssssss
                      </div>
                      <div className="autorDiv">-this is Autor</div>
                  </a>
              </div>
          </div>

          <div className="footer font"/>
      </div>
  );
}