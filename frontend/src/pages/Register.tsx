import {Nav} from "../components/Nav";
import {RegisterForm} from "../components/RegisterForm";

export const Register = () => {
    return (
        <div className="container">
            <div className="header font">
                <Nav/>
            </div>
            <div className="content">
                <div className="bodyDiv font">
                    <RegisterForm/>
                </div>
            </div>
            <div className="footer font"/>
        </div>
    );
}