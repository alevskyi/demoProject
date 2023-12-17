import {Quote, QuoteList} from "../components/QuoteList";
import {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import {Path} from "@remix-run/router/history";

import {get} from "../client";

export const Quotes = () => {
    const location: Path = useLocation();

    useEffect(() => {
        get<Quote[]>(location.pathname, (data) => setQuotes(data))
    }, []);
    const [quotes, setQuotes] = useState<Quote[]>([]);
    return (
            <div className="bodyDiv font">
                <QuoteList data={quotes}/>
            </div>
    );
}