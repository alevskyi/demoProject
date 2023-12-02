import './QuoteList.css'
import {useEffect, useState} from "react";
import {Path, useLocation} from "react-router-dom";
import axios from "axios";

interface Quote {
    id: number,
    lang: string,
    text: string,
    person: string,
    user: string
}

export function QuoteList() {
    const location: Path = useLocation();
    const relativePath = location.pathname.split('/').slice(-1);
    const [quotes, setQuotes] = useState<Quote[]>([]);

    useEffect(() => {
        // const  if ulr is 'profile' -> display quotes per user
        console.log(`${process.env.REACT_APP_BACKEND_URL}/quote/${relativePath}`);
        axios.get<Quote[]>(`${process.env.REACT_APP_BACKEND_URL}/quote/${relativePath}`)
            .then(resp => setQuotes(resp.data))
            .catch(err => console.log(err));
    }, []);

    return (
        <div className="quotesList">
            {quotes.map((quote, index) =>
                <a key={index} href={quote.id.toString()} className="quoteContainer">
                    <div className="quoteDiv">
                        {quote.text}
                    </div>
                    <div className="autorDiv">-{quote.person}</div>
                </a>
            )}
        </div>
    );
}