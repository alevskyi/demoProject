import './QuoteList.css'
import {Link} from "react-router-dom";

export interface Quote{
    id: number,
    lang: string,
    text: string,
    person: string,
    user: string
}

export function QuoteList(props: {data: Quote[]}) {
    return (
        <div className="quotesList">
            {props.data.map((quote, index) =>
                <Link key={index} to={`/quote/${quote.id}`}>
                    <div className="quoteDiv">
                        {quote.text}
                    </div>
                    <div className="autorDiv">-{quote.person}</div>
                </Link>
            )}
        </div>
    );
}