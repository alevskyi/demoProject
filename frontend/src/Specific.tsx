export const Specific = () => {
  return(
      <div className="container">
          <div className="header"/>
          <div className="content">
              <div className="bodyDiv">
                  <div className="quoteContainer">
                      <div className="quoteDiv" th:text="${quote.text}"
                           th:classappend="${quote.getLang().value()=='Russian' ? 'quoteRus':'quoteEng'}">
                          This is quote textThis is quote textThis is quote text gfdg gfdgdfgsssssssss
                      </div>

                      <div className="autorDiv" th:classappend="${quote.getLang().value()=='Russian' ? 'rus':'eng'}"
                           th:text="'&mdash; ' + ${quote.person}">-this is Autor
                      </div>
                  </div>
              </div>
          </div>

          <div className="footer"/>
      </div>
  );
}