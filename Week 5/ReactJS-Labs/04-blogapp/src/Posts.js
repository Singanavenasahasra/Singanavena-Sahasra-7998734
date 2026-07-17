import React from 'react';

class Posts extends React.Component {
  constructor(props) {
    super(props);
   
    this.state = {
      postsList: [],
      hasError: false
    };
  }


  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
       
        this.setState({ postsList: data });
      })
      .catch(error => {
      
        this.setState({ hasError: true });
        alert("Error fetching posts: " + error.message);
      });
  }


  componentDidMount() {
    this.loadPosts();
  }

  
  componentDidCatch(error, info) {
    alert("An error occurred within the component: " + error.toString());
  }

  render() {
    if (this.state.hasError) {
      return <div style={{ color: 'red', textAlign: 'center' }}>Failed to load posts dashboard.</div>;
    }

    return (
      <div style={{ maxWidth: '800px', margin: '0 auto', padding: '10px' }}>
        <h2 style={{ textAlign: 'center', color: '#0056b3' }}>Latest Blog Posts</h2>
        <hr />
        {this.state.postsList.map(post => (
          <div key={post.id} style={{ borderBottom: '1px solid #eee', padding: '15px 0' }}>
            {/* Displaying title using heading element */}
            <h3 style={{ margin: '0 0 10px 0', color: '#333', textTransform: 'capitalize' }}>
              {post.id}. {post.title}
            </h3>
            {/* Displaying post body using paragraph element */}
            <p style={{ margin: '0', color: '#666', lineHeight: '1.6' }}>
              {post.body}
            </p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;