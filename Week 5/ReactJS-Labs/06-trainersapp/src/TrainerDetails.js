import React from 'react';
import { useParams } from 'react-router-dom';
import trainersMock from './TrainersMock';

function TrainerDetail() {

  const { id } = useParams(); // [cite: 213]
  
 
  const trainer = trainersMock.find(t => t.trainerId === id); // [cite: 214]

  if (!trainer) {
    return (
      <div style={{ color: 'red', margin: '20px 0' }}>
         Trainer Profile Not Found! (Looking for ID: "{id}")
      </div>
    );
  }

  return (
    <div style={{ padding: '20px 0', lineHeight: '1.6' }}>
      <h2>Trainers Details</h2>
      <h3 style={{ margin: '10px 0' }}>{trainer.name} ({trainer.technology})</h3>
      <p style={{ margin: '5px 0', fontSize: '16px' }}>{trainer.email}</p>
      <p style={{ margin: '5px 0', fontSize: '16px' }}>{trainer.phone}</p>
      
      <ul style={{ marginTop: '15px' }}>
        {trainer.skills.map((skill, index) => (
          <li key={index} style={{ fontSize: '16px', margin: '5px 0' }}>{skill}</li>
        ))}
      </ul>
    </div>
  );
}

export default TrainerDetail;