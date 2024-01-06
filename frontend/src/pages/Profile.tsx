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
        <>
            <h2>Your postings:</h2>
            {quotes.length ?
               <div className="quotes"> <QuoteList data={quotes}/></div>: <h1>Nothing here</h1>}
            <QuoteForm successHandler={fetchQuotes}/>
        </>
    );
}