import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import {PageSkeleton} from "./pages/PageSkeleton";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);

root.render(
    <React.StrictMode>
        <PageSkeleton/>
    </React.StrictMode>
);