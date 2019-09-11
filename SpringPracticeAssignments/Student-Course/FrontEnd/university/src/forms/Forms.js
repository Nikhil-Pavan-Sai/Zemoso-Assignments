import React, { Component } from "react";
import "/home/user/Documents/Assignments/SpringPracticeAssignments/Student-Course/FrontEnd/university/src/Security/Login.css";
import { TextField } from "material-ui";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import RaisedButton from "material-ui/RaisedButton";

class Forms extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  myChangeHandler = event => {
    let nam = event.target.name;
    let val = event.target.value;
    this.setState({ [nam]: val });
    this.postchanges = this.postchanges.bind(this);
  };

  async postchanges(event) {
    if (this.props.submiturl) {
      await fetch(this.props.submiturl, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.state)
      });
    }
    if (this.props.callBack) this.props.callBack(event, this.state);
  }

  render() {
    return (
      <MuiThemeProvider>
        <form className="Login">
          {Object.entries(this.props.fields).map(([key, value]) => (
            <React.Fragment>
              <TextField
                type={value}
                name={key}
                hintText={`Enter your ${key}`}
                floatingLabelText={key}
                onChange={this.myChangeHandler}
              />
              <br />
            </React.Fragment>
          ))}
          <br />
          <RaisedButton
            label="Add"
            primary={true}
            style={style}
            onClick={() => this.postchanges()}
          />
        </form>
      </MuiThemeProvider>
    );
  }
}
const style = {
  margin: 15
};
export default Forms;
