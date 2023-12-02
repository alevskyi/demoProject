import {PageSkeleton} from "../components/PageSkeleton";

export const Main = () => {
    return (
        <PageSkeleton>
            <div className="bodyDiv font">
                <div className="homeEntry">
                    <a href="quotes/random">Random quotes</a>
                </div>

                <div className="homeEntry">
                    <a href=".#quotes/en">Random quotes in english</a>
                </div>

                <div className="homeEntry">
                    <a href="quotes/ru">Random quotes in russian</a>
                </div>

                <div className="homeEntry">
                    <a href="about">About</a>
                </div>
            </div>
        </PageSkeleton>
    );
}