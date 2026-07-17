import React, { Component } from 'react';

class Register extends Component {
  constructor(props) {
    super(props);

    this.state = {
      fullName: '',
      email: '',
      password: '',
      errors: {
        fullName: '',
        email: '',
        password: ''
      }
    };
  }

  
  handleChange = (event) => {
    const { name, value } = event.target;
    let errors = { ...this.state.errors };

    switch (name) {
      case 'fullName':
        
        errors.fullName = value.length < 5 
          ? 'Full Name must be 5 characters long!' 
          : '';
        break;
      case 'email':
         
        const validEmailRegex = RegExp(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/i);
        errors.email = validEmailRegex.test(value) 
          ? '' 
          : 'Email is not valid!';
        break;
      case 'password':
        
        errors.password = value.length < 8 
          ? 'Password must be 8 characters long!' 
          : '';
        break;
      default:
        break;
    }

    
    this.setState({ errors, [name]: value });
  };

  
  validateForm = (errors) => {
    let valid = true;
    Object.values(errors).forEach((val) => val.length > 0 && (valid = false));
    return valid;
  };

  
  handleSubmit = (event) => {
    event.preventDefault(); 
    
    
    if (this.state.fullName && this.state.email && this.state.password && this.validateForm(this.state.errors)) {
      alert('Valid Form [cite: 344]');
    } else {
      
      if (this.state.errors.fullName !== '') {
        alert(this.state.errors.fullName); 
      } else if (this.state.errors.email !== '') {
        alert(this.state.errors.email);    
      } else if (this.state.errors.password !== '') {
        alert(this.state.errors.password); 
      } else {
        alert('Please fill out all form inputs correctly before submitting.');
      }
    }
  };

  render() {
    return (
      <div style={{ textAlign: 'center', marginTop: '50px', fontFamily: 'Arial, sans-serif' }}>
        <h1 style={{ color: 'red', fontSize: '48px', fontWeight: 'bold' }}>Register Here!!!</h1>
        
        <form onSubmit={this.handleSubmit} noValidate style={{ display: 'inline-block', textAlign: 'left' }}>
          <div style={{ marginBottom: '15px' }}>
            <label style={{ display: 'inline-block', width: '100px', fontSize: '18px' }}>Name:</label>
            <input 
              type="text" 
              name="fullName" 
              onChange={this.handleChange} 
              style={{ padding: '5px', fontSize: '16px', border: '1px solid #ccc', borderRadius: '4px', width: '250px' }}
            />
          </div>

          <div style={{ marginBottom: '15px' }}>
            <label style={{ display: 'inline-block', width: '100px', fontSize: '18px' }}>Email:</label>
            <input 
              type="email" 
              name="email" 
              onChange={this.handleChange}
              style={{ padding: '5px', fontSize: '16px', border: '1px solid #ccc', borderRadius: '4px', width: '250px' }}
            />
          </div>

          <div style={{ marginBottom: '20px' }}>
            <label style={{ display: 'inline-block', width: '100px', fontSize: '18px' }}>Password:</label>
            <input 
              type="password" 
              name="password" 
              onChange={this.handleChange}
              style={{ padding: '5px', fontSize: '16px', border: '1px solid #ccc', borderRadius: '4px', width: '250px' }}
            />
          </div>

          <div style={{ paddingLeft: '100px' }}>
            <button 
              type="submit" 
              style={{ padding: '6px 15px', fontSize: '16px', cursor: 'pointer', backgroundColor: '#f0f0f0', border: '1px solid #a6a6a6', borderRadius: '3px' }}
            >
              Submit
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default Register;