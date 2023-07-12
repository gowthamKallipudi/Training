import React, { useEffect, useState } from "react";
import { fetchTheatresByMovie } from "../../API-Services/TheatreService";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Locations from "./Locations";
import "./TheatreListingPage.css"

const TheatreListingPage = ({movieName, callbackfunction, callbackfunction2}) => {
    const [theatres, setTheatres] = useState(null);
    const [date, setDate] = useState(new Date());
    const [show, setShow] = useState(null);
    const [location, setLocation] = useState(null);
    const [locationPageState, setLocationPageState] = useState(false);

    useEffect(() => {
        fetchTheatres()
    }, [])

    const fetchTheatres = async () => {
        const data = await fetchTheatresByMovie(movieName)
        setTheatres(data)
    }

    const CallBackLocation = (childData) => {
        setLocationPageState(false)
        setLocation(childData)
    }

    if(locationPageState === true){
        return <Locations callbackfunction={CallBackLocation} />
    }

    return (
        <div className="theatre-list">
            <button type="button" onClick={() => {callbackfunction()}}>Dashboard</button>
            <DatePicker selected={date} name="date" onChange={(currentDate) => {setDate(currentDate);}} />
            <input type="text" value={location} placeholder="Select the location" onClick={() => {setLocationPageState(true)}} />
            {theatres !== null && theatres.map((eachTheatre, index) => {
                return(
                    <div key={index}>
                        <p>{eachTheatre.name}</p>
                        {eachTheatre.show.map((eachShow, index) => {
                            if(show === eachShow){
                                return(
                                    <div className="each-show">
                                        <button style={{backgroundColor: "orangered"}} type="button" onClick={() => {setShow(eachShow)}}>{eachShow}</button>
                                    </div>
                                );
                            }
                            return(
                                <div className="each-show">
                                    <button type="button" onClick={() => {setShow(eachShow)}}>{eachShow}</button>
                                </div>
                            );
                        })}
                        {(eachTheatre.show.includes(show) && location !== null) &&
                        <button type="button" onClick={() => {callbackfunction2({date: date.toISOString().slice(0,10), show: show, location: location, movie: movieName, theatre: eachTheatre.name})}}>Book Ticket</button>
                        }
                    </div>
                );
            })}
        </div>
    );
}

export default TheatreListingPage;