import React, { useState, useEffect } from "react";
import App from "../App";
import Select from "react-select";
import Button from "@material-ui/core/Button";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";

export default function NewAddCourseToStudent(props) {
  const [options, setOptions] = useState([]);
  const [state, setState] = useState(0);
  const [pageStatus, setPageStatus] = useState("ADD_COURSE_TO_STUDENT");

  useEffect(() => {
    async function fetchData() {
      const response = await fetch("/courses/");
      let allTasks = await response.json();

      //Create a Map of Tasks
      const taskMap = new Map();
      allTasks.forEach(task => taskMap.set(task.id, task));

      const specificTaskResponse = await fetch(
        "/students/" + props.id + "/courses"
      );
      let specificTasks = await specificTaskResponse.json();

      //Remove assignments for current intern which are already assigned
      specificTasks.forEach(assignment => taskMap.delete(assignment.id));

      //Convert to desired format
      allTasks = [];
      taskMap.forEach(task =>
        allTasks.push({ value: task.id, label: task.courseName })
      );

      setOptions(allTasks);
    }
    fetchData();
  }, [props.id]);

  const handleChange = event => {
    setState(event.value);
  };

  const handleSubmit = event => {
    let y = null;
    for (var x in options) {
      if (options[x].courseName === state) {
        y = options[x];
        break;
      }
    }
    fetch("/students/" + props.id + "/courses/" + state, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(y)
    });
    setPageStatus("ADD_COURSE_TO_STUDENT");
  };

  if (pageStatus === "MAIN") {
    return <App />;
  }

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
              setPageStatus("MAIN");
            }}
          >
            Home
          </Button>
        </Toolbar>
      </AppBar>
      <div className="menuButton">
        <Select className="cmb" options={options} onChange={handleChange} />
      </div>
      <br />
      <div className="center">
        <Button variant="contained" color="primary" onClick={handleSubmit}>
          Add Course
        </Button>
        <br />
      </div>
    </div>
  );
}
