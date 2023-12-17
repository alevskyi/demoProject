import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";
import {post} from "../client";

export const Login = (props: { loginHandler: () => void }) => {
    const {register, handleSubmit} = useForm();
    const navigate = useNavigate()

    const onSubmit = (data: any) => {
        post<any>('auth/login', data, (data) => {
            props.loginHandler();
            navigate('/');
        });
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

                        <tr>
                            <td colSpan={2}>
                                <div className="fieldError">This is error mesg</div>
                            </td>
                        </tr>

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