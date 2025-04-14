import React, {useEffect, useState} from 'react';

const GoalPage = () => {
    // Initial goals state
    const [goals, setGoals] = useState([
        { id: 1, text: 'Run 5km ', completed: false }
    ]);

    const [newGoal, setNewGoal] = useState('');

    useEffect(() => {
        const storedGoals = localStorage.getItem("goalValues");

        if (storedGoals) {
            setGoals(JSON.parse(storedGoals));
        }
    }, []);

    // Add new goal
    const addGoal = () => {
        if (newGoal.trim() !== '') {
            const newGoalObj = {
                id: Date.now(),
                text: newGoal,
                completed: false
            };
            const updatedGoal = [...goals, newGoalObj];
            setGoals(updatedGoal);
            setNewGoal('');
            localStorage.setItem("goalValues", JSON.stringify(updatedGoal));
        }
    };

    // Toggle the completion of a goal
    const toggleGoalCompletion = (id) => {
        const updatedGoal = goals.map(goal =>
                goal.id === id ? { ...goal, completed: !goal.completed } : goal
        );
        setGoals(updatedGoal);
        localStorage.setItem("goalValues", JSON.stringify(updatedGoal));
    };

    // Remove a goal from the list
    const removeGoal = (id) => {
        const updatedGoal = goals.filter(goal => goal.id !== id);
        setGoals(updatedGoal);
        localStorage.setItem("goalValues", JSON.stringify(updatedGoal));
    };

    return (
        <div className="App">
            <h1>Goal Tracker</h1>

            <div>
                <input
                    type="text"
                    value={newGoal}
                    onChange={(e) => setNewGoal(e.target.value)}
                    placeholder="Enter new goal"
                />
                <button onClick={addGoal}>Add Goal</button>
            </div>

            <ul>
                {goals.map(goal => (
                    <li key={goal.id} style={{ textDecoration: goal.completed ? 'line-through' : 'none' }}>
                        <input
                            type="checkbox"
                            checked={goal.completed}
                            onChange={() => toggleGoalCompletion(goal.id)}
                        />
                        {goal.text}
                        <button onClick={() => removeGoal(goal.id)}>Remove</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default GoalPage;
