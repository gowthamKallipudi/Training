import React, { useEffect, useState } from "react";
import { fetchTheatreByLocation, fetchTheatresByMovie } from "../../API-Services/TheatreService";
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
    const [currTheatre, setCurrTheatre] = useState(null);

    useEffect(() => {
        fetchTheatres()
    }, [])

    const fetchTheatres = async () => {
        const data = await fetchTheatresByMovie(movieName)
        setTheatres(data)
    }

    const fetchTheatreLocation = async (current) => {
        const data = await fetchTheatreByLocation(movieName, current)
        setTheatres(data)
    }

    const CallBackLocation = (childData) => {
        setLocationPageState(false)
        if(childData !== ""){
            fetchTheatreLocation(childData)
            setLocation(childData)
        }
    }

    if(locationPageState === true){
        return <Locations callbackfunction={CallBackLocation} />
    }

    return (
        <div className="theatre-list">
            <div className="theatre-nav">
                <button className="nav-button" type="button" onClick={() => {callbackfunction()}}>Dashboard</button>
                <DatePicker className="date-picker" selected={date} name="date" onChange={(currentDate) => {setDate((currentDate >= date) ? currentDate : date)}} />
                <button className="nav-button" onClick={() => {setLocationPageState(true)}}>{(location !== null) ? location : "Select Location"}</button>
            </div>
            <div className="theatres">
                {theatres !== null && theatres.map((eachTheatre, index) => {
                    return(
                        <div key={index} className="each-theatre">
                            <div style={{fontSize: "large", fontWeight: "bold"}}>{eachTheatre.name}</div>
                            {eachTheatre.show.map((eachShow, index) => {
                                if(show === eachShow && eachTheatre.name === currTheatre){
                                    return(
                                        <div className="each-show">
                                            <button style={{backgroundColor: "orange"}} type="button" onClick={() => {setShow(eachShow); setCurrTheatre(eachTheatre.name)}}>{eachShow}</button>
                                        </div>
                                    );
                                }
                                return(
                                    <div className="each-show">
                                        <button type="button" onClick={() => {setShow(eachShow); setCurrTheatre(eachTheatre.name)}}>{eachShow}</button>
                                    </div>
                                );
                            })}
                            {(eachTheatre.show.includes(show) && location !== null) ?
                            <div>
                            <button className="book-button" type="button" onClick={() => {callbackfunction2({date: date.toISOString().slice(0,10), show: show, location: location, movie: movieName, theatre: eachTheatre.name})}}>Book Ticket</button>
                            </div> :
                            <div style={{color: "orangered", fontSize: "large"}}>select location and show to book</div>
                            }
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default TheatreListingPage;