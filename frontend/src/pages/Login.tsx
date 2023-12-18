import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";
import {post} from "../client";
import {useState} from "react";

export const Login = (props: { loginHandler: () => void }) => {
    const {register, handleSubmit} = useForm();
    const [error, setError] = useState<string>();
    const navigate = useNavigate()

    const onSubmit = (data: any) => {
        post<any>('auth/login', data,
            (data) => {
                props.loginHandler();
                navigate('/');
            },
            (data) => setError(data)
        );
    }

    return (
        <div className="bodyDiv font">
            <div className="titleBlock">
                <div className="emphasizedText">Login</div>
            </div>
            <div>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <table className="table">
                        <tbody>
                        <tr>
                            <td><span>Username:</span></td>
                            <td colSpan={2}><input type="text" {...register("username")}/></td>
                        </tr>

                        <tr>
                            <td><span>Password:</span></td>
                            <td><input type="password" {...register("password")}/></td>
                        </tr>

                        {error &&
                        <tr>
                            <td colSpan={2}>
                                <div className="fieldError">{error}</div>
                            </td>
                        </tr>
                        }
                        <tr>
                            <td colSpan={2}>
                                <input type="submit" value="Submit"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    );
}