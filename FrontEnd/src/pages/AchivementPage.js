import React from 'react';
import './ProgressBar.css'; 

const AchievementPage = () => {

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
    //Can add more achievements later on in stage 2 if needed. Assuming that dates or timestamps will be included
    //in the backend data which should allow streak to be manually calculated if needed.

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
        <div className="page">
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
        </div>
    )
}

export default AchievementPage;
