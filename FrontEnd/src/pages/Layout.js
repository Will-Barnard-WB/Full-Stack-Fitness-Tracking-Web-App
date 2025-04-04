import { Link, Outlet } from "react-router-dom";

export default function Layout() {
    return (
        <div>
            <h1>Dashboard</h1>
            <nav>
                <ul>
                    <li>
                        <Link to="/Layout/MainWindow">MainWindow</Link>
                    </li>
                </ul>
            </nav>
            <div>
                <Outlet /> {/* This is where child routes will be rendered */}
            </div>
        </div>
    );
}