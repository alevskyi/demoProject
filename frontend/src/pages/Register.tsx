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
        <div className="center">
            <li>
                <h1>Registration</h1>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <ul>
                        <li className="dirtyCenter">
                            <span>Username</span>
                            <input type="text" {...register("username")}/>
                            <>
                                {errors.username && invalidIcon}
                                {validFields.current.includes('username') && validIcon}
                            </>
                        </li>
                        {errors.username &&
                            <li>
                                <p className="error-msg">{errors.username.message as string}</p>
                            </li>
                        }

                        <li className="dirtyCenter">
                            <span>Password</span>
                            <input type="password" {...register("password")}/>
                            <>
                                {errors.password && invalidIcon}
                                {validFields.current.includes('password') && validIcon}
                            </>
                        </li>
                        {errors.password &&
                            <li>
                                <p className="error-msg">{errors.password.message as string}</p>
                            </li>
                        }

                        <li className="dirtyCenter">
                            <span>Confirm password</span>
                            <input type="password" {...register("confirmPassword")}/>
                            <>
                                {errors.confirmPassword && invalidIcon}
                                {validFields.current.includes('confirmPassword') && validIcon}
                            </>
                        </li>
                        {errors.confirmPassword &&
                            <li>
                                <p className="error-msg">{errors.confirmPassword.message as string}</p>
                            </li>
                        }
                        <li>
                            <h1><input type="submit" value="Submit"/></h1>
                        </li>
                    </ul>
                </form>
            </li>
        </div>
    );
}