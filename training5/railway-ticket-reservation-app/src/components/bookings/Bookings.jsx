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
    const response = await fetch("http://localhost:8080/api/getAllBookings", {
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
              <th>User Name</th>
              <th>Email ID</th>
              <th>Phone Number</th>
              <th>Train Number</th>
              <th>Train Name</th>
              <th>Source Station</th>
              <th>Destination Station</th>
            </thead>
          </table>
          {bookingsData.map((eachBooking, index) => {
            return (
              <table key={eachBooking.id} className="bookings-table">
                <tr>
                  <td>{index + 1}</td>
                  <td>{eachBooking.userName}</td>
                  <td>{eachBooking.emailId}</td>
                  <td>{eachBooking.phoneNumber}</td>
                  <td>{eachBooking.trainNumber}</td>
                  <td>{eachBooking.trainName}</td>
                  <td>{eachBooking.source}</td>
                  <td>{eachBooking.destination}</td>
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
