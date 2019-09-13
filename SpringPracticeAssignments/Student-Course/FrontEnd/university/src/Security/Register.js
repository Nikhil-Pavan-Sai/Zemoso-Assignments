import React, { Component } from "react";
import "./Login.css";
import "/home/user/Documents/Assignments/SpringPracticeAssignments/Student-Course/FrontEnd/university/src/App.css";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import AppBar from "material-ui/AppBar";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";

class Register extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      firstName: "",
      lastName: "",
      email: "",
      password: ""
    };
    this.addUser = this.addUser.bind(this);
    this.validateForm = this.validateForm.bind(this);
  }
  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  handleSubmit = event => {
    event.preventDefault();
  };

  async validateForm() {
    if (!this.state.email.includes(".com")) {
      alert("Please Enter a valid Email !");
    } else {
      this.addUser().then(this.props.registerUser());
    }
  }

  async addUser() {
    const response = fetch("/user/add", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state)
    }).then(alert("Successfully Registered !"));

    if (!this.state.email.includes(".com")) {
      alert("Please Enter a valid Email !");
    }
  }

  render() {
    return (
      <div>
        <MuiThemeProvider>
          <div>
            <AppBar title="Register" />
            <div className="Login">
              <TextField
                hintText="Enter your First Name"
                floatingLabelText="First Name"
                onChange={(event, newValue) =>
                  this.setState({ firstName: newValue })
                }
              />
              <br />
              <TextField
                hintText="Enter your Last Name"
                floatingLabelText="Last Name"
                onChange={(event, newValue) =>
                  this.setState({ lastName: newValue })
                }
              />
              <br />
              <TextField
                hintText="Enter your Email"
                floatingLabelText="Email"
                onChange={(event, newValue) =>
                  this.setState({ email: newValue })
                }
              />
              <br />
              <TextField
                type="password"
                hintText="Enter your Password"
                floatingLabelText="Password"
                onChange={(event, newValue) =>
                  this.setState({ password: newValue })
                }
              />
              <br />
              <RaisedButton
                label="Register"
                primary={true}
                style={style}
                onClick={() => this.validateForm(this.props.registerUser)}
              />
              <br />
              <a>Already Registered?</a>
              <RaisedButton
                label="Login"
                primary={true}
                style={style}
                onClick={() => this.props.registerUser()}
              />
            </div>
          </div>
        </MuiThemeProvider>
      </div>
    );
  }
}
const style = {
  margin: 15
};
export default Register;
