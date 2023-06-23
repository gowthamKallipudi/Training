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
  if (state.lastName === "") {
    return <Navigate to="/login" />;
  }

  const fetchBookings = async () => {
    const response = await fetch(`http://localhost:8080/api/getBookings/${state.id}`, {
      method: "GET",
      headers: {
        "content-type": "application/json",
      },
    });
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
              <th>SNO.</th>
              <th>Booking Id</th>
              <th>User Id</th>
              <th>Route Id</th>
              <th>Date</th>
              <th>Coach</th>
              <th>Seat Number</th>
            </thead>
          </table>
          {bookingsData.map((eachBooking, index) => {
            return (
              <table key={eachBooking.id} className="bookings-table">
                <tr>
                  <td>{index + 1}</td>
                  <td>{eachBooking.bookingId}</td>
                  <td>{eachBooking.userId}</td>
                  <td>{eachBooking.routeId}</td>
                  <td>{eachBooking.date}</td>
                  <td>{eachBooking.coach}</td>
                  <td>{eachBooking.seatNo}</td>
                </tr>
              </table>
            );
          })}
        </div>
      )}
    </>
  );
};

export default Bookings;
