import React, { useState } from 'react';
import { Greeting, LoginButton, LogoutButton } from './GreetingComponents';

function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(false);

 
  const handleLoginClick = () => {
    setIsLoggedIn(true);
  }; 

  const handleLogoutClick = () => {
    setIsLoggedIn(false);
  }; 

  let button; 

  if (isLoggedIn) {
    button = <LogoutButton onClick={handleLogoutClick} />;
  } else {
    button = <LoginButton onClick={handleLoginClick} />;
  } 

  return (
    <div style={{ padding: '50px 100px', fontFamily: 'Arial, sans-serif' }}>
      
      <Greeting isLoggedIn={isLoggedIn} /> 
      
      
      <div style={{ marginTop: '20px' }}>
        {button}
      </div> 
    </div>
  );
}

export default App;