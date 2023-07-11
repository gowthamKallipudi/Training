import React, { useEffect, useState } from "react";
import { fetchMovieByLocation, fetchMovies } from "../../API-Services/MovieService";
import { useNavigate } from "react-router-dom";
import "./LandingPage.css"
import Locations from "./Locations";
import TheatreListingPage from "./TheatreListingPage";
import { auth } from "../../authentication";
import ProfileBar from "./ProfileBar";

const LandingPage = () => {
    const [currentMovies, setCurrentMovies] = useState(null);
    const [movieName, setMovieName] = useState(null);
    const [profilePageState, setProfilePageState] = useState(false);
    const navigate = useNavigate();

    const [pageState, setPageState] = useState({
        main: true,
        location: false
    })

    useEffect(() => {
        fetchAllMovies()
    }, [])

    const fetchAllMovies = async () => {
        const data = await fetchMovies()
        setCurrentMovies(data)
        console.log(data);
    }

    const fetchMoviesByLocation = async (loc) => {
        const data = await fetchMovieByLocation(loc)
        setCurrentMovies(data)
        console.log(data)
    }

    const CallBack = (childData) => {
        setPageState({main:true, location:false})
        console.log(childData);
        if(childData === "") {
            fetchAllMovies()
        }else {
            fetchMoviesByLocation(childData)
        }
    }

    if(pageState.location === true){
        return <Locations callbackfunction={CallBack} />
    }

    if(movieName !== null) {
        return <TheatreListingPage movieName={movieName} />
    }

    if(profilePageState) {
        return <ProfileBar />
    }

    return (
        <>
            <div>
                <button type="button">dashboard</button>
                <input type="text" name="search" />
                <button type="submit" onClick={() => {setPageState({main: false, location: true})}}>Location</button>
                {auth.getState().userName === "" ? 
                <button type="button" onClick={() => {navigate("/login-signup")}}>login</button>
                :
                <button type="button" onClick={() => {setProfilePageState(!profilePageState)}}>Profile</button>
                }
            </div>
            <div>
                {currentMovies != null && 
                    currentMovies.map((eachMovie, index) => {
                        return (
                            <div key={index} className="each-movie">
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