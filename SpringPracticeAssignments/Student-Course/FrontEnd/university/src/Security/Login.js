import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "./Login.css";

const Heading = ({children}) => <h1>{children}</h1>

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: ""
    };
  }

  validateForm() {
    return this.state.email == "9701853281mnps@gmail.com" && this.state.password == "alphabets";
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    event.preventDefault();
  }



  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit}>

          <Heading> <font color="#1DECEC"> Login </font></Heading>

          <div className="Text-Color">
              <FormGroup controlId="email" bsSize="large">
                <ControlLabel> Email </ControlLabel><br/>
                <FormControl
                  autoFocus
                  type="email"
                  value={this.state.email}
                  onChange={this.handleChange}
                />
              </FormGroup>
          </div>

          <br/>

          <div className="Text-Color">
          <FormGroup controlId="password" bsSize="large">
            <ControlLabel> Password </ControlLabel><br/>
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          </div>

          <br/>

          <Button
            block
            bsSize="large"
            onClick={() => {if(this.validateForm()) this.props.callback(); else alert("Incorrect password !")}}
            type="submit"
          >
            Login
          </Button>
        </form>
      </div>
    );
  }
}

export default Login;