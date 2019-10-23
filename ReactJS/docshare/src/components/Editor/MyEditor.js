import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { TextField, Container } from "@material-ui/core";
import MyToolbar from "/home/user/Documents/Assignments/ReactJS/docshare/src/components/ToolBar/MyToolbar.js";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";

const useStyles = makeStyles(theme => ({
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    minWidth: 1000,
    maxWidth: 1000,
    minHeight: 500,
    textAlign: "center"
  },
  root: {
    flexGrow: 1,
    textAlign: "center"
  }
}));

function MyEditor() {
  const classes = useStyles();
  const [values, setValues] = React.useState({
    multiline: ""
  });

  const handleChange = name => event => {
    setValues({ ...values, [name]: event.target.value });
  };

  return (
    <Container fixed>
      <div className={classes.root}>
        <AppBar position="absolute">
          <Toolbar variant="dense">
            <Typography variant="h6" color="inherit">
              Your own Rich Text editor
            </Typography>
          </Toolbar>
        </AppBar>
      </div>
      <Toolbar></Toolbar>
      <MyToolbar />
      <TextField
        id="standard-multiline-flexible"
        placeholder="Enter Some Text"
        multiline
        value={values.multiline}
        onChange={handleChange("multiline")}
        className={classes.textField}
        margin="normal"
      />
    </Container>
  );
}

export default MyEditor;
