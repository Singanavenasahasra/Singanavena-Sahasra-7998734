import React from 'react';
import CohortDetails from './CohortDetails';
import { CohortData } from './Cohort';

function App() {
  return (
    <div style={{ padding: '20px' }}>
      <h2>Academy Cohorts Dashboard</h2>
      <CohortDetails cohort={CohortData[0]} />
    </div>
  );
}

export default App;