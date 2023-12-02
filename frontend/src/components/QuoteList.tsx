import './QuoteList.css'

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