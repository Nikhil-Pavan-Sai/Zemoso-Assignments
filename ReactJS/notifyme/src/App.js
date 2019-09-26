import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Main from "./Security/Main";

const useStyles = makeStyles(theme => ({
  menuButton: {
    marginRight: theme.spacing(2)
  },
  title: {
    flexGrow: 1
  },
  button: {
    margin: theme.spacing(1)
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200
  },
  heading: {
    fontSize: theme.typography.pxToRem(15),
    fontWeight: theme.typography.fontWeightRegular
  },
  center: {
    textAlign: "center"
  }
}));

function validate(name, password) {
  var defaultName = "Nikhil";
  var defaultPassword = "nikhil";
  if (defaultName == name && defaultPassword == password) {
    return true;
  } else return false;
}

export default function App() {
  const classes = useStyles();
  const [state, setState] = useState({ login: false });
  const [values, setValues] = React.useState({
    name: "",
    password: ""
  });
  const handleChange = name => event => {
    setValues({ ...values, [name]: event.target.value });
  };
  if (!state.login) {
    return (
      <div className={classes.title}>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6" className={classes.title}>
              NotifyMe
            </Typography>
          </Toolbar>
        </AppBar>

        <div className={classes.center}>
          <TextField
            id="standard-name"
            label="Name"
            className={classes.textField}
            value={values.name}
            onChange={handleChange("name")}
            margin="normal"
          />
          <br />
          <br />
          <TextField
            id="standard-password-input"
            label="Password"
            className={classes.textField}
            type="password"
            value={values.password}
            autoComplete="current-password"
            onChange={handleChange("password")}
            margin="normal"
          />
          <br />
          <br />
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
            onClick={() => {
              if (validate(values.name, values.password))
                setState({ ...state, login: true });
              else {
                setState({ ...state, login: false });
              }
            }}
          >
            Login
          </Button>
        </div>
      </div>
    );
  }
  return <Main goHome={values.name} />;
}
