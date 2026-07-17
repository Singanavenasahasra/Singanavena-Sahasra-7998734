import React from 'react';

const UserProfile = (props) => {
  return (
    <div style={{ 
      border: '1px solid #ddd', 
      borderRadius: '8px', 
      padding: '20px', 
      backgroundColor: '#fdfdfd', 
      boxShadow: '0 4px 6px rgba(0,0,0,0.05)',
      marginTop: '15px' 
    }}>
      <h3 style={{ margin: '0 0 10px 0', color: '#0056b3' }}>User Profile Card</h3>
      <p style={{ margin: '5px 0' }}><strong>Full Name:</strong> {props.name}</p>
      <p style={{ margin: '5px 0' }}><strong>Designation:</strong> {props.role}</p>
      <p style={{ margin: '5px 0' }}><strong>Location:</strong> {props.location}</p>
    </div>
  );
};

export default UserProfile;