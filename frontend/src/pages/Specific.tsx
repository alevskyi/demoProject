import {QuoteList} from "../components/QuoteList";
import {PageSkeleton} from "../components/PageSkeleton";

export const Specific = () => {
    return (
        <PageSkeleton>
            <div className="bodyDiv">
                <QuoteList/>
            </div>
        </PageSkeleton>
    );
}