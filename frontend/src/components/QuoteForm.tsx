import {useForm} from "react-hook-form";
import {useRef} from "react";
import {post} from "../client";
import check from "../images/check.png";
import cross from "../images/cross.png";

const allFields = ['text', 'person'];

export function QuoteForm(props: {successHandler: () => void}) {
    const {register, reset, formState: {errors, }, setError, handleSubmit} = useForm({
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

    const validIcon = <td><img src={check}/></td>;
    const invalidIcon = <td><img src={cross}/></td>;

    return <>
        <span>Post a new quote:</span>

        <form onSubmit={handleSubmit(onSubmit)}>
            <table className="table">
                <tbody>
                <tr>
                    <td><span>Text:</span></td>
                    <td><textarea {...register("text")}></textarea></td>
                </tr>
                {errors.text &&
                    <tr>
                        <td>
                            <div className="fieldError">{errors.text.message as string}</div>
                        </td>
                        {invalidIcon}
                        {validFields.current.includes('text') && validIcon}
                    </tr>}

                <tr>
                    <td><span>Person:</span></td>
                    <td><input {...register("person")}></input></td>
                </tr>
                {errors.person &&
                    <tr>
                        <td>
                            <div className="fieldError">{errors.person.message as string}</div>
                        </td>
                        {invalidIcon}
                        {validFields.current.includes('person') && validIcon}
                    </tr>}

                <tr>
                    <td><span>Language:</span></td>
                    <td>
                        <input type="radio" {...register("lang")} value="EN" checked={true}/>English
                        <input type="radio" {...register("lang")} value="RU"/>Russian
                    </td>
                </tr>
                {errors.lang &&
                    <tr>
                        <td>
                            <div className="fieldError">{errors.lang.message as string}</div>
                        </td>
                        {invalidIcon}
                        {validFields.current.includes('lang') && validIcon}
                    </tr>}

                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
                </tbody>
            </table>
        </form>

        <span>or upload a file:</span>

        <form action="quotes/xml" encType="multipart/form-data" method="post">
            <table className="table">
                <tbody>
                <tr>
                    <td><span>File:</span></td>
                    {/*style="font-family: cabazon; font-size: 1em;"*/}
                    <td><input type="file" accept="application/xml" name="file"/></td>
                    {/*style="font-family: cabazon; font-size: 1em;"*/}
                    <td><input type="submit" value="Submit"/></td>
                </tr>
                </tbody>
            </table>
        </form>
        {/*style="font-size: 1.3em;"*/}
        <a href="/download/quote_template" target="_blank">Download template</a>
    </>;
}