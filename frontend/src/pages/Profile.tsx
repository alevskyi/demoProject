import {Quote, QuoteList} from "../components/QuoteList";
import {QuoteForm} from "../components/QuoteForm";
import {useEffect, useState} from "react";
import {get} from "../client";

export const Profile = () => {
    const currentUser = 'testUser';
    useEffect(() => {
        get<Quote[]>(`quote/user/${currentUser}`, (data) => setQuotes(data));
    }, []);
    const [quotes, setQuotes] = useState<Quote[]>([]);
    return (
        <div className="bodyDiv font">
            {/*style="display: table-row;"*/}
            <span>Your postings:</span>
            <div className="quotesList">
                <QuoteList data={quotes}/>
                <h4>Nothing here</h4>
            </div>
            <QuoteForm/>
        </div>
    );
}