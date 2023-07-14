import React, { useEffect, useState } from "react";
import { fetchMovieByLocation, fetchMovies } from "../../API-Services/MovieService";
import { useNavigate } from "react-router-dom";
import "./LandingPage.css"
import Locations from "./Locations";
import TheatreListingPage from "./TheatreListingPage";
import { auth } from "../../authentication";
import ProfileBar from "./ProfileBar";
import SeatSelection from "./SeatSelection";
import { addBooking, fetchCapacityBookings } from "../../API-Services/BookingsService";

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

    const addTicketBooking = async (current) => {
        const data = await addBooking(current)
        console.log(data);
        navigate("/bookings")
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
        setBookingData({...bookingData, seatNo: childData, userName: auth.getState().username})
        addTicketBooking({...bookingData, seatNo: childData, userName: auth.getState().username})
        setSeatPageState(false)
    }

    const CallBackSeatReturn = () => {
        setSeatPageState(false)
    }

    if(seatPageState) {
        return <SeatSelection rowCapacity={rowCapacity} seatCapacity={seatCapacity} bookedSeats={bookedSeats} callbackfunction={CallBackSeatsPage} callbackreturn={CallBackSeatReturn} />
    }

    const CallBackTheatreData = (childData) => {
        fetchCapacity(childData.theatre, childData.show, childData.date)
        setBookingData({...bookingData, theatre: childData.theatre, timing: childData.show, region: childData.location, date: childData.date, movieName: childData.movie})
    }

    if(locationPageState === true){
        return <Locations callbackfunction={CallBackLocation} type="land" />
    }

    if(movieName !== null && auth.getState().userName !== "") {
        return <TheatreListingPage movieName={movieName} callbackfunction={CallBackTheatrePage} callbackfunction2={CallBackTheatreData} />
    } else if(movieName !== null){
        navigate("/login-signup")
    }

    return (
        <>
            {profilePageState && <ProfileBar callbackfunction={CallBackProfile}/>}
            <div className="navbar">
                <div>
                    <button type="button" onClick={() => {fetchAllMovies(); setSearchTerm("")}}>Dashboard</button>
                </div>
                <div>
                    <input type="text" name="search" value={searchTerm} onChange={(e) => {setSearchTerm(e.target.value); filterMovies(e.target.value)}} placeholder=" Enter movie to search" />
                </div>
                <div>
                <button type="submit" onClick={() => {setLocationPageState(true)}}>{(bookingData.region !== "") ? bookingData.region : "Location"}</button>
                </div>
                <div>
                    {auth.getState().userName === "" ? 
                    <button type="button" onClick={() => {navigate("/login-signup")}}>Login</button>
                    :
                    <button type="button" onClick={() => {setProfilePageState(!profilePageState)}}>Profile</button>
                    }
                </div>
            </div>
            <div className="movies-container">
                {currentMovies != null && 
                    currentMovies.map((eachMovie, index) => {
                        return (
                            <div key={index} className="each-movie">
                                <img src={eachMovie.imageurl} alt={eachMovie.name} onPointerOver={() => {console.log(eachMovie.name)}}/>
                                <p>Movie Name : {eachMovie.name}</p>
                                <p>Language : {eachMovie.language}</p>
                                <p>Release Date : {eachMovie.releaseDate}</p>
                                <p>Duration{eachMovie.duration}</p>
                                <p>Genre : {eachMovie.genre}</p>
                                <p>Description : {eachMovie.description}</p>
                                <p>Casting : {eachMovie.casting}</p>
                                <button type="submit" onClick={() => {setMovieName(eachMovie.name)}}>Book Ticket</button>
                            </div>
                        )
                    })}
            </div>
        </>
    );
}

export default LandingPage;