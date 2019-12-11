import React, { useState } from "react";
import { Typography, Button, makeStyles } from "@material-ui/core";
import Dialog from "@material-ui/core/Dialog";
import Background from "../res/cool-background.png";
import { DialogTitle, DialogContent, DialogActions } from "@material-ui/core";
import RegisteredCourses from "./RegisteredCourses";
import NewAddCourseToStudent from "./NewAddCourseToStudent";
import App from "../App";

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

export default function ExpandedStudent(props) {
  const [pageStatus, setPageStatus] = useState("STUDENT_EXPANDED");
  const [regCour, setRegCour] = useState([]);

  async function changeRegisteredCourse(id) {
    const data = await fetch("students/" + id + "/courses");
    const st_course = await data.json();
    setRegCour(st_course);
    setPageStatus("REGISTERED_COURSES");
  }

  const classes = useStyles();

  if (pageStatus === "REGISTERED_COURSES") {
    return <RegisteredCourses stu={props.callback} regCourses={regCour} />;
  }

  if (pageStatus === "ADD_COURSE_TO_STUDENT") {
    return <NewAddCourseToStudent id={props.callback.id} />;
  }

  if (pageStatus === "STUDENT_CLICKED") {
    return <App />;
  }

  return (
    <div className={classes.bg}>
      <Dialog open={true} fullScreen className={classes.bg}>
        <DialogTitle>{props.callback.name}</DialogTitle>
        <DialogContent dividers>
          <Typography gutterBottom>Branch: {props.callback.branch}</Typography>
          <Typography gutterBottom>
            Standard: {props.callback.standard}
          </Typography>
          <Typography gutterBottom>College: BVRIT</Typography>
          <Typography gutterBottom>
            Address: 4/12-A/C, Hyderabad, Telanagana.
          </Typography>
          <Typography gutterBottom>
            Contact: {Math.floor(1000000000 + Math.random() * 9000000000)}
          </Typography>
        </DialogContent>
        <DialogActions>
          <Button
            autoFocus
            color="primary"
            onClick={() => changeRegisteredCourse(props.callback.id)}
          >
            Registered Courses
          </Button>
          <Button
            autoFocus
            color="primary"
            onClick={() => setPageStatus("ADD_COURSE_TO_STUDENT")}
          >
            Add Course
          </Button>
          <Button
            autoFocus
            color="primary"
            onClick={() => setPageStatus("STUDENT_CLICKED")}
          >
            Close
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
