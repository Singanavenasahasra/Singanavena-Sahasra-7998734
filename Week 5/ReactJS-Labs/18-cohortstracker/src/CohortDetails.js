import React from 'react';

function CohortDetails({ cohort }) {
  if (!cohort) return null;
  return (
    <div style={{ border: '1px solid black', padding: '10px', width: '300px' }}>
      <h3>{cohort.cohortCode} - {cohort.name}</h3>
      <dl>
        <dt>Started On</dt>
        <dd>{cohort.startedOn}</dd>
        <dt>Current Status</dt>
        <dd>{cohort.status}</dd>
        <dt>Coach</dt>
        <dd>{cohort.coach}</dd>
        <dt>Trainer</dt>
        <dd>{cohort.trainer}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;