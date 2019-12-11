import React from "react";
import "../App.css";
import "../Security/Login.css";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import AddCourseToStudent from "../AddCourseToStudent";
import { AddStudent } from "../AddStudent";
import { AddCourse } from "../AddCourse";
import NewLogin from "../Security/NewLogin";
import Main from "../Main";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import CardActions from "@material-ui/core/CardActions";
import Dialog from "@material-ui/core/Dialog";
import {
  DialogTitle,
  DialogContent,
  DialogActions,
  List,
  ListItem,
  ListItemText,
  Divider,
  Tab,
  Paper,
  Tabs
} from "@material-ui/core";

class LegacyApp extends React.Component {
  async componentDidMount() {
    const user = localStorage.getItem("user");
    const response = await fetch("/students");
    const course_response = await fetch("/courses");

    const body = await response.json();
    const course_body = await course_response.json();

    this.setState({ user: user });
    this.setState({ students: body });
    this.setState({ courses: course_body });
  }

  constructor(props) {
    super(props);
    this.onChange = this.onChange.bind(this);
    this.changeLogin = this.changeLogin.bind(this);
    this.changeStudentsClicked = this.changeStudentsClicked.bind(this);
    this.changeCourseClicked = this.changeCourseClicked.bind(this);
    this.expandStudent = this.expandStudent.bind(this);
    this.expandCourse = this.expandCourse.bind(this);
    this.changeRegisteredCourse = this.changeRegisteredCourse.bind(this);
    this.changeAddCourseToStudent = this.changeAddCourseToStudent.bind(this);
    this.changeHomeClicked = this.changeHomeClicked.bind(this);
    this.changeAddStudent = this.changeAddStudent.bind(this);
    this.changeAddCourse = this.changeAddCourse.bind(this);
    this.removeCourse = this.removeCourse.bind(this);
    this.removeStudent = this.removeStudent.bind(this);
    this.removeCourseFromStudent = this.removeCourseFromStudent.bind(this);
    this.state = {
      isLoggedIn: false,
      isStudentsClicked: false,
      isCourseClicked: false,
      isExpandStudent: false,
      isExpandCourse: false,
      isRegisteredCourse: false,
      isAddStudent: false,
      isAddCourse: false,
      addCourse: false,
      currId: null,
      courseId: null,
      students: [],
      courses: [],
      student_courses: [],
      userName: localStorage.getItem("uname")
    };
  }

  async changeLogin(x) {
    localStorage.setItem("user", "true");
    let data = await x.json();
    this.setState({ userName: data.firstName });
    localStorage.setItem("uname", data.firstName);
    this.setState({ isLoggedIn: true, isRegistered: true, user: "true" });
  }

  changeStudentsClicked() {
    this.setState({ isStudentsClicked: true });
  }

  changeCourseClicked() {
    this.setState({ isCourseClicked: true });
  }

  async changeHomeClicked() {
    const response = await fetch("/students");
    const body = await response.json();
    this.setState({ students: body });
    this.setState({
      isLoggedIn: true,
      isStudentsClicked: false,
      isCourseClicked: false,
      isExpandStudent: false,
      isExpandCourse: false,
      isRegisteredCourse: false,
      isAddStudent: false,
      isAddCourse: false,
      addCourse: false
    });
  }

  async expandStudent(id) {
    this.setState({
      isExpandStudent: true,
      isCourseClicked: false,
      isStudentsClicked: false
    });
    const studentInfo = await fetch("students/" + id);
    const stInfo = await studentInfo.json();
    this.setState({ students: [stInfo] });
  }

  async expandCourse(id) {
    this.setState({
      isExpandCourse: true,
      isCourseClicked: false,
      isStudentsClicked: false
    });
    const courseInfo = await fetch("courses/" + id);
    const coInfo = await courseInfo.json();
    this.setState({ courses: [coInfo] });
  }

  async changeRegisteredCourse(id) {
    this.setState({
      isRegisteredCourse: true,
      isExpandStudent: false,
      isCourseClicked: false,
      isStudentsClicked: false
    });
    const data = await fetch("students/" + id + "/courses");
    const st_course = await data.json();
    this.setState({ student_courses: st_course });
  }

  changeAddCourseToStudent(id) {
    this.setState({
      isAddCourse: true,
      isRegisteredCourse: false,
      isExpandStudent: false,
      isCourseClicked: false,
      isStudentsClicked: false,
      currId: id
    });
  }

  changeAddStudent() {
    this.setState({
      isAddStudent: true,
      isAddCourse: false,
      isRegisteredCourse: false,
      isExpandStudent: false,
      isCourseClicked: false,
      isStudentsClicked: true
    });
  }

  changeAddCourse() {
    this.setState({
      addCourse: true,
      isLoggedIn: true,
      isAddCourse: false,
      isRegisteredCourse: false,
      isExpandStudent: false,
      isCourseClicked: true,
      isStudentsClicked: false
    });
  }

