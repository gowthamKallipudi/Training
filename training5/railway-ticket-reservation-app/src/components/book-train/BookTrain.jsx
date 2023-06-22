import React, { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar";
import { auth, checkAuth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";
import "./booktrain.css";

const BookTrain = () => {
  const [trainData, setTrainData] = useState(null);
  const [profileData, setProfileData] = useState(null);
  const [bookingState, setBookingState] = useState({ state: false, data: {} });

  const fetchTrains = async () => {
    const response = await fetch("http://localhost:8080/api/getAllTrains", {
      method: "GET",
      headers: {
        "content-type": "application/json",
      },
    });
    const data = await response.json();
    setTrainData(data);
  };

  const fetchProfile = async () => {
    const response = await fetch("http://localhost:8080/api/getUser/gowtham", {
      method: "GET",
      headers: {
        "content-type": "application/json",
      },
    });
    const data = await response.json();
    setProfileData(data);
  };

  useEffect(() => {
    if (checkAuth) {
      fetchTrains();
      fetchProfile();
    }
  }, []);

  const state = auth.getState();
  if (state.userName === "") {
    return <Navigate to="/login" />;
  }


  const sendData = async (currentTrain) => {
    const response = await fetch("http://localhost:8080/api/addBooking", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify({
        userName: profileData.userName,
        emailId: profileData.emailId,
        phoneNumber: profileData.phoneNumber,
        trainNumber: currentTrain.trainNumber,
        trainName: currentTrain.trainNumber,
        source: currentTrain.source,
        destination: currentTrain.destination,
      }),
    });
    console.log(response);
  };

  return (
    <>
      <NavBar />
      {trainData == null ? (
        <div>No Data Found</div>
      ) : (
        <div className="book-train">
          {bookingState.state ? (
            <div className="booking-popup">
              Confirm Adding Train Ticket ...
              <br />
              <br />
              <button
                type="button"
                onClick={() => {
                  sendData(bookingState.data);
                  setBookingState({ state: false, data: {} });
                }}
              >
                Confirm Booking
              </button>
              <br />
              <br />
              <button
                type="button"
                onClick={() => {
                  setBookingState({ state: false, data: {} });
                }}
              >
                Cancel
              </button>
            </div>
          ) : (
            <div className="book-train-main">
              <table className="book-train-table">
                <thead>
                  <th>SNO.</th>
                  <th>Train Number</th>
                  <th>Train Name</th>
                  <th>Source Station</th>
                  <th>Destination Station</th>
                  <th>Book</th>
                </thead>
              </table>
              {trainData.map((eachTrain, index) => {
                return (
                  <table key={eachTrain.id} className="book-train-table">
                    <tr>
                      <td>{index + 1}</td>
                      <td>{eachTrain.trainNumber}</td>
                      <td>{eachTrain.trainName}</td>
                      <td>{eachTrain.source}</td>
                      <td>{eachTrain.destination}</td>
                      <td>
                        <button
                          type="submit"
                          onClick={() => {
                            setBookingState({ state: true, data: eachTrain });
                          }}
                        >
                          Book Train
                        </button>
                      </td>
                    </tr>
                  </table>
                );
              })}
            </div>
          )}
        </div>
      )}
    </>
  );
};

export default BookTrain;
