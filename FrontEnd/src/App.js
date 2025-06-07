import React, { useEffect, useState } from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./pages/Layout";
import SignIn from "./pages/SignIn";
import TakeMood from "./pages/TakeMood";
import MainWindow from "./pages/MainWindow";
import AchievementPage from "./pages/AchievementPage";
import GoalPage from "./pages/GoalPage";
import GraphsPage from "./pages/GraphsPage";
import MoodPage from "./pages/MoodPage";
import Settings from "./pages/Settings";
import SignUp from "./pages/SignUp";
import BackendCall from "./pages/BackendCall";
import Leaderboard from "./pages/Leaderboard";

// ✅ Temporary Test Component
function BackendTest() {
    const [message, setMessage] = useState("");

    useEffect(() => {
        fetch("https://full-stack-fitness-web-app.onrender.com/users/hello")
            .then((res) => res.text())
            .then((data) => setMessage(data))
            .catch((err) => console.error("Backend call failed:", err));
    }, []);

    return (
        <div style={{ padding: "2rem" }}>
            <h2>Backend Test</h2>
            <p>Response from backend:</p>
            <pre>{message}</pre>
        </div>
    );
}

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<SignIn />} />
                <Route path="/SignUp" element={<SignUp />} />
                <Route path="/TakeMood" element={<TakeMood />} />
                <Route path="/backend-test" element={<BackendTest />} />
                <Route path="/BackendCall" element={<BackendCall />} />
                <Route path="/layout" element={<Layout />}>
                    <Route index element={<MainWindow />} />
                    <Route path="MainWindow" element={<MainWindow />} />
                    <Route path="AchievementPage" element={<AchievementPage />} />
                    <Route path="GoalPage" element={<GoalPage />} />
                    <Route path="GraphsPage" element={<GraphsPage />} />
                    <Route path="MoodPage" element={<MoodPage />} />
                    <Route path="Leaderboard" element={<Leaderboard />} />
                    <Route path="Settings" element={<Settings />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
