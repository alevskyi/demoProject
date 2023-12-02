import {Quote} from "../components/Quote";
import {Nav} from "../components/Nav";
import {QuoteForm} from "../components/QuoteForm";

export const Profile = () => {
    return (<div className="container">
            <div className="header">
                <Nav/>
            </div>

            <div className="content">
                <div className="bodyDiv font">
                    {/*style="display: table-row;"*/}
                    <span>Quotes posted by you:</span>


                    <div className="quotesList">
                        <Quote/>
                        <h4>Nothing here</h4>
                    </div>

                    <QuoteForm/>
                </div>
            </div>

            <div className="footer"/>
        </div>
    );
}