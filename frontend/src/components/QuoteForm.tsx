import {useForm} from "react-hook-form";
import {useRef} from "react";
import {post} from "../client";
import check from "../images/check.png";
import cross from "../images/cross.png";

const allFields = ['text', 'person'];

export function QuoteForm(props: { successHandler: () => void }) {
    const {register, reset, formState: {errors,}, setError, handleSubmit} = useForm({
        mode: "onSubmit",
        reValidateMode: "onSubmit"
    });
    const validFields = useRef<string[]>([]);

    const onSubmit = (data) => {
        post<any>('quote', data,
            (res) => {
                reset();
                props.successHandler();
            },
            (data) => parseErrors(data)
        );
    }

    const parseErrors = (data) => {
        Object.getOwnPropertyNames(data).forEach(p => setError(p, {type: 'manual', message: data[p]}));
        validFields.current = allFields.filter(f => !Object.getOwnPropertyNames(data).includes(f));
    };

    const validIcon = <img src={check}/>;
    const invalidIcon = <img src={cross}/>;

    return (
        <>
        <p>Post a new quote:</p>

        <form onSubmit={handleSubmit(onSubmit)}>
            <ul>
                <li>
                    <span>Text:</span>
                    <textarea {...register("text")}></textarea>
                </li>
                {errors.text &&
                    <li>
                        <div className="error-msg">{errors.text.message as string}</div>
                        {invalidIcon}
                        {validFields.current.includes('text') && validIcon}
                    </li>}

                <li>
                    <span>Person:</span>
                    <input {...register("person")}></input>
                </li>
                {errors.person &&
                    <li>
                        <div className="error-msg">{errors.person.message as string}</div>
                        {invalidIcon}
                        {validFields.current.includes('person') && validIcon}
                    </li>}

                <li>
                    <span>Language:</span>
                    <input type="radio" {...register("lang")} value="EN" checked={true}/>English
                    <input type="radio" {...register("lang")} value="RU"/>Russian
                </li>
                {errors.lang &&
                    <li>
                        <div className="error-msg">{errors.lang.message as string}</div>
                        {invalidIcon}
                        {validFields.current.includes('lang') && validIcon}
                    </li>}
                <li>
                    <input type="submit" value="Submit"/></li>
            </ul>
        </form>

        <h4>or upload a file:</h4>

        <ul>
            <li>
                <span>File:</span>
                <input type="file" accept="application/xml" name="file"/>
                <input type="submit" value="Submit"/>
            </li>
            <li>
                <a href="/download/quote_template" target="_blank">Download template</a>
            </li>
        </ul>
    </>
    );
}