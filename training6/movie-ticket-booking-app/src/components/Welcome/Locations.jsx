import React, { useEffect, useState } from "react";
import { fetchLocations } from "../../API-Services/LocationService";
import "./Location.css";

const Locations = ({callbackfunction, type = ""}) => {
    const [allLocations, setAllLocations] = useState(null)

    useEffect(() => {
        fetchRegions()
    }, [])

    const fetchRegions = async () => {
        const data = await fetchLocations();
        setAllLocations(data)
        console.log(data)
    }

    return(
        <div className="location-container">
            {type === "land" && <div className="each-location" onClick={() => {callbackfunction("")}}>All Locations</div>}
            {allLocations !== null && allLocations.map((eachLocation, index) => {
                return (
                    <div className="each-location" key={index} onClick={() => {callbackfunction(eachLocation)}}>{eachLocation}</div>
                )
            })}
        </div>
    )

}

export default Locations;