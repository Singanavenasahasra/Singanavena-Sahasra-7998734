import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div style={{ padding: '20px' }}>
      <h1 style={{ textAlign: 'center' }}>Student Management Portal</h1>
      <hr />
      
      {}
      <CalculateScore 
        Name="Ms. S Sahasra" 
        School="Hyderabad Engineering Academy" 
        Total={480} 
        Goal={500} 
      />
    </div>
  );
}

export default App;