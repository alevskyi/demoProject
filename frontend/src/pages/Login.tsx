import {Nav} from "../components/Nav";
import {LoginForm} from "../components/LoginForm";

export const Login = () => {
    return (
        <div className="container">
            <div className="header font">
                <Nav/>
            </div>

            <div className="content">
                <div className="bodyDiv font">
                    <div className="titleBlock">
                        <div className="emphasizedText">Login</div>
                    </div>

                    <LoginForm/>

                </div>
            </div>
            <div className="footer font"/>
        </div>);
}