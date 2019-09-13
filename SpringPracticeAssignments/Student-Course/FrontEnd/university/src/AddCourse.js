import React, { Component } from "react";
import "./App.css";
import "./Security/Login.css";

class AddCourse extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isAddCourse: false,
      showMenu: false,
      selected: null,
      options: []
    };
    this.changeAddCourse = this.changeAddCourse.bind(this);
  }

  async componentDidMount() {
    const course_response = await fetch("/courses");
    const course_body = await course_response.json();
    this.setState({ options: course_body });
  }

  changeAddCourse() {
    this.setState({ isAddCourse: !this.state.isAddCourse });
  }

  async addCourseToStudent(callback) {
    const response = fetch("/students/" + this.props.id + "/courses", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state)
    });
  }

  handleChange = event => {
    this.setState({
      selected: event.target.value
    });
  };

  handleSubmit = event => {
    let y = null;
    for (var x in this.state.options) {
      if (this.state.options[x].courseName == this.state.selected) {
        y = this.state.options[x];
        break;
      }
    }
    fetch("/students/" + this.props.id + "/courses", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(y)
    });
    this.props.callback();
  };

  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit}>
          <select onChange={this.handleChange} width="500px">
            <option selected disabled hidden>
              Choose here
            </option>
            {this.state.options.map(option => (
              <option key={option.id} value={option.courseName}>
                {option.courseName}
              </option>
            ))}
          </select>
          <br />
          <br />
          <button onClick={this.handleSubmit}> Add </button>
          <br />
          <br />
          <button onClick={() => {}}>Home</button>
        </form>
      </div>
    );
  }
}

export default AddCourse;