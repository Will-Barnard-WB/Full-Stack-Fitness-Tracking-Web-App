import { useNavigate } from "react-router-dom";
import './WebApp.css';

import { useEffect, useState } from 'react';

function UsersList() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/users')
            .then(response => response.json())
            .then(data => {
                setUsers(data); // array of user objects
            })
            .catch(error => console.error('Error fetching users:', error));
    }, []);

    return (
        <div>
            <h1>User List</h1>
            <ul>
                {users.map((user, index) => (
                    <li key={index}>
                        <strong>{user.username}</strong> - {user.email} - Age: {user.age}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default UsersList;


export default function MainWindow() {
    const navigate = useNavigate();

    return (
        <div className="mainWindow">
            <h1 className="mainTitle">Main Menu</h1>
            <div className="menuButtons">
                <button onClick={() => navigate("/Layout/AchivementPage")}>Achievements</button>
                <button onClick={() => navigate("/Layout/GoalPage")}>Goals</button>
                <button onClick={() => navigate("/Layout/GraphsPage")}>Graphs</button>
                <button onClick={() => navigate("/Layout/MoodPage")}>Mood Tracker</button>
                <button onClick={() => navigate("/Layout/Settings")}>Settings</button>
            </div>
        </div>
    );
}
