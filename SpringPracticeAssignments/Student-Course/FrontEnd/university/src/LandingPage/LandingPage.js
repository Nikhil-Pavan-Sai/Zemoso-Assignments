import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { AppBar, Toolbar, Typography } from "@material-ui/core";
import Background from "../res/cool-background.png";

const useStyles = makeStyles(theme => ({
  image: {
    backgroundSize: "cover",
    width: "100%",
    height: "100%"
  }
}));

export default function LandingPage() {
  const classes = useStyles();
  return (
    <div>
      <AppBar position="static">
        <Toolbar variant="dense">
          <Typography variant="h6" color="inherit" className="title">
            University of Starks
          </Typography>
        </Toolbar>
      </AppBar>
      <div className={classes.image}>
        <img className={classes.image} src={"../res/cool-background.png"} />
      </div>
    </div>
  );
}
