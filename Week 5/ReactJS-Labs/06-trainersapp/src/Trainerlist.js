import React from 'react';
import { Link } from 'react-router-dom';
import trainersMock from './TrainersMock';

function TrainersList() {
  return (
    <div style={{ padding: '20px 0' }}>
      <h2>Trainers List</h2> 
      <ul>
        {trainersMock.map(trainer => (
          <li key={trainer.trainerId} style={{ margin: '10px 0', fontSize: '18px' }}>
            <Link to={`/trainers/${trainer.trainerId}`}> 
              {trainer.name} {/* cite: 200 */}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TrainersList;