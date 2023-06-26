import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { auth } from "../../utilities/authentication";
import { Navigate } from "react-router";
import NavBar from "../navbar/NavBar";

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
  const [openState, setOpenState] = useState(false);

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
    setAvailableTrains(data);
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
        // body: JSON.stringify({...param, name: trainName}),
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
      <DatePicker
        selected={date}
        name="date"
        onChange={(currentDate) => {
          setDate(currentDate);
        }}
      />
      <br />
      <input
        type="text"
        name="source"
        value={station.source}
        placeholder="Enter your source station"
        onChange={(e) => {
          setStation({ ...station, [e.target.name]: e.target.value });
          setBooking({ ...booking, [e.target.name]: e.target.value });
        }}
      />
      <br />
      <input
        type="text"
        name="destination"
        value={station.destination}
        placeholder="Enter your destination station"
        onChange={(e) => {
          setStation({ ...station, [e.target.name]: e.target.value });
          setBooking({ ...booking, [e.target.name]: e.target.value });
        }}
      />
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
        Submit
      </button>
      <div>
        {availableTrains == null ? (
          <div>No Data Found</div>
        ) : (
          <div>
            {Object.keys(availableTrains).map((eachKey, index) => {
              return (
                <div key={index}>
                  {eachKey}
                  {availableTrains[eachKey].map((eachDay) => {
                    return <div>{eachDay}</div>;
                  })}
                  <button
                    type="submit"
                    onClick={() => {
                      fetchSeats(eachKey);
                    }}
                  >
                    Check Availability
                  </button>
                  <button
                    type="submit"
                    onClick={() => {
                      const user = auth.getState();
                      setBooking({
                        ...booking,
                        trainName: eachKey,
                        userId: user.id,
                      });
                      setOpenState(true);
                    }}
                  >
                    Book Train
                  </button>
                </div>
              );
            })}
          </div>
        )}
      </div>
      <br />
      {seats !== null && (
        <div>
          {Object.keys(seats).map((each) => {
            return (
              <div>
                {each} : {seats[each]}
              </div>
            );
          })}
        </div>
      )}
      {openState && <>
      <div>
        <input
          type="text"
          name="coach"
          value={booking.coach}
          placeholder="Enter your Coach"
          onChange={(e) => {
            setBooking({ ...booking, [e.target.name]: e.target.value });
          }}
        />
      </div>
      <button
        type="submit"
        onClick={() => {
          addBooking();
        }}
      >
        Add Ticket
      </button>
      </>
      }
    </>
  );
};

export default BookTrain;
