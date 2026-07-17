import React from 'react';

const Counter = ({ count, onIncrement, onDecrement, onReset }) => {
  return (
    <div style={{ textAlign: 'center', margin: '20px 0' }}>
      <h3 style={{ fontSize: '24px', color: '#333' }}>Current Value: {count}</h3>
      <div style={{ display: 'flex', gap: '10px', justifyContent: 'center' }}>
        <button 
          onClick={onIncrement} 
          style={{ padding: '8px 16px', backgroundColor: '#28a745', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
        >
          Increment (+)
        </button>
        <button 
          onClick={onDecrement} 
          style={{ padding: '8px 16px', backgroundColor: '#dc3545', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
        >
          Decrement (-)
        </button>
        <button 
          onClick={onReset} 
          style={{ padding: '8px 16px', backgroundColor: '#6c757d', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
        >
          Reset
        </button>
      </div>
    </div>
  );
};

export default Counter;