  removeCourse(id) {
    fetch("/courses/" + id, { method: "DELETE" });
    const isNotId = item => item.id !== id;
    const newCourses = this.state.courses.filter(isNotId);
    this.setState({
      courses: newCourses,
      isLoggedIn: true,
      isAddCourse: false,
      isRegisteredCourse: false,
      isExpandStudent: false,
      isCourseClicked: true,
      isStudentsClicked: false
    });
  }

  removeCourseFromStudent(stId, id) {
    fetch("students/" + stId + "/courses/" + id, { method: "DELETE" });
    const isNotId = item => item.id !== id;
    const newCourses = this.state.courses.filter(isNotId);
    this.setState({
      student_courses: newCourses,
      isLoggedIn: true,
      isAddCourse: false,
      isRegisteredCourse: false,
      isExpandStudent: true,
      isCourseClicked: false,
      isStudentsClicked: false
    });
  }

  removeStudent(id, event) {
    fetch("/students/" + id, { method: "DELETE" });
    const isNotId = item => item.id !== id;
    const newStudents = this.state.students.filter(isNotId);
    this.setState({
      students: newStudents,
      isLoggedIn: true,
      isAddCourse: false,
      isRegisteredCourse: false,
      isExpandStudent: false,
      isCourseClicked: false,
      isStudentsClicked: true
    });
  }

  onChange(e) {
    this.setState({
      name: e.target.value,
      branch: e.target.value,
      standard: e.target.value,
      courseName: e.target.value
    });
  }

