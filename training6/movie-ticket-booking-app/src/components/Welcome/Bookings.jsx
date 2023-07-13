import React, { useEffect, useState } from "react";
import { auth, checkAuth } from "../../authentication";
import { fetchBookingsById } from "../../API-Services/BookingsService";
import { Navigate, useNavigate } from "react-router-dom";
import "./Bookings.css"

const Bookings = () => {
    const [bookings, setBookings] = useState(null)
    const navigate = useNavigate();
    
    useEffect(() => {
        if (checkAuth()) fetchBookings()
    }, [])
    
    const state = auth.getState();
    if (state.userName === "") {
    return <Navigate to="/dashboard" />;
    }

    const fetchBookings = async () => {
        const data = await fetchBookingsById(auth.getState().id);
        setBookings(data)
    }

    return (
        <div className="bookings-container">
            <button type="button" onClick={() => {navigate("/dashboard")}}>Dashboard</button>
            {bookings === null ? <div>No Bookings Found</div> : <div>
                {bookings.map((eachBooking) => {
                    return (
                        <div className="each-booked-ticket">
                            <div>{eachBooking.userName}</div>
                            <div>{eachBooking.theatre}</div>
                            <div>{eachBooking.timing}</div>
                            <div>{eachBooking.region}</div>
                            <div>{eachBooking.movieName}</div>
                            <div>{eachBooking.date}</div>
                            <div>{eachBooking.seatNo}</div>
                        </div>
                    );
                })}
                </div>}
        </div>
    );
}

export default Bookings;