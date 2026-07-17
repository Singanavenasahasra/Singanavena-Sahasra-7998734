import React, { Component } from 'react';

export default class Cart extends Component {
  render() {
    return (
      <table border="1" cellPadding="10" style={{ margin: '20px auto', textAlign: 'center', borderCollapse: 'collapse' }}>
        <thead>
          <tr style={{ color: 'green' }}>
            <th>Name</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {this.props.item.map((item, index) => (
            <tr key={index}>
              <td>{item.itemname}</td>
              <td>{item.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  }
}