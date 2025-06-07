import { useEffect, useState } from 'react';
import { LineChart, Line, XAxis, YAxis, Legend, Label, ResponsiveContainer } from 'recharts';
import { useNavigate } from "react-router-dom";
import './WebApp.css';

function GraphsPage() {
    const navigate = useNavigate();
    const options = ['Speed', 'Cadence', 'Altitude'];
    const [data, setData] = useState([]);
    const [selectedWorkoutIndex, setSelectedWorkoutIndex] = useState(0);
    const [checkedOptions, setCheckedOptions] = useState({});
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const userId = localStorage.getItem("userID");
        fetch(`https://full-stack-fitness-web-app.onrender.com/users/${userId}/data`)
            .then(res => res.json())
            .then(data => {
                console.log("Fetched data:", data);

                const workoutsData = data.workouts.map((workout, index) => {

                    const date = workout.dateTime ? new Date(workout.dateTime).toLocaleDateString() : `Workout ${index + 1}`;

                    const speeds = workout.splits ? workout.splits.map(split => split.speed || 0) : [];
                    const altitudes = workout.splits ? workout.splits.map(split => split.altitude || 0) : [];
                    const cadences = workout.splits ? workout.splits.map(split => split.cadence || 0) : [];

                    return {
                        id: workout.id || index,
                        date,
                        speeds,
                        altitudes,
                        cadences
                    };
                });

                console.log("Mapped workout data:", workoutsData);
                setData(workoutsData);
                setSelectedWorkoutIndex(0);
                setIsLoading(false);
            })
            .catch(err => {
                console.error("Failed to fetch workout data:", err);
                setIsLoading(false);
            });
    }, []);

    const handleWorkoutChange = (event) => {
        setSelectedWorkoutIndex(event.target.value);
    };

    function handleCheck(event, option) {
        const { checked } = event.target;
        setCheckedOptions((prev) => ({
            ...prev,
            [option]: checked,
        }));
    }

    const selectedWorkout = data[selectedWorkoutIndex];
    console.log("Selected Workout:", selectedWorkout);

    return (
        <div className="graphs">
            <h1 className='title'>Graph View</h1>

            <div className='graphPageLayout'>
                <div className='optionsPanel'>
                    <h3>Graph Options:</h3>
                    {options.map((option) => (
                        <label className='option' key={option}>
                            <input type='checkbox' checked={!!checkedOptions[option]} onChange={(event) => handleCheck(event, option)} />
                            {option}
                        </label>
                    ))}
                </div>

                <div className='workoutSelect'>
                    <h3>Select Workout:</h3>
                    <select onChange={handleWorkoutChange} value={selectedWorkoutIndex}>
                        {data.map((workout, index) => (
                            <option key={workout.id} value={index}>
                                {workout.date}
                            </option>
                        ))}
                    </select>
                </div>

                <div className='graphDiv'>
                    {isLoading ? (
                        <p>Loading graph data...</p>
                    ) : (
                        <ResponsiveContainer width="100%" height={500} className='lineGraph'>
                            <LineChart data={selectedWorkout ? selectedWorkout.speeds.map((_, idx) => ({
                                workoutNum: idx + 1,
                                speeds: selectedWorkout.speeds[idx],
                                altitudes: selectedWorkout.altitudes[idx],
                                cadences: selectedWorkout.cadences[idx]
                            })) : []}>
                                <XAxis dataKey='workoutNum'>
                                    <Label value='Split number' offset={-5} position='insideBottom' dy={-5} />
                                </XAxis>
                                <YAxis>
                                    <Label value='Speed, Altitude, Cadence' angle={-90} position='bottomLeft' dx={-20} />
                                </YAxis>
                                <Legend />
                                {checkedOptions['Speed'] && <Line type='Monotone' dataKey='speeds' stroke="#FF0000" strokeWidth={2} />}
                                {checkedOptions['Cadence'] && <Line type='Monotone' dataKey='cadences' stroke="#808080" strokeWidth={2} />}
                                {checkedOptions['Altitude'] && <Line type='Monotone' dataKey='altitudes' stroke="#008000" strokeWidth={2} />}
                            </LineChart>
                        </ResponsiveContainer>
                    )}
                </div>

                <button className='back' onClick={() => navigate('/Layout/MainWindow')}>Back</button>
            </div>
        </div>
    );
}

export default GraphsPage;

