import React, { useState } from "react";
import "../App.css";
import "../Security/Login.css";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import {
  DialogTitle,
  DialogContent,
  DialogActions,
  List,
  ListItem,
  ListItemText,
  Divider,
  makeStyles
} from "@material-ui/core";
import Background from "../res/cool-background.png";
import App from "../App";
import log from "loglevel";

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
  Login: {
    padding: "30",
    textAlign: "center"
  }
});

export default function RegisteredCourses(props) {
  async function removeCourseFromStudent(stId, coId) {
    fetch("students/" + stId + "/courses/" + coId, { method: "DELETE" });
    const isNotId = item => item.id !== coId;
    const newCourses = props.regCourses.filter(isNotId);
    setCourse(newCourses);
    setPageStatus("REGISTERED_COURSES");
  }
  const classes = useStyles();
  const [course, setCourse] = useState([]);
  const [pageStatus, setPageStatus] = useState(["REGISTERED_COURSES"]);
  log.info(course);

  if (pageStatus === "MAIN") {
    return <App />;
  }

  return (
    <div className={classes.bg}>
      <AppBar position="static">
        <Toolbar variant="dense">
          <Typography variant="h6" color="inherit" className={classes.title}>
            Registered Courses
          </Typography>
          <Button color="inherit" onClick={() => setPageStatus("MAIN")}>
            Home
          </Button>
        </Toolbar>
      </AppBar>
      <div>
        <div className={classes.Login}>
          <Dialog open={true}>
            <DialogTitle>{props.stu.name}</DialogTitle>
            <DialogContent dividers>
              {props.regCourses.length !== 0 ? (
                props.regCourses.map(stCourse => (
                  <List
                    key={stCourse.id}
                    component="nav"
                    aria-label="secondary mailbox folders"
                  >
                    <ListItem>
                      <ListItemText primary={stCourse.courseName} />
                      <Button
                        autoFocus
                        color="primary"
                        onClick={() => {
                          removeCourseFromStudent(props.stu.id, stCourse.id);
                        }}
                      >
                        Remove
                      </Button>
                    </ListItem>
                  </List>
                ))
              ) : (
                <List>
                  <ListItemText primary="No Courses assigned !" />
                </List>
              )}
              <Divider />
            </DialogContent>
            <DialogActions>
              <Button
                autoFocus
                color="primary"
                onClick={() => setPageStatus("MAIN")}
              >
                Close
              </Button>
            </DialogActions>
          </Dialog>
        </div>
      </div>
    </div>
  );
}
