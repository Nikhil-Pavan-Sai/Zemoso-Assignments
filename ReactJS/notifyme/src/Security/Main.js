import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import App from "../App";
import ExpansionPanel from "@material-ui/core/ExpansionPanel";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1
  },
  menuButton: {
    marginRight: theme.spacing(2)
  },
  title: {
    flexGrow: 1
  },
  wid: {
    width: "25%",
    display: "flex",
    flexDirection: "row",
    flexWrap: "wrap",
    alignItems: "center",
    margin: "200"
  },
  divClass: {
    display: "flex",
    flexDirection: "row",
    flexWrap: "wrap",
    textAlign: "center"
  },
  centerContents: {
    textAlign: "center"
  }
}));

export default function Main(props) {
  const classes = useStyles();
  const [state, setState] = useState({ home: false });
  if (!state.home) {
    return (
      <div className={classes.root}>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6" className={classes.title}>
              Welcome {props.goHome} !
            </Typography>
            <Button
              color="inherit"
              onClick={() => setState({ ...state, home: true })}
            >
              Logout
            </Button>
          </Toolbar>
        </AppBar>
        <div className={classes.centerContents}>
          <ExpansionPanel className={classes.wid}>
            <ExpansionPanelSummary
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1a-content"
              id="panel1a-header"
            >
              <Typography className={classes.heading}>
                Sample React Placeholder
              </Typography>
            </ExpansionPanelSummary>
            <ExpansionPanelDetails>
              <Typography>
                Props are the React Function Component's parameters. Whereas the
                component can stay generic, we decide from the outside what it
                should render (or how it should behave). When rendering a
                component (e.g. Headline in App component), you can pass props
                as HTML attributes to the component. Then in the Function
                Component the props object is available as argument in the
                function signature.
              </Typography>
            </ExpansionPanelDetails>
          </ExpansionPanel>
          <ExpansionPanel className={classes.wid}>
            <ExpansionPanelSummary
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1a-content"
              id="panel1a-header"
            >
              <Typography className={classes.heading}>React hooks</Typography>
            </ExpansionPanelSummary>
            <ExpansionPanelDetails>
              <Typography>
                Every time incoming props or state of the component change, the
                component triggers a rerender to display the latest status quo
                which is often derived from the props and state. A render
                executes everything within the Function Component's body.
              </Typography>
            </ExpansionPanelDetails>
          </ExpansionPanel>
        </div>
      </div>
    );
  }
  return <App goBack={() => setState({ ...state, home: false })} />;
}
