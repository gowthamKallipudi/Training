import React, { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar";
import { auth } from "../../utilities/authentication";
import { Navigate } from "react-router-dom";

const BookTrain = () => {
  const [trainData, setTrainData] = useState(null);
  const [profileData, setProfileData] = useState(null);

  useEffect(() => {
    fetchTrains();
    fetchProfile();
  }, []);

  const state = auth.getState();
  if (state.userName === "") {
    return <Navigate to="/login" />;
  }

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
      <div>In Book Train Page</div>
      {trainData == null ? (
        <div>No Data Found</div>
      ) : (
        trainData.map((eachTrain) => {
          return (
            <div key={eachTrain.id}>
              <label>Train Number : </label>
              {eachTrain.trainNumber}
              <br />
              <label>Train Name : </label>
              {eachTrain.trainName}
              <br />
              <label>Train Source : </label>
              {eachTrain.source}
              <br />
              <label>Train Destination : </label>
              {eachTrain.destination}
              <br />
              <button
                type="submit"
                onClick={() => {
                  sendData(eachTrain);
                }}
              >
                Book Train
              </button>
            </div>
          );
        })
      )}
    </>
  );
};

export default BookTrain;
