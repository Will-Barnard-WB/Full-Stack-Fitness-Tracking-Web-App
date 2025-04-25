import { useNavigate } from "react-router-dom";
import './WebApp.css';
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserProfile = () => {
    const [user, setUser] = useState(null);
    const userId = localStorage.getItem('userID');

    useEffect(() => {
        axios.get(`http://172.26.42.147:8080/users/${userId}/data`)
            .then(response => setUser(response.data))
            .catch(error => console.error('Error fetching user:', error));
    }, [userId]);

    if (!user) return <p>Loading user...</p>;

    return (
        <div>
            <h2>User Profile: {user.name}</h2>
            <p><strong>ID:</strong> {user.id}</p>
            <h3>Workouts:</h3>
            <ul>
                {user.workouts && user.workouts.map((workout, idx) => (
                    <li key={idx}>{JSON.stringify(workout)}</li>
                ))}
            </ul>
            <h3>Workout Stats:</h3>
            <ul>
                {user.workoutStats && user.workoutStats.map((stat, idx) => (
                    <li key={idx}>{JSON.stringify(stat)}</li>
                ))}
            </ul>
            <h3>Moods:</h3>
            <ul>
                {user.moods && user.moods.map((mood, idx) => (
                    <li key={idx}>{JSON.stringify(mood)}</li>
                ))}
            </ul>
            <h3>User Statistics:</h3>
            <pre>{JSON.stringify(user.userStatistics, null, 2)}</pre>
        </div>
    );
};

export default function MainWindow() {
    const navigate = useNavigate();

    return (
        <div className="mainWindow">
            <h1 className="mainTitle">Main Menu</h1>
            <div className="menuButtons">
                <button onClick={() => navigate("/Layout/AchievementPage")}>Achievements</button>
                <button onClick={() => navigate("/Layout/GoalPage")}>Goals</button>
                <button onClick={() => navigate("/Layout/GraphsPage")}>Graphs</button>
                <button onClick={() => navigate("/Layout/MoodPage")}>Mood Tracker</button>
                <button onClick={() => navigate("/Layout/Settings")}>Settings</button>
                <UserProfile userId="1"/>
            </div>
        </div>
    );
}
