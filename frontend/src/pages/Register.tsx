import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";
import {post} from "../client";
import cross from "../images/cross.png";
import check from "../images/check.png";
import {useRef} from "react";

const allFields = ['username', 'password', 'confirmPassword'];

export const Register = () => {

    const {register, formState: {errors, touchedFields}, setError, handleSubmit} = useForm({mode: "onSubmit", reValidateMode: "onSubmit"});
    const navigate = useNavigate();
    const validFields = useRef<string[]>([]);

    const onSubmit = (data: any) => {
        post<void>('auth/register', data, (data) => navigate('/'), (res) => parseErrors(res))
    }

    const parseErrors = (data) => {
        Object.getOwnPropertyNames(data).forEach(p => setError(p, {type: 'manual', message: data[p]}))
        validFields.current = allFields.filter(f => !Object.getOwnPropertyNames(data).includes(f));
    };

    const validIcon = <td><img src={check}/></td>;
    const invalidIcon = <td><img src={cross}/></td>;

    return (
        <div className="bodyDiv font">
            <div className="titleBlock">
                <div className="emphasizedText">Registration</div>
            </div>
            <div>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <table className="table">
                        <tbody>
                        <tr>
                            <td><span>Username:</span></td>
                            <td><input type="text" {...register("username")}/></td>
                        </tr>
                        {errors.username &&
                            <tr>
                                <td colSpan={3}>
                                    <div className="fieldError">{errors.username.message as string}</div>
                                </td>
                                {invalidIcon}
                            </tr>}
                        {validFields.current.includes('username') && validIcon}

                        <tr>
                            <td><span>Password:</span></td>
                            <td><input type="password" {...register("password")}/></td>
                        </tr>
                        {errors.password &&
                            <tr>
                                <td colSpan={3}>
                                    <div className="fieldError">{errors.password.message as string}</div>
                                </td>
                                {invalidIcon}
                            </tr>}
                        {validFields.current.includes('password') && validIcon}

                        <tr>
                            <td><span>Confirm password:</span></td>
                            <td><input type="password" {...register("confirmPassword")}/></td>
                        </tr>
                        {errors.confirmPassword &&
                            <tr>
                                <td colSpan={3}>
                                    <div className="fieldError">{errors.confirmPassword.message as string}</div>
                                </td>
                                {invalidIcon}
                            </tr>}
                        {validFields.current.includes('confirmPassword') && validIcon}
                        <tr>
                            <td>
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