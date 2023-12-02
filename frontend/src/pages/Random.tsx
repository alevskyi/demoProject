import {Quote} from "../components/Quote";
import {PageSkeleton} from "../components/PageSkeleton";

export const Random = () => {
    return (
        <PageSkeleton>
            <div className="bodyDiv font">
                <Quote/>
            </div>
        </PageSkeleton>
    );
}