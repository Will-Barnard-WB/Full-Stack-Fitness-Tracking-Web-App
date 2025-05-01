import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import './WebApp.css';

const AchievementPage = () => {
    const navigate = useNavigate();

    function calculateLoginStreak() {
        const stored = localStorage.getItem('loginDates');
        if (!stored) return 0;

        const datesArray = JSON.parse(stored); // Convert string to array
        const dateSet = new Set(datesArray);   // Now it's correct

        const today = new Date();
        today.setHours(0, 0, 0, 0);

        const formatDate = (date) => date.toLocaleDateString('en-GB');
        const todayStr = formatDate(today);

        if (!dateSet.has(todayStr)) return 0;

        let streak = 0;
        let current = new Date(today);

        while (true) {
            const dateStr = formatDate(current);
            if (dateSet.has(dateStr)) {
                streak++;
                current.setDate(current.getDate() - 1);
            } else {
                break;
            }
        }
        return streak;
    }

    const [stats, setStats] = useState({
        totalDistance: null,
        totalAltitude: null,
    });

    useEffect(() => {
        const fetchStats = async () => {
            try {
                const userId = localStorage.getItem('userID');
                const response = await fetch(`http://172.26.42.147:8080/users/${userId}/data`);
                const data = await response.json();

                const totalDistance = data.userStatistics?.totalDistance ?? 0;
                const workouts = Array.isArray(data.workouts) ? data.workouts : [];

                let overallAltitudeGain = 0;

                workouts.forEach((workout) => {
                    const validSplits = (workout.splits || []).filter(split => {
                        const altitude = parseFloat(split?.altitude);
                        return !isNaN(altitude);
                    });

                    if (validSplits.length > 0) {
                        const altitudes = validSplits.map(split => parseFloat(split.altitude));
                        const minAltitude = Math.min(...altitudes);
                        const maxAltitude = Math.max(...altitudes);
                        overallAltitudeGain += (maxAltitude - minAltitude);
                    }
                });

                setStats({
                    totalDistance,
                    totalAltitude: overallAltitudeGain,
                });

            } catch (error) {
                console.error('Error fetching stats:', error);
            }
        };
        fetchStats();
    }, []);

    const data = {
        dist: stats.totalDistance !== null ? (stats.totalDistance / 1000).toFixed(1) : null,
        alt: stats.totalAltitude !== null ? stats.totalAltitude.toFixed(1) : null,
        streak: calculateLoginStreak()
    };

    const achievements = [
        { title: "Run 250km", target: 250, unit: "km", type: "dist"},
        { title: "Run 500km", target: 500, unit: "km", type: "dist"},
        { title: "Climb 1000m", target: 1000, unit: "m", type: "alt"},
        { title: "7 Day Login Streak", target: 7, unit: "days", type: "streak" },
    ];
    
    const mergedAchievements= achievements.map((achievement) => {
        const current = data[achievement.type] || 0;
        const progress = Math.min((current / achievement.target) * 100, 100);
        return {
            ...achievement,
            current,
            progress
        }
    });

    return (
        <div className="achievement-page">
            <h1>Achievement Page</h1>
            {mergedAchievements.map((achievement, index) => {
                return (
                    <div key={index} className="achievement">
                        <h2>{achievement.title}</h2>
                        <p>{achievement.current}/{achievement.target} {achievement.unit}</p>
                        <div className="progress-container">
                            <div className="progress-bar"
                                 style={{width:`${achievement.progress}%`}}>
                            </div>
                        </div>
                        <p>{achievement.progress.toFixed(1)}%</p>
                    </div>
                )
            })}
            <button className='back' onClick={() => navigate('/Layout/MainWindow')}>Back</button>
        </div>
    )
}

export default AchievementPage;
