import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Register from "./Register.js";
import "./Login.css";
import "/home/user/Documents/Assignments/SpringPracticeAssignments/Student-Course/FrontEnd/university/src/App.css";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import AppBar from "material-ui/AppBar";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";

const Heading = ({ children }) => <h1>{children}</h1>;

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isRegistered: false,
      email: "",
      password: ""
    };
    this.changeRegistered = this.changeRegistered.bind(this);
  }

  changeRegistered() {
    this.setState({ isRegistered: !this.state.isRegistered });
  }

  async validateForm(callback) {
    if (this.state.email.includes(".com")) {
      const response = fetch("/user/validate", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          email: this.state.email,
          password: this.state.password
        })
      }).then(x => {
        if (x.status == 200) callback();
        else alert("Invalid Credentials !");
      });
    }
    if (!this.state.email.includes(".com")) {
      alert("Please Enter a valid Email !");
    }
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  handleSubmit = event => {
    event.preventDefault();
  };

  render() {
    if (this.state.isRegistered)
      return <Register registerUser={this.changeRegistered} />;
    return (
      <div>
        <MuiThemeProvider>
          <div>
            <AppBar title="Login" />
            <div className="Login">
              <a></a>
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
                label="Login"
                primary={true}
                style={style}
                onClick={() => this.validateForm(this.props.callback)}
              />
              <RaisedButton
                label="Register"
                primary={true}
                style={style}
                onClick={() => this.changeRegistered()}
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
export default Login;
