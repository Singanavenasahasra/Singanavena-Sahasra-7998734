import React, { useState } from 'react';

function CurrencyConverter() {
  const [rupees, setRupees] = useState('');
  const [euros, setEuros] = useState(0);

  const handleSubmit = (e) => {
    
    e.preventDefault(); 
    
    
    const convertedValue = parseFloat(rupees || 0) * 0.011;
    setEuros(convertedValue.toFixed(2));
  };

  return (
    <div style={{ marginTop: '30px', padding: '15px', border: '1px solid #ccc', borderRadius: '6px', maxWidth: '400px' }}>
      <h3>Currency Converter (INR to EUR)</h3> 
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '10px' }}>
          <label style={{ marginRight: '10px' }}>Indian Rupees (INR):</label>
          <input 
            type="number" 
            value={rupees} 
            onChange={(e) => setRupees(e.target.value)} 
            placeholder="Enter Amount"
            style={{ padding: '5px' }}
            required
          />
        </div>
        <button type="submit" style={{ padding: '6px 12px', cursor: 'pointer', backgroundColor: '#007bff', color: '#fff', border: 'none', borderRadius: '4px' }}>
          Convert
        </button> 
      </form>
      <h4 style={{ marginTop: '15px', color: '#28a745' }}>Equivalent Euros: € {euros}</h4>
    </div>
  );
}

export default CurrencyConverter;