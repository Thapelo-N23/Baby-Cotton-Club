import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Product from "./Product";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
    <React.StrictMode>
        <div>
            <h1>BabyCottonClub</h1>
            <Product />

        </div>
    </React.StrictMode>
);
