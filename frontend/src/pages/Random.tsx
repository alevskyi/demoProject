import {Quote} from "../components/Quote";
import {Nav} from "../components/Nav";

export const Random = () => {
  return(
      <div className="container">
          <div className="header font">
              <Nav/>
          </div>

          <div className="content">
              <div className="bodyDiv font">
                  <Quote/>
              </div>
          </div>

          <div className="footer font"/>
      </div>
  );
}