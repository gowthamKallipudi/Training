import React, { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar";
import { auth, checkAuth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";
import "./bookings.css";

const Bookings = () => {
  const [bookingsData, setBookingsData] = useState(null);

  useEffect(() => {
    if (checkAuth()) fetchBookings();
  }, []);

  const state = auth.getState();
  if (state.userName === "") {
    return <Navigate to="/login" />;
  }

  const fetchBookings = async () => {
    const response = await fetch(
      `http://localhost:8080/api/getBookings/${state.id}`,
      {
        method: "GET",
        headers: {
          "content-type": "application/json",
        },
      }
    );
    const data = await response.json();
    setBookingsData(data);
  };

  return (
    <>
      <NavBar />
      {bookingsData == null ? (
        <div>No Data Found</div>
      ) : (
        <div className="bookings-main">
          <table className="bookings-table">
            <thead>
              <tr>
                <th>Booking Id</th>
                <th>User Name</th>
                <th>Train Name</th>
                <th>Date</th>
                <th>Coach</th>
                <th>Seat Number</th>
                <th>Source</th>
                <th>Destination</th>
              </tr>
            </thead>
            <tbody>
              {bookingsData.map((eachBooking) => {
                return (
                  <tr key={eachBooking.id}>
                    <td>{eachBooking.bookingId}</td>
                    <td>{eachBooking.userName}</td>
                    <td>{eachBooking.trainName}</td>
                    <td>{eachBooking.date}</td>
                    <td>{eachBooking.coach}</td>
                    <td>{eachBooking.seatNo}</td>
                    <td>{eachBooking.source}</td>
                    <td>{eachBooking.destination}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      )}
    </>
  );
};

export default Bookings;
