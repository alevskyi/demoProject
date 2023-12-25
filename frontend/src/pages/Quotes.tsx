import {Quote, QuoteList} from "../components/QuoteList";
import {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";

import {get} from "../client";

export const Quotes = () => {
    const fetchUrl = useLocation()["pathname"];
    useEffect(() => {
        get<Quote[]>(fetchUrl, (data) => setQuotes(data))
    }, [fetchUrl]);

    const [quotes, setQuotes] = useState<Quote[]>([]);

    return (
        <QuoteList data={quotes}/>
    );
}