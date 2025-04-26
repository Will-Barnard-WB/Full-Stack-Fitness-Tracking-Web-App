import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import './WebApp.css';

const emojis = [
    "😭", "😰", "😢", "🥲", "😐", "🙂", "😄",
    "😁", "😆", "🤩"
];

const TakeMood = () => {
    const [value, setValue] = useState(5);
    const [values, setValues] = useState([]);
    const [dates, setDates] = useState([]);
    const [status, setStatus] = useState(""); // 👈 status message
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://172.26.42.147:8080/users/1/moods") // Replace "1" with dynamic user ID if needed
            .then(res => res.json())
            .then(data => {
                const moodValues = data.map(mood => mood.mood);
                const moodDates = data.map(mood => new Date(mood.date).toLocaleDateString()); // assuming timestamp field
                setValues(JSON.parse(moodValues));
                setDates(JSON.parse(moodDates));
            })
            .catch(err => {
                console.error("Failed to fetch mood history:", err);
            });
    }, []);


    const handleChange = (e) => {
        setValue(Number(e.target.value));
    };

    const handleRelease = () => {
        const currentDate = new Date().toLocaleDateString();
        const updatedValues = [...values, value];
        const updatedDates = [...dates, currentDate];

        setValues(updatedValues);
        setDates(updatedDates);
        localStorage.setItem("sliderValues", JSON.stringify(updatedValues));
        localStorage.setItem("sliderDates", JSON.stringify(updatedDates));

        const userId = localStorage.getItem("userID");

        fetch(`http://172.26.42.147:8080/users/${userId}/moods`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(value)
        })
            .then(res => {
                if (!res.ok) throw new Error("Failed to submit mood");
                return res.text();
            })
            .then(msg => {
                console.log("Backend response:", msg);
                setStatus("✅ Mood saved successfully!");
                setTimeout(() => {
                    navigate("/Layout");
                }, 0); // Delay navigation to show success
            })
            .catch(err => {
                console.error("Error:", err);
                setStatus("❌ Failed to save mood.");
            });
    };

    return (
        <div className="moodPage">
            <h1 className="moodTitle">Pick a Mood</h1>

            <input
                className="moodSlider"
                type="range"
                min="1"
                max="10"
                value={value}
                onChange={handleChange}
                onMouseUp={handleRelease}
                onTouchEnd={handleRelease}
            />

            <div className="moodSelected">
                Selected: {value} {emojis[value - 1]}
            </div>

            {status && (
                <div className="moodStatus" style={{ marginTop: "1rem", fontWeight: "bold" }}>
                    {status}
                </div>
            )}

            <div className="moodHistory">
                <h3 className="historyTitle">Mood history (stored in localStorage):</h3>
                <ul className="historyList">
                    {values.length === 0 ? (
                        <li>No data yet</li>
                    ) : (
                        values.map((val, index) => (
                            <li key={index} className="historyItem">
                                #{index + 1}: {val} {emojis[val - 1]} — {dates[index]}
                            </li>
                        ))
                    )}
                </ul>
            </div>
        </div>
    );
};

export default TakeMood;
