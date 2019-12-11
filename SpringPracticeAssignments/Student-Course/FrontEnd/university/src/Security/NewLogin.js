import React, { useState } from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import NewRegister from "./NewRegister";
import Logo from "../res/logo.png";
import Background from "../res/cool-backgroundLogin.png";
import log from "loglevel";

const useStyles = makeStyles(theme => ({
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  },
  heading: {
    textAlign: "center",
    paddingTop: "30px"
  },
  bg: {
    backgroundImage: "url(" + Background + ")",
    height: "100%",
    width: "100%",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover"
  }
}));

export default function NewLogin(props) {
  const [uname, setUname] = useState("");
  const [pass, setPassword] = useState("");
  const [state, setState] = useState({ register: false });
  const [emailError, setEmailError] = useState("");
  const [passError, setPassError] = useState("");
  const classes = useStyles();

  function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"𝕮𝖔𝖕𝖞𝖗𝖎𝖌𝖍𝖙 © "}
        <Link
          color="inherit"
          href="https://github.com/Nikhil-Pavan-Sai/Zemoso-Assignments"
        >
          𝕹𝖎𝖐𝖍𝖎𝖑
        </Link>{" "}
        {new Date().getFullYear()}
        {"."}
      </Typography>
    );
  }

  function validate() {
    if (uname.includes(".com")) {
      const response = fetch("/user/validate", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          email: uname,
          password: pass
        })
      }).then(x => {
        if (x.status === 200 && props.callback) props.callback(x);
        else {
          setPassError("Invalid Email address or Password!");
          setEmailError(" ");
        }
      });
      log.info(response);
    }
    if (!uname.includes(".com")) {
      setEmailError("Invalid Email address or Password!");
    }
  }

  if (!state.register) {
    return (
      <div className={classes.bg} width="100%" height="100%">
        <Container component="main" maxWidth="xs">
          <h1 className={classes.heading}>𝖀𝖓𝖎𝖛𝖊𝖗𝖘𝖎𝖙𝖞 𝕺𝖋 𝕾𝖙𝖆𝖗𝖐𝖘</h1>
          <div className={classes.paper}>
            <img src={Logo} alt="Logo" width="200" height="200" />
            <Typography component="h1" variant="h5">
              𝕾𝖎𝖌𝖓 𝕴𝖓
            </Typography>
            <form className={classes.form}>
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="email"
                label="𝕰𝖒𝖆𝖎𝖑 𝕬𝖉𝖉𝖗𝖊𝖘𝖘"
                name="email"
                value={uname}
                error={emailError.length !== 0}
                helperText={emailError}
                onChange={event => setUname(event.target.value)}
                autoFocus
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                name="password"
                label="𝕻𝖆𝖘𝖘𝖜𝖔𝖗𝖉"
                type="password"
                id="password"
                error={passError.length !== 0}
                helperText={passError}
                value={pass}
                onChange={event => setPassword(event.target.value)}
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                className={classes.submit}
                onClick={e => {
                  e.preventDefault();
                  validate();
                }}
              >
                𝕾𝖎𝖌𝖓 𝕴𝖓
              </Button>
              <Grid container>
                <Grid item>
                  <Link
                    href="Register"
                    variant="body2"
                    onClick={e => {
                      e.preventDefault();
                      setState({ ...state, register: true });
                    }}
                  >
                    {"𝕯𝖔𝖓'𝖙 𝖍𝖆𝖛𝖊 𝖆𝖓 𝖆𝖈𝖈𝖔𝖚𝖓𝖙? 𝕾𝖎𝖌𝖓 𝖀𝖕"}
                  </Link>
                </Grid>
              </Grid>
            </form>
          </div>
          <Box mt={8}>
            <Copyright />
          </Box>
        </Container>
      </div>
    );
  }
  if (state.register) {
    return <NewRegister />;
  }
}
