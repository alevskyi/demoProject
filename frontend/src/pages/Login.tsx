import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";
import {post} from "../client";
import {useState} from "react";

export const Login = (props: { loginHandler: (username: string) => void }) => {
    const {register, handleSubmit} = useForm();
    const [error, setError] = useState<string>();
    const navigate = useNavigate()

    const onSubmit = (data) => {
        post<any>('auth/login', data,
            (res) => {
                props.loginHandler(data.username);
                navigate('/');
            },
            (data) => setError(data)
        );
    }

    return (
        <div className="col">
            <h3 className="emphasizedText">Login</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <ul>
                    <li>
                        <span>Username:</span>
                        <input type="text" {...register("username")}/>
                    </li>

                    <li>
                        <span>Password:</span>
                        <input type="password" {...register("password")}/>
                    </li>
                    {error &&
                        <p className="error-msg">{error}</p>
                    }
                    <li>
                        <input type="submit" value="Submit"/>
                    </li>
                </ul>
            </form>
        </div>
    );
}