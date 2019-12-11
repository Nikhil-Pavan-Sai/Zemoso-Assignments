import React, { useState } from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import NewLogin from "./NewLogin";
import Logo from "../res/logo.png";
import Background from "../res/cool-backgroundLogin.png";

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {"𝕮𝖔𝖕𝖞𝖗𝖎𝖌𝖍𝖙 © "}
      <Link color="inherit" href="https://github.com/Nikhil-Pavan-Sai">
        𝕹𝖎𝖐𝖍𝖎𝖑
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const useStyles = makeStyles(theme => ({
  "@global": {
    body: {
      backgroundColor: theme.palette.common.white
    }
  },
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
    marginTop: theme.spacing(3)
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

export default function NewRegister(props) {
  const classes = useStyles();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [state, setState] = useState({ login: false });

  function validateForm() {
    if (!email.includes(".com")) {
      alert("Please Enter a valid Email !");
    } else {
      addUser().then(props.registerUser());
    }
  }

  function addUser() {
    const response = fetch("/user/add", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password
      })
    }).then(alert("Successfully Registered !"));

    if (!email.includes(".com")) {
      alert("Please Enter a valid Email !");
    }
    response.json();
  }

  if (!state.login) {
    return (
      <div className={classes.bg} width="100%" height="100%">
        <Container component="main" maxWidth="xs">
          <h1 className={classes.heading}>𝖀𝖓𝖎𝖛𝖊𝖗𝖘𝖎𝖙𝖞 𝕺𝖋 𝕾𝖙𝖆𝖗𝖐𝖘</h1>
          <div className={classes.paper}>
            <img src={Logo} alt="Logo" width="200" height="200" />
            <Typography component="h1" variant="h5">
              𝕾𝖎𝖌𝖓 𝖀𝖕
            </Typography>
            <form className={classes.form} noValidate>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    autoComplete="fname"
                    name="firstName"
                    variant="outlined"
                    required
                    fullWidth
                    id="firstName"
                    label="𝕱𝖎𝖗𝖘𝖙 𝕹𝖆𝖒𝖊"
                    value={firstName}
                    onChange={event => setFirstName(event.target.value)}
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    variant="outlined"
                    required
                    fullWidth
                    id="lastName"
                    label="𝕷𝖆𝖘𝖙 𝕹𝖆𝖒𝖊"
                    name="lastName"
                    value={lastName}
                    onChange={event => setLastName(event.target.value)}
                    autoComplete="lname"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    variant="outlined"
                    required
                    fullWidth
                    id="email"
                    label="𝕰𝖒𝖆𝖎𝖑 𝕬𝖉𝖉𝖗𝖊𝖘𝖘"
                    name="email"
                    value={email}
                    onChange={event => setEmail(event.target.value)}
                    autoComplete="email"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    variant="outlined"
                    required
                    fullWidth
                    name="password"
                    label="𝕻𝖆𝖘𝖘𝖜𝖔𝖗𝖉"
                    type="password"
                    id="password"
                    value={password}
                    onChange={event => setPassword(event.target.value)}
                    autoComplete="current-password"
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                className={classes.submit}
                onClick={() => validateForm(props.registerUser)}
              >
                𝕾𝖎𝖌𝖓 𝖀𝖕
              </Button>
              <Grid container justify="flex-end">
                <Grid item>
                  <Link
                    href="#"
                    onClick={e => {
                      e.preventDefault();
                      setState({ ...state, login: true });
                    }}
                    variant="body2"
                  >
                    𝕬𝖑𝖗𝖊𝖆𝖉𝖞 𝖍𝖆𝖛𝖊 𝖆𝖓 𝖆𝖈𝖈𝖔𝖚𝖓𝖙? 𝕾𝖎𝖌𝖓 𝖎𝖓
                  </Link>
                </Grid>
              </Grid>
            </form>
          </div>
          <Box mt={5}>
            <Copyright />
          </Box>
        </Container>
      </div>
    );
  }
  return <NewLogin />;
}
