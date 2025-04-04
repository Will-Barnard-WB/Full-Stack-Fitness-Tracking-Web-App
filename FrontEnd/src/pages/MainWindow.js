import { useNavigate } from "react-router-dom";

export default function MainWindow() {
    const navigate = useNavigate();

    return (
        <div>
            <h1>MainWindow</h1>
            <button
                onClick={() => navigate("/Layout/AchivementPage")}
            >
                Achivement Page
            </button>
            <button
                onClick={() => navigate("/Layout/GoalPage")}
            >
                Goal Page
            </button>
            <button
                onClick={() => navigate("/Layout/GraphsPage")}
            >
                Graphs Page
            </button>
            <button
                onClick={() => navigate("/Layout/MoodPage")}
            >
                Mood Page
            </button>
            <button
                onClick={() => navigate("/Layout/Settings")}
            >
                Settings
            </button>
        </div>
    );
}