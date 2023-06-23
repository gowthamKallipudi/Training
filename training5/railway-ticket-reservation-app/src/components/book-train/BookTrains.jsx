import React, { useEffect, useState } from "react";

const initialData = {
  source: "",
  destination: "",
};

const BookTrains = () => {
  const [availableTrains, setAvailableTrains] = useState(null);
  const [station, setStation] = useState(initialData);

  const fetchAvailableTrains = async () => {
    const response = await fetch(
      `http://localhost:8080/api/getTrainBetween?source=${station.source}&destination=${station.destination}`,
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

  return (
    <>
      <input
        type="text"
        name="source"
        value={station.source}
        placeholder="Enter your source station"
        onChange={(e) => {
          setStation({ ...station, [e.target.name]: e.target.value });
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
        }}
      />
      <button
        type="submit"
        onClick={() => {
          fetchAvailableTrains();
        }}
      >
        Submit
      </button>
      {/* {availableTrains == null ? (
        <div>No Data Found</div>
      ) : (
        <div>
          {availableTrains.map((each) => {
            return <div>{each}</div>;
          })}
        </div>
      )} */}
    </>
  );
};

export default BookTrains;
