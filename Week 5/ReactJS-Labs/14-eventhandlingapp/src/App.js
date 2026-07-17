import React, { useState } from 'react';
import Counter from './Counter';

function App() {
  const [count, setCount] = useState(0);

  
  const handleIncrement = () => setCount(prev => prev + 1);
  const handleDecrement = () => setCount(prev => prev - 1);
  const handleReset = () => setCount(0);

  return (
    <div style={{ padding: '30px', fontFamily: 'Arial, sans-serif', maxWidth: '500px', margin: '0 auto' }}>
      <h2 style={{ textAlign: 'center', color: '#0056b3' }}>Lab 14: Props & Event Handling</h2>
      <hr />
      <p style={{ textAlign: 'center', color: '#666' }}>
        Managing component state globally and passing action handlers downstream via React props.
      </p>
      
      <Counter 
        count={count} 
        onIncrement={handleIncrement} 
        onDecrement={handleDecrement} 
        onReset={handleReset} 
      />
    </div>
  );
}

export default App;