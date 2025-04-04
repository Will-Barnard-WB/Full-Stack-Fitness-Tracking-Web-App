import { useNavigate } from "react-router-dom";

export default function TakeMood() {
    const navigate = useNavigate();

    return (
        <div>
            <h1>Take Mood Page</h1>
            <button
                onClick={() => navigate("/Layout")}
            >
                Enter Main Window
            </button>
        </div>
    );
}