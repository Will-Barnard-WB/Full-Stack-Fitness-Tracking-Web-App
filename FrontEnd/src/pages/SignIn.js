import { useNavigate } from "react-router-dom";

export default function SignIn() {
    const navigate = useNavigate();

    return (
        <div>
            <h1>Welcome to Strava2.0 this is the login page</h1>
            <button
                onClick={() => navigate("/TakeMood")}
            >
                Enter
            </button>
        </div>
    );
}