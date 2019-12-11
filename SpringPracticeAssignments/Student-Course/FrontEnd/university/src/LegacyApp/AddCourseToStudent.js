import React from "react";
import "../App.css";
import "../forms/Forms.css";
import "../Security/Login.css";
import Select from "react-select";
import Button from "@material-ui/core/Button";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";

class AddCourseToStudent extends React.Component {
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
    //Fetch All Tasks
    const response = await fetch("/courses/");
    let allTasks = await response.json();

    //Create a Map of Tasks
    const taskMap = new Map();
    allTasks.forEach(task => taskMap.set(task.id, task));

    const specificTaskResponse = await fetch(
      "/students/" + this.props.id + "/courses"
    );
    let specificTasks = await specificTaskResponse.json();

    //Remove assignments for current intern which are already assigned
    specificTasks.forEach(assignment => taskMap.delete(assignment.id));

    //Convert to desired format
    allTasks = [];
    taskMap.forEach(task =>
      allTasks.push({ value: task.id, label: task.courseName })
    );
    this.setState({ options: allTasks });
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
      if (this.state.options[x].courseName === this.state.selected) {
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
      <div>
        <AppBar position="static">
          <Toolbar variant="dense">
            <Typography variant="h6" color="inherit" className="title">
              Choose Course
            </Typography>
            <Button
              color="inherit"
              onClick={() => {
                this.props.goHome();
              }}
            >
              Home
            </Button>
          </Toolbar>
        </AppBar>
        <div className="menuButton">
          <Select
            className="cmb"
            options={this.state.options}
            onChange={this.handleChange}
          />
        </div>
        <br />
        <div className="center">
          <Button
            variant="contained"
            color="primary"
            onClick={this.handleSubmit}
          >
            Add Course
          </Button>
          <br />
        </div>
      </div>
    );
  }
}

export default AddCourseToStudent;
