import React, { useEffect, useState } from 'react';
import GitClient from './GitClient';
import './App.css';

function App() {
  const [repositories, setRepositories] = useState([]);

  useEffect(() => {
    GitClient.getRepositories('techiesyed').then(r => setRepositories(r.data));
  }, []);

  return (
    <div className="App" style={{ textAlign: 'center', padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>Git repositories of User - TechieSyed</h1>
      <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '8px', marginTop: '20px' }}>
        {repositories.map(r => (
          <p key={r.name} style={{ margin: '0', fontSize: '16px' }}>{r.name}</p>
        ))}
      </div>
    </div>
  );
}

export default App;