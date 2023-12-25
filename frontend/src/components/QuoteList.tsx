import {Link} from "react-router-dom";

export interface Quote {
    id: number,
    lang: string,
    text: string,
    person: string,
    user: string
}

export function QuoteList(props: { data: Quote[] }) {
    return (
        <>
            {props.data.map((quote, index) =>
                <Link key={index} to={`/quote/${quote.id}`}>
                    <p className="quote">{quote.text}</p>
                    <p className="person">-{quote.person}</p>
                </Link>
            )}
        </>
    );
}