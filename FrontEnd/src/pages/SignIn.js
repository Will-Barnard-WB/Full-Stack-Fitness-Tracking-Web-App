import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './WebApp.css';

function SignIn() {
    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    useEffect(() => {
        if (!localStorage.getItem('users')) {
            localStorage.setItem('users', JSON.stringify(['Aaron', 'Isaac', 'Will', 'Dylan']));
            localStorage.setItem('passwords', JSON.stringify(['password123', 'donthackme', 'PASSWORD!', '123456789']));
            localStorage.setItem('id', JSON.stringify(['1', '2', '3', '4']));
        }
    } );

    function handleLogin() {
        const users = JSON.parse(localStorage.getItem('users'));
        const passwords = JSON.parse(localStorage.getItem('passwords'));
        const id = JSON.parse(localStorage.getItem('id'));

        const userIndex = users.indexOf(username);
        const userId = id[userIndex];

        if (userIndex !== -1 && password === passwords[userIndex]) {
            localStorage.setItem('loggedInUser', username);
            localStorage.setItem('userID', userId);
            navigate("/TakeMood");
        } else {
            alert('Incorrect username or password');
        }
    }

    return (
        <div className="login">
            <h1 className='title'>Strava Ripoff</h1>
            <h3>Enter user details</h3>
            <a href="/backend-test" style={{ display: 'block', marginTop: '1rem', color: 'blue' }}>
                🔧 Test Backend Connection
            </a>

            <label htmlFor='username'>Username:</label>
            <input type='text' id='username' placeholder='Username' onChange={(e) => setUsername(e.target.value)} />
            <label htmlFor='password'>Password:</label>
            <input type='password' id='password' placeholder='Password' onChange={(e) => setPassword(e.target.value)} />
            <button type='submit' onClick={handleLogin}>Sign in</button>
        </div>
    );
}

export default SignIn;
