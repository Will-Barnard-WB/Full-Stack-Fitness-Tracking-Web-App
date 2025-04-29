import React, { useEffect, useState } from 'react';

import { useNavigate } from 'react-router-dom';
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer,
} from 'recharts';

const MoodPage = () => {

    const navigate = useNavigate();

    const [data, setData] = useState([]);


    useEffect(() => {
        const userId = localStorage.getItem("userID");
        fetch(`http://172.26.42.147:8080/users/${userId}/moods`)
            .then(res => res.json())
            .then(data => {
                const moodValues = data.map(mood => mood.mood);
                const moodDates = data.map(mood => new Date(mood.date).toLocaleDateString());
                const combined = moodDates.map((date, index) => ({
                    date,
                    mood: moodValues[index],
                }));

                setData(combined);
            })
            .catch(err => {
                console.error("Failed to fetch mood history:", err);
            });
    }, []);

    useEffect(() => {
        const storedValues = JSON.parse(localStorage.getItem("sliderValues")) || [];
        const storedDates = JSON.parse(localStorage.getItem("sliderDates")) || [];

        const combined = storedDates.map((date, index) => ({
            date,
            mood: storedValues[index],
        }));

        setData(combined);
    }, []);

return (
  <div className="moodChartPage">
    <h1 className="moodChartTitle">Mood Tracker</h1>
    {data.length === 0 ? (
      <p>Loading data...</p>
    ) : (
      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={data} margin={{ top: 20, right: 30, left: 20, bottom: 5 }}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="date" />
          <YAxis domain={[0, 10]} />
          <Tooltip />
          <Legend />
          <Line
            type="monotone"
            dataKey="mood"
            stroke="#4F46E5"
            fill="#4F46E5"
            strokeWidth={2}
          />
        </LineChart>
      </ResponsiveContainer>
    )}
    <button className="back" onClick={() => navigate('/Layout/MainWindow')}>Back</button>
  </div>
);


export default MoodPage;
