import React, { useEffect, useState } from "react";
import { auth, checkAuth } from "../../authentication";
import { deleteBooking, fetchBookingsById } from "../../API-Services/BookingsService";
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

    const deleteBookingById = async (id) => {
        const data = await deleteBooking(id);
        console.log(data)
    }

    return (
        <div className="bookings-container">
            <button type="button" onClick={() => {navigate("/dashboard")}}>Dashboard</button>
            <div>
                {bookings === null ? <div>No Bookings Found</div> : <div className="bookings">
                    {bookings.map((eachBooking) => {
                        const currentDate = (new Date()).toISOString().slice(0, 10)
                        return (
                            <div className="each-booked-ticket" key={eachBooking.id}>
                                <div>User Name : {eachBooking.userName}</div>
                                <div>Theatre : {eachBooking.theatre}</div>
                                <div>Show : {eachBooking.timing}</div>
                                <div>Region : {eachBooking.region}</div>
                                <div>Movie Name : {eachBooking.movieName}</div>
                                <div>Show Date : {eachBooking.date}</div>
                                <div>Seat No : {eachBooking.seatNo}</div>
                                <div>
                                    {(currentDate <= eachBooking.date) ? 
                                    <button type="button" onClick={() => {deleteBookingById(eachBooking.id); fetchBookings()}}>Cancel</button>
                                    :
                                    <div style={{color: "orange", fontSize: "large"}}>Movie Completed</div>
                                    }
                                </div>
                            </div>
                        );
                    })}
                    </div>}
            </div>
        </div>
    );
}

export default Bookings;