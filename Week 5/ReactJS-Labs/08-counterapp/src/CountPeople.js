import React from 'react';

class CountPeople extends React.Component {
  constructor() {
    super();

    this.state = {
      entrycount: 0,
      exitcount: 0,
      c: 0
    };


    this.updateEntry = this.updateEntry.bind(this);
    this.updateExit = this.updateExit.bind(this);
  }


  updateEntry() {
    this.setState((prevState) => {
      return { entrycount: prevState.entrycount + 1 };
    });
  }
  updateExit() {
    this.setState((prevState) => {
      return { exitcount: prevState.exitcount + 1 };
    });
  }

  render() {
    const inlineButtonStyle = {
      backgroundColor: '#90ee90',
      border: '1px solid black',
      padding: '5px 10px',
      cursor: 'pointer',
      marginRight: '8px',
      borderRadius: '3px',
      fontWeight: '500'
    };

    return (
      <div style={{ display: 'flex', justifyContent: 'center', gap: '100px', marginTop: '100px', fontFamily: 'Arial, sans-serif' }}>
   
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <button onClick={this.updateEntry} style={inlineButtonStyle}>
            Login
          </button>
          <span>{this.state.entrycount} People Entered!!!</span>
        </div>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <button onClick={this.updateExit} style={inlineButtonStyle}>
            Exit
          </button>
          <span>{this.state.exitcount} People Left!!!</span>
        </div>
      </div>
    );
  }
}

export default CountPeople;