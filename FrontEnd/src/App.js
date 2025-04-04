import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./pages/Layout";
import SignIn from "./pages/SignIn";
import TakeMood from "./pages/TakeMood";
import MainWindow from "./pages/MainWindow";
import AchivementPage from "./pages/AchivementPage";
import GoalPage from "./pages/GoalPage";
import GraphsPage from "./pages/GraphsPage";
import MoodPage from "./pages/MoodPage";
import Settings from "./pages/Settings";

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<SignIn />} /> {/* HomePage as the entry point */}
                <Route path="/TakeMood" element={<TakeMood />} /> {/* Login Page before Layout */}

                {/* Layout with child routes */}
                <Route path="/Layout" element={<Layout />}>
                    <Route index element={<MainWindow />} /> {/* Default child when in /layout */}
                    <Route path="MainWindow" element={<MainWindow />} />
                    <Route path="AchivementPage" element={<AchivementPage />} />
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