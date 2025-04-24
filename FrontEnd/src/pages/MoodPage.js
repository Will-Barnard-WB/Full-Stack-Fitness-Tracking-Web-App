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

    const clearHistory = () => {
        localStorage.removeItem("sliderValues");
        localStorage.removeItem("sliderDates");

        window.location.reload();
    };

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
        <div>
            <h1>Mood Tracker (Recharts)</h1>
            {data.length === 0 ? (
                <p>No data yet</p>
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
            <button onClick={clearHistory}>Clear History</button>

            <button className='back' onClick={() => navigate('/Layout/MainWindow')}>Back</button>


        </div>
    );
};

export default MoodPage;
