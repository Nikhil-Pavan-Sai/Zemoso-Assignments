import React, { useState, useEffect } from "react";
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

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1
  },
  card: {
    maxWidth: 300,
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
  const [state, setState] = useState({ home: false });
  const [user, setUser] = localStorage.getItem("user");
  const [values, setValues] = React.useState({
    age: "",
    name: "hai"
  });
  const [expand, setExpand] = React.useState([
    {
      title: "React JS",
      subTitle: "14 September 2019",
      overview:
        "This impressive paella is a perfect party dish and a fun meal tocook together with your guests. Add 1 cup of frozen peas alongwith the mussels, if you like",
      content:
        "In a typical Redux app, there is just a single store with a single root reducing function. As your app grows, you split the root reducer into smaller reducers independently operating on the different parts of the state tree. This is exactly like how there is just one root component in a React app, but it is composed out of many small components.",
      isExanded: false
    },
    {
      title: "Spring Boot",
      subTitle: "02 October 2019",
      overview: "Hey this is super freaking awesome.",
      content:
        "In a typical Redux app, there is just a single store with a single root reducing function. As your app grows, you split the root reducer into smaller reducers independently operating on the different parts of the state tree. This is exactly like how there is just one root component in a React app, but it is composed out of many small components.",
      isExanded: false
    }
  ]);
  const handleChange = event => {
    setValues(oldValues => ({
      ...oldValues,
      [event.target.name]: event.target.value
    }));
  };

  const handleExpandClick = idx => {
    setExpand([
      ...expand.slice(0, idx),
      {
        title: expand[idx].title,
        subTitle: expand[idx].subTitle,
        overview: expand[idx].overview,
        content: expand[idx].content,
        isExpanded: !expand[idx].isExpanded
      },
      ...expand.slice(idx + 1)
    ]);
    console.log(expand);
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
              onClick={() => setState({ ...state, home: true })}
            >
              Logout
            </Button>
          </Toolbar>
        </AppBar>
        <div className={classes.formControl}>
          <FormControl>
            <InputLabel htmlFor="age-simple">Assignment</InputLabel>
            <Select
              value={values.age}
              onChange={handleChange}
              inputProps={{
                name: "age",
                id: "age-simple"
              }}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              <MenuItem value={1}>Spring Boot</MenuItem>
              <MenuItem value={2}>Hibernate</MenuItem>
              <MenuItem value={3}>Reatc JS</MenuItem>
              <MenuItem value={4}>HTML</MenuItem>
              <MenuItem value={5}>Software Engineering</MenuItem>
            </Select>
          </FormControl>
          <br />
          <br />
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
          >
            Assign
          </Button>
        </div>
        <div className={classes.divClass}>
          {expand.map((x, idx) => (
            <Card className={classes.card} key={idx}>
              <CardHeader title={x.title} subheader={x.subTitle} />

              <CardContent>
                <Typography variant="body2" color="textSecondary" component="p">
                  {x.overview}
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
                  <Typography paragraph>{x.content}</Typography>
                </CardContent>
              </Collapse>
            </Card>
          ))}
        </div>
      </div>
    );
  }
  return <Login goBack={() => setState({ ...state, home: false })} />;
}
