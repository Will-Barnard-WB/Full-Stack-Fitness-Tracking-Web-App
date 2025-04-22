import { Outlet } from "react-router-dom";

export default function Layout() {
    return (
        <div>
                <Outlet /> {/* This is where child routes will be rendered */}
        </div>
    );
}

