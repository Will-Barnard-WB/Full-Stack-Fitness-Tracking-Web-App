import { useNavigate } from "react-router-dom";
import './WebApp.css';

function Leaderboard() {
    const leaderboard = [
        [['Aaron', 2.57], ['Isaac', 3.2]],
        [['Aaron', 35], ['Isaac', 305]]
    ];

    return (
        <div className="leaderboardContainer">
            <h2 className="leaderboardTitle">Leaderboard</h2>
            <ul className="leaderboardList">
                {leaderboard[0].map((nameVal, index) => (
                    <li key={index} className="leaderboardItem">
                        <span className="rank">{index + 1}.</span>
                        <span className="name">{nameVal[0]}</span>
                        <span className="score">Time: {nameVal[1]}</span>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Leaderboard;