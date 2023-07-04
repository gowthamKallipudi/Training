import React, { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar";
import { auth, checkAuth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";
import "./bookings.css";

const Bookings = () => {
  const [bookingsData, setBookingsData] = useState(null);
  const [prompt, setPrompt] = useState(false);
  const [cancelId, setCancelId] = useState(null);

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

  const cancelTicket = async (bookingId) => {
    const response = await fetch(
      `http://localhost:8080/api/cancelBooking/${bookingId}`,
      {
        method: "PUT",
        headers: {
          "content-type": "application/json",
        },
      }
    );
    fetchBookings();
  };

  return (
    <>
      <NavBar />
      {prompt ? (
        <div className="cancel-popup">
          Confirm Cancelling Train Ticket ...
          <br />
          <br />
          <button
            type="button"
            onClick={() => {
              cancelTicket(cancelId);
              setPrompt(!prompt);
            }}
          >
            Confirm Booking
          </button>
          <br />
          <br />
          <button
            type="button"
            onClick={() => {
              setPrompt(!prompt);
            }}
          >
            Cancel
          </button>
        </div>
      ) : bookingsData === null || Object.keys(bookingsData).length === 0 ? (
        <div className="no-data">No Data Found</div>
      ) : (
        <div className="bookings-main">
          <table className="bookings-table">
            <thead>
              <tr>
                <th id="booking-id">Booking Id</th>
                <th id="userName">User Name</th>
                <th>Train Name</th>
                <th id="date">Date</th>
                <th id="coach">Coach</th>
                <th id="seat-number">Seat Number</th>
                <th id="source">Source</th>
                <th id="destination">Destination</th>
                <th id="type">Type</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {bookingsData.map((eachBooking) => {
                const date = new Date();
                var seatType = null;
                switch (eachBooking.seatNo % 6) {
                  case 0:
                  case 1:
                    seatType = "WS";
                    break;
                  case 2:
                  case 5:
                    seatType = "MS";
                    break;
                  case 3:
                  case 4:
                    seatType = "AS";
                    break;
                  default:
                    seatType = "";
                    break;
                }
                return (
                  <tr key={eachBooking.bookingId}>
                    <td id="booking-id">{eachBooking.bookingId}</td>
                    <td id="userName">{eachBooking.userName}</td>
                    <td>{eachBooking.trainName}</td>
                    <td id="date">{eachBooking.date}</td>
                    <td id="coach">{eachBooking.coach}</td>
                    <td id="seat-number">
                      {eachBooking.seatNo} - {seatType}
                    </td>
                    <td id="source">{eachBooking.source}</td>
                    <td id="destination">{eachBooking.destination}</td>
                    <td id="type">{eachBooking.type}</td>
                    <td>
                      {eachBooking.status === "Booked" &&
                      eachBooking.date >= date.toISOString().slice(0, 10) ? (
                        <div>
                          <button
                            type="button"
                            onClick={() => {
                              setCancelId(eachBooking.bookingId);
                              setPrompt(!prompt);
                            }}
                          >
                            Cancel Ticket
                          </button>
                        </div>
                      ) : (
                        <div>{eachBooking.status}</div>
                      )}
                    </td>
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
