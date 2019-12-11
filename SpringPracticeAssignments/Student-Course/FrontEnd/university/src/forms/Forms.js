import React from "react";
import "/home/user/Documents/Assignments/SpringPracticeAssignments/Student-Course/FrontEnd/university/src/Security/Login.css";
import Button from "@material-ui/core/Button";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";
import TextField from "@material-ui/core/TextField";
import "./Forms.css";

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
      const ret = await fetch(this.props.submiturl, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.state)
      });

      const response = await ret.json();
      if (this.props.callBack) this.props.callBack(event, response);
    }
  }

  render() {
    return (
      <div className="bg">
        <AppBar position="static">
          <Toolbar variant="dense">
            <Typography variant="h6" color="inherit" className="title">
              Add new element
            </Typography>
            <Button color="inherit" onClick={() => this.props.goHome()}>
              Home
            </Button>
          </Toolbar>
        </AppBar>
        <div>
          <form className="Login">
            {Object.entries(this.props.fields).map(([key, value]) => (
              <React.Fragment key={key}>
                <TextField
                  variant="outlined"
                  margin="normal"
                  required
                  type={value}
                  name={key}
                  label={`Enter your ${key}`}
                  onChange={this.myChangeHandler}
                  autoFocus
                />
                <br />
              </React.Fragment>
            ))}
            <br />
            <Button
              type="submit"
              variant="contained"
              color="primary"
              onClick={e => {
                e.preventDefault();
                this.postchanges();
              }}
            >
              Add
            </Button>
          </form>
        </div>
      </div>
    );
  }
}
export default Forms;
