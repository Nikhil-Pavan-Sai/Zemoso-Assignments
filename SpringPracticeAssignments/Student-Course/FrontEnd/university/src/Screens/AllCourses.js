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
import { AddCourse } from "./AddCourse";
import App from "../App";
import ExpandedCourse from "./ExpnadedCourse";

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

export default function AllCourses(props) {
  const classes = useStyles();
  const [newCourses, setNewCourses] = useState([]);
  const [value, setValue] = useState(1);
  const [pageStatus, setPageStatus] = useState("COURSES_CLICKED");

  function addCourse() {
    setPageStatus("ADD_COURSE");
  }

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  async function expand(id) {
    const courseInfo = await fetch("courses/" + id);
    const coInfo = await courseInfo.json();
    setNewCourses(coInfo);
    setPageStatus("COURSE_EXPANDED");
  }

  if (pageStatus === "COURSE_EXPANDED") {
    return <ExpandedCourse callBack={newCourses} />;
  }

  if (pageStatus === "ADD_COURSE") {
    return (
      <AddCourse
        callBack={(event, ret) => {
          let couList = [...props.courses];
          if ("id" in ret) couList = couList.concat(ret);
          setNewCourses(couList);
          setPageStatus("COURSES_CLICKED");
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
            All Courses
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
      {props.courses.map(course => (
        <div
          className={classes.content}
          key={course.id}
          display="flex"
          flexdirection="vertical"
          maxwidth="200"
        >
          <List>
            <ListItem button>
              <ListItemText
                primary={course.courseName}
                onClick={() => {
                  expand(course.id);
                }}
              />
            </ListItem>
            <ListItem>
              <Button size="small" onClick={() => props.remove(course.id)}>
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
          <Button size="small" onClick={() => addCourse()}>
            Add Course
          </Button>
        </ListItem>
      </List>
    </div>
  );
}
