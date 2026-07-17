import React from 'react';

function App() {
  const element = "Office Space";
  const ItemName = { Name: "DBS", Rent: 50000, Address: 'Chennai' };
  
  const rentStyle = {
    color: ItemName.Rent < 60000 ? 'red' : 'green',
    fontWeight: 'bold'
  };

  const sr = "https://via.placeholder.com/250x150";

  return (
    <div style={{ padding: '40px', fontFamily: 'Arial, sans-serif' }}>
      <h1>{element} , at Affordable Range</h1>
      
      <img 
        src={sr} 
        width="25%" 
        height="25%" 
        alt="Office Space"
        style={{ border: '1px solid #ccc', borderRadius: '4px', margin: '15px 0' }}
      />

      <h2>Name: {ItemName.Name}</h2>
      <h3 style={rentStyle}>Rent: Rs. {ItemName.Rent}</h3>
      <h3>Address: {ItemName.Address}</h3>
    </div>
  );
}

export default App;