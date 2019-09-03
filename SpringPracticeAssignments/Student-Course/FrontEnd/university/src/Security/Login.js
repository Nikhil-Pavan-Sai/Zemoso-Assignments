import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Register from './Register.js'
import "./Login.css";

const Heading = ({children}) => <h1>{children}</h1>

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isRegistered:false,
      email: "",
      password: ""
    };
    this.changeRegistered=this.changeRegistered.bind(this)
  }

  changeRegistered()
  {
      this.setState({isRegistered:!this.state.isRegistered})
  }


  async validateForm(callback) {
    if(this.state.email.includes(".com"))
    {
        const response=fetch("/user/validate", {method:"POST", headers:{'Accept':'application/json',
        'Content-Type':'application/json'}, body:JSON.stringify(this.state)}).then(x => {if(x.status == 200) callback(); else alert("Invalid Credentials !")});

    }
    if(!this.state.email.includes(".com"))
    {
        alert("Please Enter a valid Email !");
    }
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
  if(this.state.isRegistered)
          return(<Register registerUser={this.changeRegistered}/>)
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
            onClick={() => {this.validateForm(this.props.callback)}}
            type="submit"
          >
            Login
          </Button>

          <br/>

          <Button className="fontSize"
          onClick={this.changeRegistered}
          >
          Register </Button>

        </form>
      </div>
    );
  }
}

export default Login;