export const Help = () => {
    const json: string = "{person : 'person', text : 'text'}";
    return (
        <>
            <p>Visit <a href="api/quote/random">/api/quote/random</a> to get random quote.</p>
            <br></br>
            <p>Visit <a href="api/quote/en">/api/quote/en</a> to get random quote in English.</p>
            <br></br>
            <p>Visit <a href="api/quote/ru">/api/quote/ru</a> to get random quote in Russian.</p>
            <br></br>
            <p>Quotes encoded in JSON, {json}</p>
        </>);
}