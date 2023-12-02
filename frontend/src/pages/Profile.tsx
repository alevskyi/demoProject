import {Quote, QuoteList} from "../components/QuoteList";
import {QuoteForm} from "../components/QuoteForm";
import {PageSkeleton} from "../components/PageSkeleton";
import {useEffect, useState} from "react";
import axios from "axios";

export const Profile = () => {
    const currentUser = 'testUser';
    useEffect(() => {
        axios.get<Quote[]>(`${process.env.REACT_APP_BACKEND_URL}/quote/user/${currentUser}`)
            .then(resp => setQuotes(resp.data))
            .catch(err => console.log(err));
    }, []);
    const [quotes, setQuotes] = useState<Quote[]>([]);
    return (
        <PageSkeleton>
            <div className="bodyDiv font">
                {/*style="display: table-row;"*/}
                <span>Your postings:</span>
                <div className="quotesList">
                    <QuoteList data={quotes}/>
                    <h4>Nothing here</h4>
                </div>
                <QuoteForm/>
            </div>
        </PageSkeleton>
    );
}