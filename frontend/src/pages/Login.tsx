import {LoginForm} from "../components/LoginForm";
import {PageSkeleton} from "../components/PageSkeleton";

export const Login = () => {
    return (
        <PageSkeleton>
            <div className="bodyDiv font">
                <div className="titleBlock">
                    <div className="emphasizedText">Login</div>
                </div>
                <LoginForm/>
            </div>
        </PageSkeleton>
    );
}