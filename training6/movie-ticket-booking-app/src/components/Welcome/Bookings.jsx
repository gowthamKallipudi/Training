import React, { useEffect, useState } from "react";
import { auth } from "../../authentication";
import { fetchBookingsById } from "../../API-Services/BookingsService";
import { useNavigate } from "react-router-dom";

const Bookings = () => {
    const [bookings, setBookings] = useState(null)
    const navigate = useNavigate();
    
    useEffect(() => {
        fetchBookings()
    }, [])

    const state = auth.getState();

    const fetchBookings = async () => {
        const data = await fetchBookingsById(state.id);
        setBookings(data)
    }

    return (
        <>
            <button type="button" onClick={() => {navigate("/dashboard")}}>Dashboard</button>
            {bookings === null ? <div>No Bookings Found</div> : <div>
                {bookings.map((eachBooking) => {
                    return (
                        <div>
                            <p>{eachBooking.userName}</p>
                            <p>{eachBooking.theatre}</p>
                            <p>{eachBooking.timing}</p>
                            <p>{eachBooking.region}</p>
                            <p>{eachBooking.movieName}</p>
                            <p>{eachBooking.date}</p>
                            <p>{eachBooking.seatNo}</p>
                        </div>
                    );
                })}
                </div>}
        </>
    );
}

export default Bookings;