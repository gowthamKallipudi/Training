import React, { useState, useRef, useEffect } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { auth, checkAuth } from "../../utilities/authentication";
import { Navigate } from "react-router";
import NavBar from "../navbar/NavBar";
import "./booktrain.css";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

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
  const [seatTypes, setSeatTypes] = useState([]);
  const [prompt, setPrompt] = useState({ state: "" });
  const [allStations, setAllStations] = useState(null);
  const [allStationsSource, setAllStationsSource] = useState(null);
  const [allStationsDestination, setAllStationsDestination] = useState(null);
  const [decision, setDecision] = useState({
    state: false,
    data: "",
    type: "normal",
  });
  const [datePrompt, setDatePrompt] = useState(false);
  const myRef1 = useRef();
  const myRef2 = useRef();

  useEffect(() => {
    if (checkAuth()) fetchStations();
  }, []);

  const state = auth.getState();
  if (state.userName === "") {
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
      `http://localhost:8080/api/availableTrainSeats?name=${trainName}&date=${date
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
    setSeatTypes(Object.keys(data));
  };

  const addBooking = async () => {
    const response = await fetch(`http://localhost:8080/api/addBooking`, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(booking),
    });
    if (response.status === 201) {
      setPrompt({ state: "success" });
    } else {
      setPrompt({ state: "failure" });
    }
    setAvailableTrains(null);
    setStation(initialData);
  };

  const fetchStations = async () => {
    const response = await fetch(`http://localhost:8080/api/getAllStations`, {
      method: "GET",
      headers: {
        "content-type": "application/json",
      },
    });
    const data = await response.json();
    setAllStations(Object.values(data));
  };

  const fetchTatkalSeats = async (trainName) => {
    const response = await fetch(
      `http://localhost:8080/api/getCancelledSeats?name=${trainName}&date=${date
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
    setSeatTypes(Object.keys(data));
  };

  const addTatkal = async () => {
    const response = await fetch(`http://localhost:8080/api/addTatkal`, {
      method: "PUT",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(booking),
    });
    if (response.status === 201) {
      setPrompt({ state: "success" });
    } else {
      setPrompt({ state: "failure" });
    }
    setAvailableTrains(null);
    setStation(initialData);
  };

  return (
    <div
      className="book-train-full-page"
      onClick={() => {
        setAllStationsSource(null);
        setAllStationsDestination(null);
      }}
    >
      {allStationsSource && (
        <div
          className="scroll-hide"
          style={{
            position: "absolute",
            left: myRef1.current.offsetLeft,
            top: myRef1.current.offsetTop + 35,
          }}
        >
          <div className="source-station-division">
            <table className="drop-down-station">
              {allStationsSource.map((each) => {
                return (
                  <tr>
                    <td
                      style={{ cursor: "pointer" }}
                      onClick={() => {
                        setStation({ ...station, source: each });
                        setBooking({ ...booking, source: each });
                      }}
                    >
                      {each}
                    </td>
                  </tr>
                );
              })}
            </table>
          </div>
        </div>
      )}
      {allStationsDestination && (
        <div
          className="scroll-hide"
          style={{
            position: "absolute",
            left: myRef2.current.offsetLeft,
            top: myRef2.current.offsetTop + 35,
          }}
        >
          <div className="source-station-division">
            <table className="drop-down-station">
              {allStationsDestination.map((each) => {
                return (
                  <tr>
                    <td
                      style={{ cursor: "pointer" }}
                      onClick={() => {
                        setStation({ ...station, destination: each });
                        setBooking({ ...booking, destination: each });
                      }}
                    >
                      {each}
                    </td>
                  </tr>
                );
              })}
            </table>
          </div>
        </div>
      )}
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
                setPrompt({ state: "" });
                setDatePrompt(false);
              }}
            />
          </div>
          <div>
            <label>Source Station : </label>
            <input
              ref={myRef1}
              type="text"
              name="source"
              value={station.source}
              placeholder="Enter your source station"
              onChange={(e) => {
                setStation({ ...station, [e.target.name]: e.target.value });
                setBooking({ ...booking, [e.target.name]: e.target.value });
                setAvailableTrains(null);
                setSeats(null);
                setPrompt({ state: "" });
                setAllStationsSource(
                  allStations.filter((each) => {
                    return each
                      .toLowerCase()
                      .startsWith(e.target.value.toLowerCase());
                  })
                );
              }}
              onClick={() => {
                setAllStationsSource(allStations);
                setAllStationsDestination(null);
              }}
            />
          </div>
          <div>
            <label>Destination Station : </label>
            <input
              ref={myRef2}
              type="text"
              name="destination"
              value={station.destination}
              placeholder="Enter your destination station"
              onChange={(e) => {
                setStation({ ...station, [e.target.name]: e.target.value });
                setBooking({ ...booking, [e.target.name]: e.target.value });
                setAvailableTrains(null);
                setSeats(null);
                setPrompt({ state: "" });
                setAllStationsDestination(
                  allStations.filter((each) => {
                    return each
                      .toLowerCase()
                      .startsWith(e.target.value.toLowerCase());
                  })
                );
              }}
              onClick={() => {
                setAllStationsDestination(allStations);
                setAllStationsSource(null);
              }}
            />
          </div>
          <div>
            <button
              type="submit"
              onClick={() => {
                setAllStationsDestination(null);
                setAllStationsSource(null);
                const current = new Date();
                if (
                  date.toISOString().slice(0, 10) <
                  current.toISOString().slice(0, 10)
                ) {
                  setDatePrompt(true);
                } else {
                  fetchAvailableTrains();
                }
                setBooking({
                  ...booking,
                  date: date.toISOString().slice(0, 10),
                  day: date.getDay(),
                });
                setDecision({ state: false, data: "", type: "normal" });
              }}
            >
              Search Trains
            </button>
          </div>
        </div>
        {datePrompt && (<div className="date-prompt">Train In The Past Dates Are Not Available... Choose Current or Later Dates</div>)}
        {prompt.state !== "" &&
          (prompt.state === "success" ? (
            <div className="prompt">Ticket booked successfully ...</div>
          ) : (
            <div className="prompt">Ticket booking not successful ...</div>
          ))}
        {bookingState ? (
          <div className="booking-popup">
            Confirm Adding Train Ticket ...
            <br />
            <br />
            <button
              type="button"
              onClick={() => {
                decision.type === "normal" ? addBooking() : addTatkal();
                setBookingState(false);
                setAvailableTrains(null);
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
                setAvailableTrains(null);
              }}
            >
              Cancel
            </button>
          </div>
        ) : (
          availableTrains != null &&
          (Object.keys(availableTrains).length !== 0 ? (
            <div className="book-train-sub-container">
              <div className="train-display-container child-cont">
                <table className="book-train-table">
                  <thead>
                    <tr>
                      <th>Train Name</th>
                      <th>Weekly Status</th>
                      <th>Availability</th>
                    </tr>
                  </thead>
                  <tbody>
                    {Object.keys(availableTrains).map((eachKey, index) => {
                      return (
                        <tr key={index}>
                          <td>{eachKey}</td>
                          <td>
                            <div className="days-viewer">
                              {availableTrains[eachKey].map((eachDay) => {
                                var day = "";
                                switch (eachDay) {
                                  case 0:
                                  case 6:
                                    day = "S";
                                    break;
                                  case 1:
                                    day = "M";
                                    break;
                                  case 2:
                                  case 4:
                                    day = "T";
                                    break;
                                  case 3:
                                    day = "W";
                                    break;
                                  case 5:
                                    day = "F";
                                    break;
                                  default:
                                    day = "x";
                                    break;
                                }
                                return <div>{day}</div>;
                              })}
                            </div>
                          </td>
                          <td>
                            <button
                              type="submit"
                              onClick={() => {
                                setDecision({
                                  state: true,
                                  data: eachKey,
                                  type: "normal",
                                });
                                setSeats(null);
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
              {decision.state && (
                <div className="decision-block">
                  <button
                    type="submit"
                    onClick={() => {
                      fetchSeats(decision.data);
                      setDecision({ state: false, data: "", type: "normal" });
                    }}
                  >
                    Normal
                  </button>
                  <button
                    type="submit"
                    onClick={() => {
                      fetchTatkalSeats(decision.data);
                      setDecision({ state: false, data: "", type: "tatkal" });
                    }}
                  >
                    Tatkal
                  </button>
                </div>
              )}
              {seats !== null && (
                <div className="availability-container">
                  <table className="availability-table">
                    <thead>
                      <tr>
                        <th>Coach</th>
                        <th>Available</th>
                      </tr>
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
                          <Dropdown
                            menuClassName="drop-down-menu"
                            className="drop-down"
                            name="coach"
                            options={seatTypes}
                            value={booking.coach}
                            placeholder="Select your coach"
                            onChange={(e) => {
                              setBooking({
                                ...booking,
                                coach: e.value,
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
                              setSeats(null);
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
          ) : (
            <div className="no-trains">No Trains Available</div>
          ))
        )}
      </div>
    </div>
  );
};

export default BookTrain;
