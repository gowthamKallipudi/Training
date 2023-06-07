import React, { useEffect } from "react";
// import {readFile} from "fs";
import file from "./data.json";

const FirstPage = () => {
    useEffect(() => {
        // readFile("./data.json", (error, data) => {
        //     if(error) throw error;
        //     const acquiredData = JSON.stringify(data);
        //     console.log(acquiredData);
        // })
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await fetch(file);
        const data = await response.json();
        console.log(data);
    }

    return (
        <>
            <h1>Hello Viewers</h1>
        </>
    );
}

export default FirstPage;