  render() {
    if (!this.state.user) return <NewLogin callback={this.changeLogin} />;

    if (this.state.isAddCourse)
      return (
        <AddCourseToStudent
          id={this.state.currId}
          callback={() => {
            this.setState({ isAddCourse: false });
          }}
          goHome={() => this.changeHomeClicked()}
        />
      );

    if (this.state.isAddStudent)
      return (
        <AddStudent
          callBack={(event, ret) => {
            let stuList = this.state.students;
            if ("id" in ret) stuList = stuList.concat(ret);
            this.setState({
              isAddStudent: false,
              isLoggedIn: true,
              students: stuList
            });
          }}
          goHome={() => this.changeHomeClicked()}
          goStudent={() => this.changeStudentsClicked()}
          goCourse={() => this.changeCourseClicked()}
        />
      );

    if (this.state.addCourse)
      return (
        <AddCourse
          callBack={(event, ret) => {
            let couList = this.state.courses;
            if ("id" in ret) couList = couList.concat(ret);
            this.setState({
              addCourse: false,
              isLoggedIn: true,
              courses: couList
            });
          }}
          goHome={() => this.changeHomeClicked()}
          goStudent={() => this.changeStudentsClicked()}
          goCourse={() => this.changeCourseClicked()}
        />
      );

    if (this.state.isStudentsClicked) {
      return (
        <div className="bg">
          <AppBar position="static">
            <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" className="title">
                All Students
              </Typography>
              <Button color="inherit" onClick={() => this.changeHomeClicked()}>
                Home
              </Button>
            </Toolbar>
          </AppBar>
          <AppBar position="static">
            <Toolbar variant="dense">
              <Paper className="title">
                <Tabs indicatorColor="primary" textColor="primary" centered>
                  <Tab
                    label="Students"
                    onClick={() => this.changeStudentsClicked()}
                  />
                  <Tab
                    label="Courses"
                    onClick={() => this.changeCourseClicked()}
                  />
                </Tabs>
              </Paper>
            </Toolbar>
          </AppBar>
          <div className="divClass">
            {this.state.students.map(student => (
              <div key={student.id} className="card">
                {" "}
                <div>
                  <Card className="card">
                    <CardContent>
                      <Button
                        className="title"
                        style={style}
                        onClick={() => {
                          this.expandStudent(student.id);
                        }}
                      >
                        {student.name}
                      </Button>
                    </CardContent>
                    <CardActions>
                      <Button
                        size="small"
                        style={style}
                        onClick={() => this.removeStudent(student.id)}
                      >
                        Remove
                      </Button>
                    </CardActions>
                  </Card>
                </div>
              </div>
            ))}

            <Card>
              <CardContent>New</CardContent>
              <CardActions>
                <Button
                  size="small"
                  style={style}
                  onClick={() => this.changeAddStudent()}
                >
                  Add Student
                </Button>
              </CardActions>
            </Card>
          </div>
        </div>
      );
    }

    if (this.state.isCourseClicked) {
      return (
        <div className="bg">
          <AppBar position="static">
            <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" className="title">
                All Courses
              </Typography>
              <Button color="inherit" onClick={() => this.changeHomeClicked()}>
                Home
              </Button>
            </Toolbar>
          </AppBar>
          <AppBar position="static">
            <Toolbar variant="dense">
              <Paper className="title">
                <Tabs indicatorColor="primary" textColor="primary" centered>
                  <Tab
                    label="Students"
                    onClick={() => this.changeStudentsClicked()}
                  />
                  <Tab
                    label="Courses"
                    onClick={() => this.changeCourseClicked()}
                  />
                </Tabs>
              </Paper>
            </Toolbar>
          </AppBar>
          <div className="divClass">
            {this.state.courses.map(course => (
              <div key={course.id}>
                {" "}
                <div className="card">
                  <Card className="card">
                    <CardContent>
                      <Button
                        className="title"
                        style={style}
                        onClick={() => {
                          this.expandCourse(course.id);
                        }}
                      >
                        {course.courseName}
                      </Button>
                    </CardContent>
                    <CardActions>
                      <Button
                        size="small"
                        style={style}
                        onClick={() => this.removeCourse(course.id)}
                      >
                        Remove
                      </Button>
                    </CardActions>
                  </Card>
                </div>
              </div>
            ))}
            <Card className="card">
              <CardContent>New</CardContent>
              <CardActions>
                <Button
                  size="small"
                  style={style}
                  onClick={() => this.changeAddCourse()}
                >
                  Add Course
                </Button>
              </CardActions>
            </Card>
          </div>
        </div>
      );
    }

    if (this.state.isExpandStudent) {
      return (
        <div className="bg">
          <AppBar position="static">
            <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" className="title">
                All Students
              </Typography>
            </Toolbar>
          </AppBar>
          <div className="Login">
            {this.state.students.map(student => (
              <div key={student.id}>
                <Dialog open={true}>
                  <DialogTitle>{student.name}</DialogTitle>
                  <DialogContent dividers>
                    <Typography gutterBottom>
                      Branch: {student.branch}
                    </Typography>
                    <Typography gutterBottom>
                      Standard: {student.standard}
                    </Typography>
                    <Typography gutterBottom>College: BVRIT</Typography>
                    <Typography gutterBottom>
                      Address: 4/12-A/C, Hyderabad, Telanagana.
                    </Typography>
                    <Typography gutterBottom>
                      Contact: {Math.floor(10000000 + Math.random() * 90000000)}
                    </Typography>
                  </DialogContent>
                  <DialogActions>
                    <Button
                      autoFocus
                      color="primary"
                      onClick={() => this.changeRegisteredCourse(student.id)}
                    >
                      Registered Courses
                    </Button>
                    <Button
                      autoFocus
                      color="primary"
                      onClick={() => this.changeAddCourseToStudent(student.id)}
                    >
                      Add Course
                    </Button>
                    <Button
                      autoFocus
                      color="primary"
                      onClick={() => this.changeStudentsClicked()}
                    >
                      Close
                    </Button>
                  </DialogActions>
                </Dialog>
              </div>
            ))}
          </div>
        </div>
      );
    }

    if (this.state.isExpandCourse) {
      return (
        <div className="bg">
          <AppBar position="static">
            <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" className="title">
                All Courses
              </Typography>
            </Toolbar>
          </AppBar>
          <div className="Login">
            {this.state.courses.map(course => (
              <div key={course.id}>
                <Dialog open={true}>
                  <DialogTitle>{course.courseName}</DialogTitle>
                  <DialogContent dividers>
                    <Typography gutterBottom>
                      {course.courseDescription}
                    </Typography>
                  </DialogContent>
                  <DialogActions>
                    <Button
                      autoFocus
                      color="primary"
                      onClick={() => this.changeCourseClicked()}
                    >
                      Close
                    </Button>
                  </DialogActions>
                </Dialog>
              </div>
            ))}
          </div>
        </div>
      );
    }

    if (this.state.isRegisteredCourse) {
      return (
        <div className="bg">
          <AppBar position="static">
            <Toolbar variant="dense">
              <Typography variant="h6" color="inherit" className="title">
                Registered Courses
              </Typography>
              <Button color="inherit" onClick={() => this.changeHomeClicked()}>
                Home
              </Button>
            </Toolbar>
          </AppBar>
          {this.state.students.map(student => (
            <div key={student.id}>
              <div className="Login">
                {/* {this.state.student_courses.map(stCourse => ( */}
                <Dialog open={true}>
                  <DialogTitle>{student.name}</DialogTitle>
                  <DialogContent dividers>
                    {this.state.student_courses.length !== 0 ? (
                      this.state.student_courses.map(stCourse => (
                        <List
                          component="nav"
                          aria-label="secondary mailbox folders"
                        >
                          <ListItem>
                            <ListItemText primary={stCourse.courseName} />
                            <Button
                              autoFocus
                              color="primary"
                              onClick={() =>
                                this.removeCourseFromStudent(
                                  student.id,
                                  stCourse.id
                                )
                              }
                            >
                              Remove
                            </Button>
                          </ListItem>
                        </List>
                      ))
                    ) : (
                      <List>
                        <ListItemText primary="No Courses assigned !" />
                      </List>
                    )}
                    <Divider />
                  </DialogContent>
                  <DialogActions>
                    <Button
                      autoFocus
                      color="primary"
                      onClick={() => this.changeHomeClicked()}
                    >
                      Close
                    </Button>
                  </DialogActions>
                </Dialog>
              </div>
            </div>
          ))}
        </div>
      );
    }

    return (
      <Main
        res_student={this.changeStudentsClicked}
        res_course={this.changeCourseClicked}
        onLogout={() => this.setState({ user: null })}
        zoro={this.state.userName}
      />
    );
  }
}
const style = {
  margin: 15
};

export default LegacyApp;
