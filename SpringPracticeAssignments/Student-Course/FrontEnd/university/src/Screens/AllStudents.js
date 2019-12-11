import React, { useState } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Paper,
  Tabs,
  Tab,
  List,
  ListItem,
  ListItemText,
  Divider
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import Background from "../res/cool-background.png";
import { AddStudent } from "./AddStudent";
import App from "../App";
import ExpandedStudent from "./ExpandedStudent";

const useStyles = makeStyles({
  bg: {
    backgroundImage: "url(" + Background + ")",
    height: "100%",
    width: "100%",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover",
    position: "absolute"
  },
  title: {
    flexGrow: 1
  },
  content: {
    color: "#FFFFFF",
    textAlign: "center"
  }
});

export default function AllStudents(props) {
  const classes = useStyles();
  const [newStudents, setNewStudents] = useState([]);
  const [value, setValue] = useState(0);
  const [pageStatus, setPageStatus] = useState("STUDENTS_CLICKED");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  function addStudent() {
    setPageStatus("ADD_STUDENT");
  }

  async function expand(id) {
    const studentInfo = await fetch("students/" + id);
    const stInfo = await studentInfo.json();
    setNewStudents(stInfo);
    setPageStatus("STUDENT_EXPANDED");
  }

  if (pageStatus === "STUDENT_EXPANDED") {
    return <ExpandedStudent callback={newStudents} />;
  }

  if (pageStatus === "ADD_STUDENT") {
    return (
      <AddStudent
        callBack={(event, ret) => {
          let stuList = props.students;
          if ("id" in ret) stuList = stuList.concat(ret);
        }}
        goHome={() => setPageStatus("LOGGED_IN")}
        goStudent={() => setPageStatus("STUDENTS_CLICKED")}
        goCourse={() => setPageStatus("COURSES_CLICKED")}
      />
    );
  }

  if (pageStatus === "LOGGED_IN") {
    return <App />;
  }

  return (
    <div className={classes.bg}>
      <AppBar position="static">
        <Toolbar variant="dense">
          <Typography variant="h6" color="inherit" className={classes.title}>
            All Students
          </Typography>
          <Button color="inherit" onClick={() => props.goHome()}>
            Home
          </Button>
        </Toolbar>
      </AppBar>
      <AppBar position="static">
        <Toolbar variant="dense">
          <Paper className={classes.title}>
            <Tabs
              indicatorColor="primary"
              textColor="primary"
              centered
              value={value}
              onChange={handleChange}
            >
              <Tab
                label="Students"
                onClick={() => props.goStudents()}
                tabIndex="1"
              />
              <Tab
                label="Courses"
                onClick={() => props.goCourses()}
                tabIndex="2"
              />
            </Tabs>
          </Paper>
        </Toolbar>
      </AppBar>
      {props.students.map(student => (
        <div
          className={classes.content}
          key={student.id}
          display="flex"
          flexdirection="vertical"
          maxwidth="200"
        >
          <List>
            <ListItem button>
              <ListItemText
                primary={student.name}
                onClick={() => {
                  expand(student.id);
                }}
              />
            </ListItem>
            <ListItem>
              <Button size="small" onClick={() => props.remove(student.id)}>
                Remove
              </Button>
            </ListItem>
          </List>
          <Divider />
        </div>
      ))}
      <List className={classes.content}>
        <ListItem>
          <ListItemText primary="New Course" />
        </ListItem>
        <ListItem>
          <Button size="small" onClick={() => addStudent()}>
            Add Student
          </Button>
        </ListItem>
      </List>
    </div>
  );
}
