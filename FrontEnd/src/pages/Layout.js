import { Link, Outlet, useLocation } from "react-router-dom";


export default function Layout() {
    const location = useLocation();
    const isMainWindow = location.pathname === "/Layout"
        || location.pathname === "/Layout/MainWindow";

    return (
        <div>
            <div>
                <Outlet /> {/* This is where child routes will be rendered */}
            </div>
            {!isMainWindow ? (
                <div>
                    <h3>Back to main window</h3>
                    <nav>
                        <ul>
                            <Link to="/Layout/MainWindow">MainWindow</Link>
                        </ul>
                    </nav>
                </div>
                ) : null
            }
        </div>
    );
}
