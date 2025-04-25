import React from 'react';
import { useNavigate } from "react-router-dom";
import './WebApp.css';

const AchievementPage = () => {
    const navigate = useNavigate();

    const data = {
        distance: 73.6,
        altitude: 251.2,
        streak: 5
    };

    const achievements = [
        { title: "Run 100km", target: 100, unit: "km", type: "distance" },
        { title: "Run 250km", target: 250, unit: "km", type: "distance"},
        { title: "Climb 1000m", target: 1000, unit: "m", type: "altitude"},
        { title: "7-Day Login Streak", target: 7, unit: "days", type: "streak" },
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
            <h1>Achievements</h1>
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
