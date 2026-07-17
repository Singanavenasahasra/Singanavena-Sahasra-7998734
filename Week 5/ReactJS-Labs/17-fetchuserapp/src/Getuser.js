import React, { Component } from 'react';

class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      person: null,
      loading: true
    };
  }

  async componentDidMount() {
    const url = "https://api.randomuser.me/";
    const response = await fetch(url);
    const data = await response.json();
    this.setState({ person: data.results[0], loading: false });
    console.log(data.results[0]);
  }

  render() {
    const { person, loading } = this.state;

    if (loading) {
      return <div style={{ padding: '50px', fontFamily: 'Arial' }}>Loading user details...</div>;
    }

    return (
      <div style={{ padding: '50px', fontFamily: 'Arial, sans-serif' }}>
        {/* Enforces the layout alignment for Title + First Name */}
        <h1 style={{ fontSize: '32px', fontWeight: 'bold', marginBottom: '15px' }}>
          {person.name.title} {person.name.first}
        </h1>
        <img 
          src={person.picture.large} 
          alt="User Profile" 
          style={{ width: '150px', height: '150px', display: 'block' }}
        />
      </div>
    );
  }
}

export default Getuser;