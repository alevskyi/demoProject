import {PageSkeleton} from "../components/PageSkeleton";

export const Main = () => {
    return (
        <PageSkeleton>
            <div className="bodyDiv font">
                <div className="homeEntry">
                    <a href="quote/random">Random quotes</a>
                </div>

                <div className="homeEntry">
                    <a href="quote/lang/en">Random quotes in english</a>
                </div>

                <div className="homeEntry">
                    <a href="quote/lang/ru">Random quotes in russian</a>
                </div>

                <div className="homeEntry">
                    <a href="about">About</a>
                </div>
            </div>
        </PageSkeleton>
    );
}