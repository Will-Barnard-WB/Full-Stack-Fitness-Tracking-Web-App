import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./pages/Layout";
import SignIn from "./pages/SignIn";
import TakeMood from "./pages/TakeMood";
import MainWindow from "./pages/MainWindow";
import AchievementPage from "./pages/AchievementPage";
import GoalPage from "./pages/GoalPage";
import GraphsPage from "./pages/GraphsPage";
import MoodPage from "./pages/MoodPage";
import Settings from "./pages/Settings";
import SignUp from "./pages/SignUp";

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<SignIn />} />
                <Route path="/SignUp" element={<SignUp />} />

                <Route path="/TakeMood" element={<TakeMood />} />

                <Route path="/layout" element={<Layout />}>
                    <Route index element={<MainWindow />} />
                    <Route path="MainWindow" element={<MainWindow />} />
                    <Route path="AchievementPage" element={<AchievementPage />} />
                    <Route path="GoalPage" element={<GoalPage />} />
                    <Route path="GraphsPage" element={<GraphsPage />} />
                    <Route path="MoodPage" element={<MoodPage />} />
                    <Route path="Settings" element={<Settings />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
