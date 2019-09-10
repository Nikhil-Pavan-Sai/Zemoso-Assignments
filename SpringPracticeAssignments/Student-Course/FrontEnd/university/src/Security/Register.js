import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "./Login.css";
import "/home/user/Documents/Assignments/SpringPracticeAssignments/Student-Course/FrontEnd/university/src/App.css"

const Heading = ({children}) => <h1>{children}</h1>

class Register extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: ""
    };
  }
 handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    event.preventDefault();
  }

  async addUser(registerUser)
  {
        const response=fetch("/user/add", {method:"POST", headers:{'Accept':'application/json',
                'Content-Type':'application/json'}, body:JSON.stringify(this.state)}).then(alert("Successfully Registered !")).then(registerUser());

        if(!this.state.email.includes(".com"))
        {
             alert("Please Enter a valid Email !");
        }
  }

  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit}>

          <Heading> <font color="#1DECEC"> Register </font></Heading>

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

          <Button className="glass"
            block
            bsSize="large"
            onClick={() => {this.addUser(this.props.registerUser)}}
            type="submit"
          >
            Register
          </Button>
        </form>
      </div>
    );
  }
}

export default Register;