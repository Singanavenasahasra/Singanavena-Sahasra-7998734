import React from 'react';
import Posts from './Posts';

function App() {
  return (
    <div style={{ backgroundColor: '#fafafa', minHeight: '100vh', padding: '20px' }}>
      {/* Adding the Posts component directly to the parent App layout */}
      <Posts />
    </div>
  );
}

export default App;