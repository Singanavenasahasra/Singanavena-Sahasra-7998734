import React from 'react';
import Home from './Components/Home';
import About from './Components/About';
import Contact from './Components/Contact';

function App() {
  return (
    <div style={{ padding: '30px', fontFamily: 'Arial, sans-serif', maxWidth: '600px', margin: '0 auto' }}>
      <h2 style={{ textAlign: 'center', color: '#333' }}>Student Management Portal</h2>
      <hr />
      
      {/* Invoking all three components together */}
      <Home />
      <About />
      <Contact />
    </div>
  );
}

export default App;