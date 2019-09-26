import React, { Component } from "react";
import "./App.css";
import "./Security/Login.css";
import Select from "react-select";
import { RaisedButton } from "material-ui";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import AppBar from "material-ui/AppBar";
import ExpansionPanel from "@material-ui/core/ExpansionPanel";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import Typography from "@material-ui/core/Typography";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { Container } from "@material-ui/core";

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
    const lis = [];
    course_body.forEach(item =>
      lis.push({ label: item.courseName, value: item.id })
    );
    this.setState({ options: lis });
  }

  changeAddCourse() {
    this.setState({ isAddCourse: !this.state.isAddCourse });
  }

  async addCourseToStudent(callback) {
    const response = fetch(
      "/students/" + this.props.id + "/courses/" + this.state.selected,
      {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.state)
      }
    );
  }

  handleChange = event => {
    console.log(event);
    this.setState({
      selected: event.value
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
    fetch("/students/" + this.props.id + "/courses/" + this.state.selected, {
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
      <MuiThemeProvider>
        <div>
          <AppBar title="Choose Course" />
          <div className="menuButton">
            <div className="menuButton">
              <Select
                className="cmb"
                options={this.state.options}
                onChange={this.handleChange}
              />
            </div>
            <br />
            <div className="center">
              <RaisedButton
                label="Assign"
                primary={true}
                onClick={this.handleSubmit}
              ></RaisedButton>
              <br />
              <br />
              <RaisedButton
                label="Home"
                primary={true}
                onClick={() => {
                  this.props.goHome();
                }}
              ></RaisedButton>
            </div>
          </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default AddCourse;
