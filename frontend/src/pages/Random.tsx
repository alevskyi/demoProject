import {QuoteList} from "../components/QuoteList";
import {PageSkeleton} from "../components/PageSkeleton";

export const Random = () => {
    return (
        <PageSkeleton>
            <div className="bodyDiv font">

                <QuoteList/>
            </div>
        </PageSkeleton>
    );
}