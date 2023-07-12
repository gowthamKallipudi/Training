import React, { useEffect, useState } from "react";
import { fetchMovieByLocation, fetchMovies } from "../../API-Services/MovieService";
import { useNavigate } from "react-router-dom";
import "./LandingPage.css"
import Locations from "./Locations";
import TheatreListingPage from "./TheatreListingPage";
import { auth } from "../../authentication";
import ProfileBar from "./ProfileBar";
import SeatSelection from "./SeatSelection";
import { fetchCapacityBookings } from "../../API-Services/BookingsService";

const defaultBookingData = {
    "userName": "",
    "theatre": "",
    "timing": "",
    "region": "",
    "movieName": "",
    "date": "",
    "seatNo": []
}

const LandingPage = () => {
    const [fetchedMovies, setFetchedMovies] = useState(null)
    const [currentMovies, setCurrentMovies] = useState(null);
    const [movieName, setMovieName] = useState(null);
    const [profilePageState, setProfilePageState] = useState(false);
    const [locationPageState, setLocationPageState] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");
    const [bookingData, setBookingData] = useState(defaultBookingData);
    const [seatPageState, setSeatPageState] = useState(false)
    const [seatCapacity, setSeatCapacity] = useState(null)
    const [rowCapacity, setRowCapacity] = useState(null)
    const [bookedSeats, setBookedSeats] = useState(null)
    const [currentBooking, setCurrentBooking] = useState([])
    const navigate = useNavigate();

    useEffect(() => {
        fetchAllMovies()
    }, [])

    console.log(bookingData)

    const fetchAllMovies = async () => {
        const data = await fetchMovies()
        setCurrentMovies(data)
        setFetchedMovies(data)
        console.log(data);
    }

    const fetchMoviesByLocation = async (loc) => {
        const data = await fetchMovieByLocation(loc)
        setCurrentMovies(data)
        setFetchedMovies(data)
        console.log(data)
    }

    const fetchCapacity = async (theatre, show, date) => {
        const data = await fetchCapacityBookings(theatre, show, date);
        setSeatCapacity(data.seatCapacity)
        setRowCapacity(data.rowCapacity)
        setBookedSeats(data.bookedSeats)
        setSeatPageState(true);
        setMovieName(null);
    }

    const filterMovies = (term) => {
        setCurrentMovies(fetchedMovies.filter((eachMovie) => {
            return eachMovie.name.includes(term.toLowerCase())
        }))
    }

    const CallBackLocation = (childData) => {
        setLocationPageState(false)
        setBookingData({...bookingData, region: childData})
        console.log(childData);
        if(childData === "") {
            fetchAllMovies()
        }else {
            fetchMoviesByLocation(childData)
        }
    }

    const CallBackProfile = () => {
        setProfilePageState(false)
    }

    const CallBackTheatrePage = () => {
        setMovieName(null)
    }

    const CallBackSeatsPage = (childData) => {
        setCurrentBooking(childData)
        setBookingData({...bookingData, seatNo: childData})
        setSeatPageState(false)
    }

    if(seatPageState) {
        return <SeatSelection rowCapacity={rowCapacity} seatCapacity={seatCapacity} bookedSeats={bookedSeats} callbackfunction={CallBackSeatsPage} />
    }

    const CallBackTheatreData = (childData) => {
        fetchCapacity(childData.theatre, childData.show, childData.date)
        setBookingData({...bookingData, theatre: childData.theatre, timing: childData.show, region: childData.location, date: childData.date, movieName: childData.movie})
    }

    if(locationPageState === true){
        return <Locations callbackfunction={CallBackLocation} />
    }

    if(movieName !== null) {
        return <TheatreListingPage movieName={movieName} callbackfunction={CallBackTheatrePage} callbackfunction2={CallBackTheatreData} />
    }

    if(profilePageState) {
        return <ProfileBar callbackfunction={CallBackProfile}/>
    }

    return (
        <>
            <div>
                <button type="button" onClick={() => {fetchAllMovies(); setSearchTerm("")}}>dashboard</button>
                <input type="text" name="search" value={searchTerm} onChange={(e) => {setSearchTerm(e.target.value); filterMovies(e.target.value)}} />
                <button type="submit" onClick={() => {setLocationPageState(true)}}>Location</button>
                {auth.getState().userName === "" ? 
                <button type="button" onClick={() => {navigate("/login-signup")}}>login</button>
                :
                <button type="button" onClick={() => {setProfilePageState(!profilePageState)}}>Profile</button>
                }
            </div>
            <div className="movies-container">
                {currentMovies != null && 
                    currentMovies.map((eachMovie, index) => {
                        return (
                            <div key={index} className="each-movie">
                                <img src={eachMovie.imageurl} alt={eachMovie.name} onPointerOver={() => {console.log(eachMovie.name)}}/>
                                <p>{eachMovie.name}</p>
                                <p>{eachMovie.language}</p>
                                <p>{eachMovie.releaseDate}</p>
                                <p>{eachMovie.duration}</p>
                                <p>{eachMovie.genre}</p>
                                <p>{eachMovie.description}</p>
                                <p>{eachMovie.casting}</p>
                                <button type="submit" onClick={() => {setMovieName(eachMovie.name)}}>Book Ticket</button>
                            </div>
                        )
                    })}
            </div>
        </>
    );
}

export default LandingPage;