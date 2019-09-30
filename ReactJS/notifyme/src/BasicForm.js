import React from "react";
import { Button, TextField } from "@material-ui/core";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import { validate } from "@babel/types";

class Forms extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
    this.postchanges = this.postchanges.bind(this);
    this.validate = this.validate.bind(this);
  }
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
    if (this.validate()) this.props.callback(event, this.state);
  }

  render() {
    return (
      <div>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6">Add Course</Typography>
          </Toolbar>
        </AppBar>

        <TextField
          label={"CourseName"}
          value={this.props.newProp}
          InputProps={{
            readOnly: true
          }}
        />

        <form>
          {Object.entries(this.props.fields).map(([key, value]) => (
            <div sm={1}>
              <TextField
                id="standard-name"
                label={key}
                type={value}
                name={key}
                onChange={this.myChangeHandler}
                margin="normal"
              />
              <br />
            </div>
          ))}
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
    );
  }
}

export default Forms;
