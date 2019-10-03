import React from "react";
import { Button, TextField } from "@material-ui/core";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import DateFnsUtils from "@date-io/date-fns";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker
} from "@material-ui/pickers";
import "./App.css";

class Forms extends React.Component {
  constructor(props) {
    super(props);
    this.state = { newDate: new Date() };
    this.postchanges = this.postchanges.bind(this);
    this.validate = this.validate.bind(this);
  }

  setDate = newDate => this.setState({ newDate });

  myChangeHandler = event => {
    let nam = event.target.name;
    let val = event.target.value;
    this.setState({ [nam]: val });
  };

  validate() {
    if (Object.keys(this.state).length === 0) return false;
    for (let field in this.state) {
      if (field === "") {
        return false;
      }
    }
    return true;
  }

  async postchanges(event) {
    var options = {
      weekday: "long",
      year: "numeric",
      month: "long",
      day: "numeric"
    };

    if (this.validate())
      this.props.callback(event, {
        ...this.state,
        CourseName: this.props.newProp,
        Date: this.state.newDate.toLocaleDateString("en-US", options)
      });
  }

  render() {
    return (
      <div>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6">Add Course</Typography>
          </Toolbar>
        </AppBar>
        <br />
        <br />

        <div>
          <form className="formControl">
            <div>
              <TextField
                label={"CourseName"}
                value={this.props.newProp}
                InputProps={{
                  readOnly: true
                }}
              />
              <br />
              <br />
              <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <Grid container justify="space-around">
                  <KeyboardDatePicker
                    disableToolbar
                    variant="inline"
                    format="MM/dd/yyyy"
                    margin="normal"
                    id="date-picker-inline"
                    label="Date picker inline"
                    value={this.state.newDate}
                    onChange={this.setDate}
                    KeyboardButtonProps={{
                      "aria-label": "change date"
                    }}
                  />
                </Grid>
              </MuiPickersUtilsProvider>
              <TextField
                id="standard-name"
                multiline
                label="Overview"
                name="Overview"
                onChange={this.myChangeHandler}
                margin="normal"
              />
              <br />
              <TextField
                id="standard-name"
                multiline
                label="Content"
                name="Content"
                onChange={this.myChangeHandler}
                margin="normal"
              />
              <br />
            </div>

            <br />
            <Button
              variant="contained"
              color="primary"
              onClick={this.postchanges}
            >
              Submit
            </Button>
          </form>
        </div>
      </div>
    );
  }
}

export default Forms;
