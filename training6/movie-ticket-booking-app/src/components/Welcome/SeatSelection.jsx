import React, { useState } from "react";
import "./SeatSelection.css";

const SeatSelection = ({seatCapacity, rowCapacity, bookedSeats, callbackfunction}) => {
    const [current, setCurrent] = useState([]);

    const noOfRows = seatCapacity/rowCapacity
    const seats = []
    for(let i = 0; i < noOfRows; i++){
        const row = []
        for(let j = 1; j < rowCapacity + 1; j++) {
            row[j - 1] = String.fromCharCode(65 + i) + j
        }
        seats[i] = row
    }

    return(
        <div className="seats">
            {seats.map((eachRow, index) => {
                return(
                    <div key={index}>
                        {eachRow.map((eachSeat, index) => {
                            if(bookedSeats.includes(eachSeat)){
                                return (
                                    <button key={index} style={{backgroundColor: "red"}}>|{eachSeat}|</button>
                                )
                            } else if(current.includes(eachSeat)) {
                                return (
                                    <button key={index} style={{backgroundColor: "yellow"}} onClick={() => {
                                        if(current.includes(eachSeat)){
                                            const array = []
                                            for(let i = 0; i < current.length; i++){
                                                if(current[i] !== eachSeat){
                                                    array.push(current[i])
                                                }
                                            }
                                            setCurrent(array)
                                        }
                                        else {
                                            setCurrent([...current, eachSeat])
                                        }
                                    }}>|{eachSeat}|</button>
                                )
                            }
                            return (
                                <button key={index} style={{backgroundColor: "blue"}} onClick={() => {
                                    if(current.includes(eachSeat)){
                                        const array = []
                                        for(let i = 0; i < current.length; i++){
                                            if(current[i] !== eachSeat){
                                                array.push(current[i])
                                            }
                                        }
                                        setCurrent(array)
                                    }
                                    else {
                                        setCurrent([...current, eachSeat])
                                    }
                                }}>|{eachSeat}|</button>
                            )
                        })}
                    </div>
                );
            })}
            <br />
            {seats[0].map((eachRow, index) => {
                return (
                    <span key={index}>_-_-_-_-</span>
                )   
            })}
            {current.map(each => {
                return(
                    <p>{each}</p>
                )
            })}
            <button type="button" onClick={() => {callbackfunction(current)}}>Book</button>
        </div>
    );

}

export default SeatSelection;