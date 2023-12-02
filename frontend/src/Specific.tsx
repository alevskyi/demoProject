export const Specific = () => {
  return(
      <div className="container">
          <div className="header"/>
          <div className="content">
              <div className="bodyDiv">
                  <div className="quoteContainer">
                      {/*th:text="${quote.text}"
                           th:classappend="${quote.getLang().value()=='Russian' ? 'quoteRus':'quoteEng'}"*/}
                      <div className="quoteDiv">
                          This is quote textThis is quote textThis is quote text gfdg gfdgdfgsssssssss
                      </div>

                      {/*th:classappend="${quote.getLang().value()=='Russian' ? 'rus':'eng'}"
                           th:text="'&mdash; ' + ${quote.person}"*/}
                      <div className="autorDiv">-this is Autor
                      </div>
                  </div>
              </div>
          </div>

          <div className="footer"/>
      </div>
  );
}