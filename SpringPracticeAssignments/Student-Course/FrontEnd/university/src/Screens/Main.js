import React from "react";
import Button from "@material-ui/core/Button";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";
import { makeStyles } from "@material-ui/core/styles";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogTitle from "@material-ui/core/DialogTitle";
import { Tab, Paper, Tabs } from "@material-ui/core";
import Logo from "../res/cool-background.png";
import brain from "../res/brain.jpg";

export default function Main(props) {
  const useStyles = makeStyles(theme => ({
    heightOf: {
      height: "70px"
    },
    menuButton: {
      marginRight: theme.spacing(2)
    },
    hide: {
      display: "none"
    },
    title: {
      flexGrow: 1
    },
    bg: {
      backgroundImage: "url(" + Logo + ")",
      height: "100vh",
      backgroundPosition: "center",
      backgroundRepeat: "no-repeat",
      backgroundSize: "cover"
    },
    content: {
      color: "#FFFFFF",
      textAlign: "center"
    }
  }));

  const classes = useStyles();
  const [value, setValue] = React.useState(0);
  const [state, setState] = React.useState({
    open: false
  });

  const handleClickOpen = () => {
    setState({ ...state, open: true });
  };

  const handleClose = () => {
    setState({ ...state, open: false });
  };

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className={classes.bg}>
      <AppBar position="static" className={classes.heightOf}>
        <Toolbar variant="dense">
          <Typography variant="h6" color="inherit" className={classes.title}>
            University Of Starks
          </Typography>
          <Button color="inherit" onClick={handleClickOpen}>
            {"Hey, " + props.zoro + "!"}
          </Button>
        </Toolbar>
      </AppBar>

      <Dialog
        disableBackdropClick
        disableEscapeKeyDown
        open={state.open}
        onClose={handleClose}
      >
        <DialogTitle>Are you sure, Logout?</DialogTitle>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button
            onClick={() => {
              localStorage.removeItem("user");
              props.onLogout();
              setState({ open: false });
            }}
            color="primary"
          >
            Ok
          </Button>
        </DialogActions>
      </Dialog>
      <AppBar position="static">
        <Toolbar variant="dense">
          <Paper className={classes.title}>
            <Tabs
              value={value}
              onChange={handleChange}
              indicatorColor="primary"
              textColor="primary"
              centered
            >
              <Tab label="Students" onClick={() => props.res_student()} />
              <Tab label="Courses" onClick={() => props.res_course()} />
            </Tabs>
          </Paper>
        </Toolbar>
      </AppBar>
      <div className={classes.content}>
        <img src={brain} alt="brain" width="150" height="150" />
        <h2>A Place for Curiosity</h2>
        <h5>
          Ducks dive into research to find answers to some of humanity’s biggest
          questions. We’re using big data to save and improve lives, striving to
          predict earthquakes and prevent deadly disease. We’re combining
          expertise across disciplines in math, biology, and genomics with
          bioengineering, neuroengineering, and precision medicine to fight
          cancer. We’re re-defining research education with the Phil and Penny
          Knight Campus for Accelerating Scientific Impact, shaping the next
          generation of scientists, innovators, and entrepreneurs, ready to
          serve the state, nation, and world with discovery and impact.
        </h5>
      </div>
    </div>
  );
}
