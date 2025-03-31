import React, { useState } from "react";

function MyButton() {
  return (
      <button>
        I'm a button
      </button>
  );
}

export default function MyApp() {
  return (
      <>
          <div>
              <h1>Welcome to my app</h1>
              <MyButton />
          </div>
          <div>
              <h1>Select Payment Type</h1>
              <TabGroup />
          </div>
      </>
  );
}

function TabGroup() {
    const types = ["Basic", "Premium", "Enterprise"];
    const [active, setActive] = useState(types[0]);
    return (
        <>
            <div>
                {types.map((type) => (
                    <button
                        key={type}
                        className={active === type ? "active-tab" : ""}
                        onClick={() => setActive(type)}
                    >
                        {type}
                    </button>
                ))}
            </div>
            <p />
            <p> Your payment selection: {active} </p>
        </>
    );
}