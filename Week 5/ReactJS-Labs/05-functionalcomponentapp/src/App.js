import React from 'react';
import CohortDetails from './CohortDetails';


const mockCohorts = [
  {
    cohortCode: "INTADMDF10",
    name: ".NET FSD",
    startedOn: "22-Feb-2022",
    status: "Scheduled",
    coach: "Aathma",
    trainer: "Jojo Jose"
  },
  {
    cohortCode: "ADM21JF014",
    name: "Java FSD",
    startedOn: "10-Sep-2021",
    status: "Ongoing",
    coach: "Apoorv",
    trainer: "Elisa Smith"
  },
  {
    cohortCode: "CDBJF21025",
    name: "Java FSD",
    startedOn: "24-Dec-2021",
    status: "Ongoing",
    coach: "Aathma",
    trainer: "John Doe"
  }
];

function App() {
  return (
    <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
      <h1>Cohorts Details</h1>
      <div style={{ display: 'flex', flexWrap: 'wrap' }}>
        {mockCohorts.map((item) => (
          <CohortDetails key={item.cohortCode} cohort={item} />
        ))}
      </div>
    </div>
  );
}

export default App;