import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const users = ['Harry', 'Leo', 'Brandon']
const passwords = ['123', 'password', 'react']

function SignIn() {
  const navigate = useNavigate();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  function handleLogin() {
    if (users.includes(username) && password === passwords[users.indexOf(username)]) {
      alert('Logged in!');
      navigate("/TakeMood");
    } else {
      alert('Incorrect username or password');
    }
  }

  return (
    <div className="login">
      <h1 className='title'>Strava Ripoff</h1>
      <h3>Enter user details</h3>
      <label htmlFor='username'>Username:</label>
      <input type='text' id='username' placeholder='Username' onChange={(e) => setUsername(e.target.value)}/>
      <label htmlFor='password'>Password:</label>
      <input type='password' id='password' placeholder='Password' onChange={(e) => setPassword(e.target.value)}/>
      <button type='submit' onClick={handleLogin}>Sign in</button>
    </div>
  );
}
