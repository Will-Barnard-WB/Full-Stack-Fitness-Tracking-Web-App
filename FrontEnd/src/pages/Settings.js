import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";

const Settings = () => {
    const navigate = useNavigate();
    const [oldPass, setOldPass] = useState('');
    const [newPass, setNewPass] = useState('');
    const [message, setMessage] = useState('');

    const username = localStorage.getItem('loggedInUser');
    const users = JSON.parse(localStorage.getItem('users'));
    const passwords = JSON.parse(localStorage.getItem('passwords'));

    function handleChangePassword() {
        const userIndex = users.indexOf(username);

        if (userIndex === -1) {
            setMessage('User not found.');
            return;
        }

        if (passwords[userIndex] !== oldPass) {
            setMessage('Old password is incorrect.');
            return;
        }

        // Update password
        passwords[userIndex] = newPass;
        localStorage.setItem('passwords', JSON.stringify(passwords));
        setMessage('Password changed successfully!');
    }

    function handleLogout() {
        localStorage.removeItem('loggedInUser');
        navigate("/");
    }

    return (
        <div>
            <h1>Settings</h1>
            <h3>Change Password</h3>
            <input type="password"
                placeholder="Old Password"
                value={oldPass}
                onChange={(e) => setOldPass(e.target.value)}/>
            <input type="password"
                placeholder="New Password"
                value={newPass}
                onChange={(e) => setNewPass(e.target.value)}/>
            <button onClick={handleChangePassword}>Change Password</button>
            <p>{message}</p>

            <br />
            <button onClick={handleLogout}>
                Log Out
            </button>
        </div>
    );
};

export default Settings;
