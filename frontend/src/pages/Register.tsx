import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";
import {post} from "../client";
import cross from "../images/cross.png";
import check from "../images/check.png";
import {useRef} from "react";

const allFields = ['username', 'password', 'confirmPassword'];

export const Register = () => {

    const {register, formState: {errors}, setError, handleSubmit} = useForm({
        mode: "onSubmit",
        reValidateMode: "onSubmit"
    });
    const navigate = useNavigate();
    const validFields = useRef<string[]>([]);

    const onSubmit = (data: any) => {
        post<void>('auth/register', data, (data) => navigate('/'), (res) => parseErrors(res))
    }

    const parseErrors = (data) => {
        Object.getOwnPropertyNames(data).forEach(p => setError(p, {type: 'manual', message: data[p]}))
        validFields.current = allFields.filter(f => !Object.getOwnPropertyNames(data).includes(f));
    };

    const validIcon = <img src={check}/>;
    const invalidIcon = <img src={cross}/>;

    return (
        <div className="col">
            <h3 className="emphasizedText">Registration</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <ul>
                    <li>
                        <span>Username:</span>
                        <input type="text" {...register("username")}/>
                    </li>
                    {errors.username &&
                        <li>
                            <div className="error-msg">{errors.username.message as string}</div>
                            {invalidIcon}
                        </li>}
                    {validFields.current.includes('username') && validIcon}

                    <li>
                        <span>Password:</span>
                        <input type="password" {...register("password")}/>
                    </li>
                    {errors.password &&
                        <li>

                            <div className="error-msg">{errors.password.message as string}</div>

                            {invalidIcon}
                        </li>}
                    {validFields.current.includes('password') && validIcon}

                    <li>
                        <span>Confirm password:</span>
                        <input type="password" {...register("confirmPassword")}/>
                    </li>
                    {errors.confirmPassword &&
                        <li>
                            <div className="error-msg">{errors.confirmPassword.message as string}</div>
                            {invalidIcon}
                        </li>}
                    {validFields.current.includes('confirmPassword') && validIcon}
                    <li>
                        <input type="submit" value="Submit"/>
                    </li>
                </ul>
            </form>
        </div>
    );
}