import React from 'react';
import '../Stylesheets/mystyle.css';


const CalculateScore = ({ Name, School, Total, Goal }) => {
  
  const average = (Total / Goal) * 100;

  return (
    <div className="scoreCard">
      <h2>Student Scorecard</h2>
      <div className="scoreElement"><strong>Student Name:</strong> {Name}</div>
      <div className="scoreElement"><strong>School:</strong> {School}</div>
      <div className="scoreElement"><strong>Total Score:</strong> {Total}</div>
      <div className="scoreElement"><strong>Goal Target:</strong> {Goal}</div>
      <div className="scoreElement">
        <strong>Average Score:</strong> {average.toFixed(2)}%
      </div>
    </div>
  );
};

export default CalculateScore;