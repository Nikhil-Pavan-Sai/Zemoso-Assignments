import React, { useState } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  makeStyles
} from "@material-ui/core";
import Dialog from "@material-ui/core/Dialog";
import Background from "../res/cool-background.png";
import { DialogTitle, DialogContent, DialogActions } from "@material-ui/core";
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

export default function ExpandedCourse(props) {
  const [pageStatus, setPageStatus] = useState("COURSE_EXPANDED");
  const classes = useStyles();

  if (pageStatus === "COURSES_CLICKED") {
    return <App />;
  }

  return (
    <div className={classes.bg}>
      <AppBar position="static">
        <Toolbar variant="dense">
          <Typography variant="h6" color="inherit" className={classes.title}>
            All Courses
          </Typography>
        </Toolbar>
      </AppBar>
      <div className={classes.Login}>
        <div>
          <Dialog open={true} fullScreen>
            <DialogTitle>{props.callBack.courseName}</DialogTitle>
            <DialogContent dividers>
              <Typography gutterBottom>
                {props.callBack.courseDescription}
              </Typography>
            </DialogContent>
            <DialogActions>
              <Button
                autoFocus
                color="primary"
                onClick={() => setPageStatus("COURSES_CLICKED")}
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
