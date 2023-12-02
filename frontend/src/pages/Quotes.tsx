import {Quote, QuoteList} from "../components/QuoteList";
import {PageSkeleton} from "../components/PageSkeleton";
import {useEffect, useState} from "react";
import axios from "axios";
import {useLocation} from "react-router-dom";
import {Path} from "@remix-run/router/history";

export const Quotes = () => {
    const location: Path = useLocation();

    useEffect(() => {
        axios.get<Quote[]>(`${process.env.REACT_APP_BACKEND_URL}${location.pathname}`)
            .then(resp => setQuotes(resp.data))
            .catch(err => console.log(err));
    }, []);
    const [quotes, setQuotes] = useState<Quote[]>([]);
    return (
        <PageSkeleton>
            <div className="bodyDiv font">
                <QuoteList data={quotes}/>
            </div>
        </PageSkeleton>
    );
}