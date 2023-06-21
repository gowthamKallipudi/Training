import React, { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar";
import { auth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";

const Bookings = () => {
  const [bookingsData, setBookingsData] = useState(null);

  useEffect(() => {
    fetchBookings();
  }, []);

  const state = auth.getState();
  if (state.userName === "") {
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
      <div>In Bookings Page</div>
      {bookingsData == null ? (
        <div>No Data Found</div>
      ) : (
        <div>
          {bookingsData.map((eachBooking) => {
            return (
              <div key={eachBooking.id}>
                <label>User Name : </label>
                {eachBooking.userName}
                <br />
                <label>Email Id : </label>
                {eachBooking.emailId}
                <br />
                <label>Phone Number : </label>
                {eachBooking.phoneNumber}
                <br />
                <label>Train Number : </label>
                {eachBooking.trainNumber}
                <br />
                <label>Train Name : </label>
                {eachBooking.trainName}
                <br />
                <label>Train Source : </label>
                {eachBooking.source}
                <br />
                <label>Train destination : </label>
                {eachBooking.destination}
                <br />
              </div>
            );
          })}
        </div>
      )}
    </>
  );
};

export default Bookings;
