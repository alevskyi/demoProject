import {RegisterForm} from "../components/RegisterForm";
import {PageSkeleton} from "../components/PageSkeleton";

export const Register = () => {
    return (
        <PageSkeleton>
            <div className="bodyDiv font">
                <RegisterForm/>
            </div>
        </PageSkeleton>
    );
}