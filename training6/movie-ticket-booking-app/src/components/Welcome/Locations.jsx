import React, { useEffect, useState } from "react";
import { fetchLocations } from "../../API-Services/LocationService";

const Locations = ({callbackfunction}) => {
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
        <>
            <p onClick={() => {callbackfunction("")}}>All Locations</p>
            {allLocations !== null && allLocations.map((eachLocation, index) => {
                return (
                    <p key={index} onClick={() => {callbackfunction(eachLocation)}}>{eachLocation}</p>
                )
            })}
        </>
    )

}

export default Locations;