import React, { useState } from 'react';
import CurrencyConverter from './CurrencyConverter';

function App() {
  const [counter, setCounter] = useState(0);

  
  const handleIncreaseEvents = (e) => {

    setCounter((prev) => prev + 1); 
    
   
    alert("Hello! You just triggered an increment operation."); 
  };

  const handleDecreaseEvent = () => {
    setCounter((prev) => prev - 1); 
  };


  const sayWelcomeHandler = (messageText) => {
    alert(`System message parameter argument: "${messageText}"`); 
  };

 
  const handleSyntheticEvent = (e) => {
    console.log("Synthetic Event Native Object context profile:", e);
    alert(`I was clicked! Triggered event primitive subclass type: "${e.type}"`); 
  };

  return (
    <div style={{ padding: '40px', fontFamily: 'Arial, sans-serif' }}>
      <h2>Lab 11: Form Event Handling Controls</h2> 
      <hr />

      
      <div style={{ margin: '20px 0' }}>
        <h3>Counter Value: {counter}</h3>
        <button onClick={handleIncreaseEvents} style={{ marginRight: '10px', padding: '6px 12px', cursor: 'pointer' }}>
          Increment
        </button> 
        
        <button onClick={handleDecreaseEvent} style={{ padding: '6px 12px', cursor: 'pointer' }}>
          Decrement
        </button> 
      </div>
      <hr />

      <div style={{ margin: '20px 0' }}>
        <h3>Custom Parameter Passing</h3>
        <button onClick={() => sayWelcomeHandler('welcome')} style={{ marginRight: '10px', padding: '6px 12px', cursor: 'pointer' }}>
          Say Welcome
        </button> 
      </div>
      <hr />

      <div style={{ margin: '20px 0' }}>
        <h3>Synthetic Events Inspection</h3>
        <button onClick={handleSyntheticEvent} style={{ padding: '6px 12px', cursor: 'pointer', backgroundColor: '#6c757d', color: '#fff', border: 'none', borderRadius: '4px' }}>
          Trigger Synthetic Event
        </button> 
      </div>
      <hr />

      <CurrencyConverter /> 
    </div>
  );
}

export default App;