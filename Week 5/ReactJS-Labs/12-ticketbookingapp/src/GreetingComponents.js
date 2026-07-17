import React from 'react';


export function LoginButton(props) {
  return (
    <button onClick={props.onClick} style={{ padding: '6px 15px', cursor: 'pointer' }}>
      Login
    </button>
  );
} 


export function LogoutButton(props) {
  return (
    <button onClick={props.onClick} style={{ padding: '6px 15px', cursor: 'pointer' }}>
      Logout
    </button>
  );
} 


function UserGreeting() {
  return (
    <div>
      <h1 style={{ fontSize: '36px', fontWeight: 'bold', margin: '20px 0' }}>Welcome back</h1>
      <div style={{ padding: '15px', backgroundColor: '#e8f5e9', border: '1px solid #c8e6c9', borderRadius: '4px', maxWidth: '400px' }}>
        <h4 style={{ margin: '0 0 5px 0', color: '#2e7d32' }}>✔ Booking Status Active</h4>
        <p style={{ margin: '0', color: '#555' }}>You are cleared to reserve flights on this terminal.</p>
      </div>
    </div>
  );
}

function GuestGreeting() {
  return (
    <div>
      <h1 style={{ fontSize: '36px', fontWeight: 'bold', margin: '20px 0' }}>Please sign up.</h1>
      <div style={{ padding: '15px', backgroundColor: '#fff3e0', border: '1px solid #ffe0b2', borderRadius: '4px', maxWidth: '400px' }}>
        <h4 style={{ margin: '0 0 5px 0', color: '#e65100' }}>🔒 Read-Only Access</h4>
        <p style={{ margin: '0', color: '#555' }}>Please log in above to access the ticket scheduling desk.</p>
      </div>
    </div>
  );
}


export function Greeting(props) {
  const isLoggedIn = props.isLoggedIn;
  if (isLoggedIn) {
    return <UserGreeting />;
  }
  return <GuestGreeting />;
} 