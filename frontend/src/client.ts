import  axios  from "axios";

export const get = <T>(relativePath: string, succeeded: (response: T) => void,
                       rejected: (code: any) => void = (res: any) => console.log('Rejected\n' + res)) => {
    axios.get<T>(getAbsolutePath(relativePath))
        .then(res => succeeded(res.data), res => rejected(res))
        .catch(err => console.log(err));
}

export const post = <T>(relativePath: string, data: T, succeeded: (response: T) => void,
                       rejected: (code: any) => void = (res: any) => console.log('Rejected\n' + res)) => {
    axios.post(getAbsolutePath(relativePath), data)
        .then(res => succeeded(res.data), res => rejected(res.response.data))
        .catch(err => console.log(err));
}

const getAbsolutePath = (relativePath: string): string => {
    const sanitizedPath = relativePath.startsWith('/') ? relativePath : '/' + relativePath;
    const path =  `${process.env.REACT_APP_BACKEND_URL}${sanitizedPath}`
    return path;
}
