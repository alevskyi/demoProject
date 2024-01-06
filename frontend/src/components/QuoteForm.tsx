import {useForm} from "react-hook-form";
import {ChangeEvent, useRef, useState} from "react";
import {post} from "../client";
import check from "../images/check.png";
import cross from "../images/cross.png";

const allFields = ['text', 'person'];

interface FormValues {
    text: string,
    person: string,
    lang: 'EN' | 'UA'
}

export function QuoteForm(props: { successHandler: () => void }) {
    const {register, reset, formState: {errors,}, setError, handleSubmit} = useForm<FormValues>({
        mode: "onSubmit",
        reValidateMode: "onSubmit",
        defaultValues: {lang: 'EN'},
    });
    const validFields = useRef<string[]>([]);
    const [file, setFile] = useState<File>();

    const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setFile(e.target.files[0]);
        }
    };

   const uploadFile = () => {
       if (!file) {
           return;
       }
       const form = new FormData();
       form.append('file', file);
       post('quote/upload', form, () => {props.successHandler()});
   }

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
        // @ts-ignore
        Object.getOwnPropertyNames(data).forEach(p => setError(p, {type: 'manual', message: data[p]}));
        validFields.current = allFields.filter(f => !Object.getOwnPropertyNames(data).includes(f));
    };

    const validIcon = <img src={check}/>;
    const invalidIcon = <img src={cross}/>;

    return (
        <>
            <h3>Post a new quote:</h3>

            <form onSubmit={handleSubmit(onSubmit)}>
                <ul>
                    <li className="dirtyCenter">
                        <span>Text</span>
                        <textarea {...register("text")}></textarea>
                        <>
                            {errors.text && invalidIcon}
                            {validFields.current.includes('text') && validIcon}
                        </>
                    </li>
                    {errors.text &&
                        <li>
                            <p className="error-msg">{errors.text.message as string}</p>
                        </li>
                    }

                    <li className="dirtyCenter">
                        <span>Person</span>
                        <input type="text" autoComplete="false" {...register("person")}></input>
                        <>
                            {errors.person && invalidIcon}
                            {validFields.current.includes('person') && validIcon}
                        </>
                    </li>
                    {errors.person &&
                        <li>
                            <p className="error-msg">{errors.person.message as string}</p>
                        </li>
                    }

                    <li className="dirtyCenter">
                        <span>Language</span>
                        <input type="radio" {...register("lang")} value="EN"/><span>English</span>
                        <input type="radio" {...register("lang")} value="UA"/><span>Ukrainian</span>
                    </li>

                    <li>
                        <input type="submit" value="Submit"/>
                    </li>
                </ul>
            </form>

            <h3>or upload a file:</h3>

            <div>
                <li>
                    <span>File</span>
                    <input type="file" accept="application/xml" name="file" onChange={handleFileChange}/>
                    <input type="submit" value="Submit" onClick={uploadFile}/>
                </li>
                <li>
                    <a href="quotes_template.xml" download="template.xml"><h3>Download template</h3></a>
                </li>
            </div>
        </>
    );
}