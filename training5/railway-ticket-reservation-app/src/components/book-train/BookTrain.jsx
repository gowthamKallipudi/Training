import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { auth } from "../../utilities/authentication";
import { Navigate } from "react-router";
import NavBar from "../navbar/NavBar";
import "./booktrain.css";
import { useNavigate } from "react-router-dom";

const initialData = {
  source: "",
  destination: "",
};

const bookingInitialData = {
  userId: "",
  date: "",
  coach: "",
  source: "",
  destination: "",
  trainName: "",
  day: "",
};

const BookTrain = () => {
  const [availableTrains, setAvailableTrains] = useState(null);
  const [station, setStation] = useState(initialData);
  const [seats, setSeats] = useState(null);
  const [date, setDate] = useState(new Date());
  const [booking, setBooking] = useState(bookingInitialData);
  const [bookingState, setBookingState] = useState(false);
  const navigate = useNavigate();

  const state = auth.getState();
  if (state.lastName === "") {
    return <Navigate to="/login" />;
  }

  const fetchAvailableTrains = async () => {
    const response = await fetch(
      `http://localhost:8080/api/getTrainBetween?source=${
        station.source
      }&destination=${station.destination}&day=${date.getDay()}`,
      {
        method: "GET",
        headers: {
          "content-type": "application/json",
        },
      }
    );
    const data = await response.json();
    if (response.status === 200) setAvailableTrains(data);
    console.log(data);
  };

  const fetchSeats = async (trainName) => {
    const response = await fetch(
      `http://localhost:8080/api/availableTrains?name=${trainName}&date=${date
        .toISOString()
        .slice(0, 10)}&day=${date.getDay()}`,
      {
        method: "GET",
        headers: {
          "content-type": "application/json",
        },
      }
    );
    const data = await response.json();
    setSeats(data);
    console.log(data);
  };

  const addBooking = async () => {
    const response = await fetch(`http://localhost:8080/api/addBooking`, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(booking),
    });
    const data = await response.json();
    console.log(data);
  };

  return (
    <>
      <NavBar />
      <div className="book-train">
        <div className="book-train-input-container">
          <div>
            <label>Date of Journey : </label>
            <DatePicker
              selected={date}
              name="date"
              onChange={(currentDate) => {
                setDate(currentDate);
                setAvailableTrains(null);
                setSeats(null);
              }}
            />
          </div>
          <div>
            <label>Source Station : </label>
            <input
              type="text"
              name="source"
              value={station.source}
              placeholder="Enter your source station"
              onChange={(e) => {
                setStation({ ...station, [e.target.name]: e.target.value });
                setBooking({ ...booking, [e.target.name]: e.target.value });
                setAvailableTrains(null);
                setSeats(null);
              }}
            />
          </div>
          <div>
            <label>Destination Station : </label>
            <input
              type="text"
              name="destination"
              value={station.destination}
              placeholder="Enter your destination station"
              onChange={(e) => {
                setStation({ ...station, [e.target.name]: e.target.value });
                setBooking({ ...booking, [e.target.name]: e.target.value });
                setAvailableTrains(null);
                setSeats(null);
              }}
            />
          </div>
          <div>
            <button
              type="submit"
              onClick={() => {
                fetchAvailableTrains();
                setBooking({
                  ...booking,
                  date: date.toISOString().slice(0, 10),
                  day: date.getDay(),
                });
              }}
            >
              Search Trains
            </button>
          </div>
        </div>
        {bookingState ? (
          <div className="booking-popup">
            Confirm Adding Train Ticket ...
            <br />
            <br />
            <button
              type="button"
              onClick={() => {
                addBooking();
                setBookingState(false);
                navigate("/bookings");
              }}
            >
              Confirm Booking
            </button>
            <br />
            <br />
            <button
              type="button"
              onClick={() => {
                setBookingState(false);
              }}
            >
              Cancel
            </button>
          </div>
        ) : (
          availableTrains != null && (
            <div className="book-train-sub-container">
              <div className="train-display-container child-cont">
                <table className="book-train-table">
                  <thead>
                    <th>Train Name</th>
                    <th>Weekly Status</th>
                    <th>Availability</th>
                  </thead>
                  <tbody>
                    {Object.keys(availableTrains).map((eachKey, index) => {
                      return (
                        <tr key={index}>
                          <td>{eachKey}</td>
                          <td>
                            {availableTrains[eachKey].map((eachDay) => {
                              return <div>{eachDay}</div>;
                            })}
                          </td>
                          <td>
                            <button
                              type="submit"
                              onClick={() => {
                                fetchSeats(eachKey);
                                setBooking({
                                  ...booking,
                                  trainName: eachKey,
                                  userId: state.id,
                                });
                              }}
                            >
                              Check Availability
                            </button>
                          </td>
                        </tr>
                      );
                    })}
                  </tbody>
                </table>
              </div>
              {seats !== null && (
                <div className="availability-container child-cont">
                  <table className="availability-table">
                    <thead>
                      <th>Coach</th>
                      <th>Available</th>
                    </thead>
                    <tbody>
                      {Object.keys(seats).map((each, index) => {
                        return (
                          <tr key={index}>
                            <td>{each}</td>
                            <td>{seats[each]}</td>
                          </tr>
                        );
                      })}
                      <tr>
                        <td colSpan={2}>
                          <input
                            type="text"
                            name="coach"
                            value={booking.coach}
                            placeholder="Enter your Coach"
                            onChange={(e) => {
                              setBooking({
                                ...booking,
                                [e.target.name]: e.target.value,
                              });
                            }}
                          />
                        </td>
                      </tr>
                      <tr>
                        <td colSpan={2}>
                          <button
                            type="submit"
                            onClick={() => {
                              setBookingState(true);
                            }}
                          >
                            Add Ticket
                          </button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          )
        )}
      </div>
    </>
  );
};

export default BookTrain;
