import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Button from "@material-ui/core/Button";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import Login from "./Login";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import clsx from "clsx";
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import CardContent from "@material-ui/core/CardContent";
import CardActions from "@material-ui/core/CardActions";
import Collapse from "@material-ui/core/Collapse";
import IconButton from "@material-ui/core/IconButton";
import Typography from "@material-ui/core/Typography";
import Forms from "./BasicForm";

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1
  },
  card: {
    minWidth: 300,
    maxWidth: 300,
    minHeight: 250,
    margin: 30
  },
  menuButton: {
    marginRight: theme.spacing(2)
  },
  button: {
    marginRight: theme.spacing(1)
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
  },
  formControl: {
    margin: theme.spacing(1),
    textAlign: "center",
    flexWrap: "wrap"
  },
  expand: {
    transform: "rotate(0deg)",
    marginLeft: "auto",
    transition: theme.transitions.create("transform", {
      duration: theme.transitions.duration.shortest
    })
  },
  expandOpen: {
    transform: "rotate(180deg)"
  }
}));

export default function Main(props) {
  const classes = useStyles();
  const [state, setState] = useState({ home: false, add: false });
  const [values, setValues] = React.useState({
    name: ""
  });
  var cardsInfo = [10];
  const [expand, setExpand] = React.useState([]);
  const handleChange = event => {
    setValues(oldValues => ({
      ...oldValues,
      name: event.target.value
    }));
  };

  const handleExpandClick = idx => {
    setExpand([
      ...expand.slice(0, idx),
      {
        CourseName: expand[idx].CourseName,
        Date: expand[idx].Date,
        Overview: expand[idx].Overview,
        Content: expand[idx].Content,
        isExpanded: !expand[idx].isExpanded
      },
      ...expand.slice(idx + 1)
    ]);
  };

  if (!state.home) {
    return (
      <div className={classes.root}>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6" className={classes.root}>
              Welcome {props.goHome} !
            </Typography>
            <Button
              color="inherit"
              onClick={() => {
                setState({ ...state, home: true, add: true });
                localStorage.removeItem("user");
              }}
            >
              Logout
            </Button>
          </Toolbar>
        </AppBar>
        <div className={classes.formControl}>
          <div minWidth="100">
            <FormControl>
              <InputLabel htmlFor="age-simple">Task</InputLabel>
              <Select
                minWidth="100"
                value={values.name}
                onChange={handleChange}
                inputProps={{
                  name: "age",
                  id: "age-simple"
                }}
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                <MenuItem value={"Spring Boot"}>Spring Boot</MenuItem>
                <MenuItem value={"Hibernate"}>Hibernate</MenuItem>
                <MenuItem value={"Reatc JS"}>Reatc JS</MenuItem>Date
                <MenuItem value={"HTML"}>HTML</MenuItem>Date
                <MenuItem value={"Software Engineering"}>
                  Software Engineering
                </MenuItem>
                Date
              </Select>
            </FormControl>
          </div>
          <br />
          <br />
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
            onClick={() => {
              setState({ ...state, home: true });
            }}
          >
            Add
          </Button>
        </div>
        <div className={classes.divClass}>
          {expand.map((x, idx) => (
            <Card className={classes.card} key={idx}>
              <CardHeader title={x.CourseName} subheader={x.Date} />

              <CardContent>
                <Typography variant="body2" color="textSecondary" component="p">
                  {x.Overview}
                </Typography>
              </CardContent>
              <CardActions disableSpacing>
                <IconButton
                  className={clsx(classes.expand, {
                    [classes.expandOpen]: x.isExpanded
                  })}
                  onClick={event => {
                    handleExpandClick(idx);
                  }}
                  aria-expanded={x.isExpanded}
                  aria-label="show more"
                >
                  <ExpandMoreIcon />
                </IconButton>
              </CardActions>
              <Collapse in={x.isExpanded} timeout="auto" unmountOnExit>
                <CardContent>
                  <Typography paragraph>{x.Content}</Typography>
                </CardContent>
              </Collapse>
            </Card>
          ))}
        </div>
      </div>
    );
  }
  if (!state.add) {
    return (
      <Forms
        callback={(event, ret) => {
          setState({ home: false });
          if (ret) setExpand([...expand, ret]);
        }}
        fields={{
          Date: "text",
          Overview: "text",
          Content: "text"
        }}
        newProp={values.name}
      />
    );
  }
  return <Login goBack={() => setState({ ...state, home: false })} />;
}
