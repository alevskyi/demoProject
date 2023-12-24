import {Quote, QuoteList} from "../components/QuoteList";
import {QuoteForm} from "../components/QuoteForm";
import {useEffect, useState} from "react";
import {get} from "../client";


export const Profile = (props: { currentUser: string }) => {
    const [quotes, setQuotes] = useState<Quote[]>([]);
    useEffect(() => {
        fetchQuotes();
    }, []);
    const fetchQuotes = () => get<Quote[]>(`quote/user/${props.currentUser}`, (data) => setQuotes(data));

    return (
        <div className="bodyDiv font">
            <span>Your postings:</span>
            <div className="quotesList">
                {quotes ? <QuoteList data={quotes}/> : <h4>Nothing here</h4>}
            </div>
            <QuoteForm successHandler={fetchQuotes}/>
        </div>
    );
}