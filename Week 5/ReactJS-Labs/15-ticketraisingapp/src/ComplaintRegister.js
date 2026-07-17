import React from 'react';

class ComplaintRegister extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      ename: '',
      complaint: '',
      NumberHolder: 0
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  handleSubmit(event) {
    const randomId = Math.floor(Math.random() * 100) + 1;
    
    this.setState({ NumberHolder: randomId }, () => {
      var msg = 'Thanks ' + this.state.ename + '\nYour Complaint was Submitted.\nTransaction ID is: ' + this.state.NumberHolder;
      alert(msg);
    });

    event.preventDefault();
  }

  render() {
    return (
      <div style={{ padding: '40px', fontFamily: 'Arial, sans-serif', maxWidth: '600px', margin: '0 auto' }}>
        <h1 style={{ color: 'red', textAlign: 'center', marginBottom: '30px' }}>
          Register your complaints here!!!
        </h1>
        
        <form onSubmit={this.handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
          <div style={{ display: 'flex', alignItems: 'center' }}>
            <label style={{ width: '120px', fontSize: '18px' }}>Name:</label>
            <input 
              type="text" 
              name="ename" 
              value={this.state.ename} 
              onChange={this.handleChange} 
              style={{ width: '250px', padding: '5px', fontSize: '16px' }}
              required
            />
          </div>

          <div style={{ display: 'flex', alignItems: 'flex-start' }}>
            <label style={{ width: '120px', fontSize: '18px', marginTop: '5px' }}>Complaint:</label>
            <textarea 
              name="complaint" 
              value={this.state.complaint} 
              onChange={this.handleChange} 
              rows="4"
              style={{ width: '250px', padding: '5px', fontSize: '16px', resize: 'vertical' }}
              required
            />
          </div>

          <div style={{ paddingLeft: '120px' }}>
            <button type="submit" style={{ padding: '6px 20px', fontSize: '16px', cursor: 'pointer' }}>
              Submit
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default ComplaintRegister;