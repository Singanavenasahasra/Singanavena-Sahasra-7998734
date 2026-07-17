import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import TrainersList from './Trainerlist';
import TrainerDetail from './TrainerDetails';

function App() {
  return (
    <BrowserRouter>
      <div style={{ padding: '30px', fontFamily: 'Arial, sans-serif', maxWidth: '800px' }}>
        <h1>My Academy Trainers App</h1>
        
        <nav style={{ fontSize: '18px', marginBottom: '20px' }}>
          <Link to="/">Home</Link> | <Link to="/trainers">Show Trainers</Link>
        </nav>
        <hr />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/trainers" element={<TrainersList />} />
          <Route path="/trainers/:id" element={<TrainerDetail />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;