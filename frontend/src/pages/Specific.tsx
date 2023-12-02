import {Quote} from "../components/Quote";
import {PageSkeleton} from "../components/PageSkeleton";

export const Specific = () => {
    return (
        <PageSkeleton>
            <div className="bodyDiv">
                <Quote/>
            </div>
        </PageSkeleton>
    );
}