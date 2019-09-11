import React from "react";
import "./App.css";
import "./Security/Login.css";
import Login from "./Security/Login.js";
import AddCourse from "./AddCourse.js";
import Register from "./Security/Register.js";
import Forms from "./forms/Forms.js";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import AppBar from "material-ui/AppBar";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";

class App extends React.Component {
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
    this.changeRegisteredCourse = this.changeRegisteredCourse.bind(this);
    this.changeAddCourse = this.changeAddCourse.bind(this);
    this.changeHomeClicked = this.changeHomeClicked.bind(this);
    this.changeAddStudent = this.changeAddStudent.bind(this);
    this.changeAllCourses = this.changeAllCourses.bind(this);
    this.removeCourse = this.removeCourse.bind(this);
    this.removeStudent = this.removeStudent.bind(this);
    this.state = {
      isLoggedIn: false,
      isStudentsClicked: false,
      isCourseClicked: false,
      isExpandStudent: false,
      isRegisteredCourse: false,
      isAddStudent: false,
      isBackClicked: false,
      isAddCourse: false,
      allCourses: false,
      currId: null,
      students: [],
      courses: [],
      student_courses: []
    };
  }

  changeLogin() {
    localStorage.setItem("user", "true");
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
      isAddCourse: false,
      isRegisteredCourse: false,
      isExpandStudent: false,
      isCourseClicked: false,
      isStudentsClicked: false
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

  changeAddCourse(id) {
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
      isStudentsClicked: false
    });
  }

  changeAllCourses() {
    this.setState({
      allCourses: true,
      isLoggedIn: true,
      isAddCourse: false,
      isRegisteredCourse: false,
      isExpandStudent: false,
      isCourseClicked: false,
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
    console.log(this.state.isAddCourse);
    if (!this.state.user) return <Login callback={this.changeLogin} />;

    if (this.state.isAddCourse)
      return (
        <AddCourse
          id={this.state.currId}
          callback={() => {
            this.setState({ isAddCourse: false });
          }}
        />
      );

    if (this.state.isAddStudent)
      return (
        <Forms
          callback={event => {
            event.preventDefault();
            this.setState({
              isAddStudent: false,
              isLoggedIn: true,
              isStudentsClicked: true
            });
          }}
          submiturl="/students"
          fields={{ name: "text", standard: "text", branch: "text" }}
        />
      );

    if (this.state.allCourses)
      return (
        <Forms
          callback={event => {
            event.preventDefault();
            this.setState({ allCourses: false, isLoggedIn: true });
          }}
          submiturl="/courses"
          fields={{ courseName: "text" }}
        />
      );

    if (this.state.isStudentsClicked) {
      return (
        <div className="App">
          <div>
            <button
              onClick={() => {
                this.changeHomeClicked();
              }}
            >
              {" "}
              Home{" "}
            </button>
            <br />
            <br />
          </div>
          <div>
            {this.state.students.map(student => (
              <div key={student.id}>
                {" "}
                <Button
                  block
                  bsSize="large"
                  onClick={() => {
                    this.expandStudent(student.id);
                  }}
                >
                  {" "}
                  {student.name}
                </Button>
                <button
                  className="Side"
                  onClick={event => {
                    this.removeStudent(student.id, event);
                  }}
                >
                  Remove
                </button>
                <br />
                <br />
              </div>
            ))}
          </div>
          <div>
            <br />
            <button
              onClick={() => {
                this.changeAddStudent();
              }}
            >
              {" "}
              Add Students{" "}
            </button>
            <br />
          </div>
        </div>
      );
    }

    if (this.state.isCourseClicked) {
      return (
        /*<div>
            {this.state.courses.map(course => (
              <div key={course.id}>
                <div> {course.courseName} </div>
                <button
                  className="Side"
                  onClick={event => {
                    this.removeCourse(course.id, event);
                  }}
                >
                  Remove
                </button>
                <br />
                <br />
              </div>
            ))}
          </div>
          <div>
            <br />
            <button
              onClick={() => {
                this.changeAllCourses();
              }}
            >
              {" "}
              Add Course{" "}
            </button>
            <br />
          </div>*/
        <div>
          <MuiThemeProvider>
            <div>
              <AppBar title="htfghk" />
              <div className="Login">
                <RaisedButton
                  label="Home"
                  primary={true}
                  style={style}
                  onClick={() => this.changeHomeClicked()}
                />
                <br />
                <br />
                <div>
                  {this.state.courses.map(course => (
                    <div key={course.id}>
                      <div> {course.courseName} </div>
                      <div>
                        <RaisedButton
                          label="Remove"
                          primary={true}
                          style={style}
                          onClick={() => this.removeCourse(course.id)}
                        />
                      </div>
                      <br />
                      <div>
                        <RaisedButton
                          label="Add Course"
                          primary={true}
                          style={style}
                          onClick={() => this.changeAllCourses()}
                        />
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </MuiThemeProvider>
        </div>
      );
    }

    if (this.state.isExpandStudent) {
      return (
        <div>
          <MuiThemeProvider>
            <div>
              <AppBar title="Student Info" />
              <div className="Login">
                <RaisedButton
                  label="Home"
                  primary={true}
                  style={style}
                  onClick={() => this.changeHomeClicked()}
                />
                <br />
                {this.state.students.map(student => (
                  <div key={student.id}>
                    <div>
                      <h3> {student.name} </h3>
                      <p> Branch: {student.branch} </p>
                      <p> Standard: {student.standard} </p>
                      <RaisedButton
                        label="Registered Courses"
                        primary={true}
                        style={style}
                        onClick={() => this.changeRegisteredCourse(student.id)}
                      />
                      <br />
                      <RaisedButton
                        label="Add Courses"
                        primary={true}
                        style={style}
                        onClick={() => this.changeAddCourse(student.id)}
                      />
                      <br />
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </MuiThemeProvider>
        </div>
      );
    }

    if (this.state.isRegisteredCourse) {
      return (
        <div>
          <MuiThemeProvider>
            <div>
              <AppBar title="Registered Courses" />
              <div className="Login">
                <RaisedButton
                  label="Home"
                  primary={true}
                  style={style}
                  onClick={() => this.changeHomeClicked()}
                />
                <br />
                <div>
                  {this.state.student_courses.map(stCourse => (
                    <div>
                      {stCourse.courseName}
                      <br />
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </MuiThemeProvider>
        </div>
      );
    }

    return (
      <div>
        <MuiThemeProvider>
          <div>
            <AppBar title="University Of Starks" />
            <div className="Login">
              <RaisedButton
                label="Students"
                primary={true}
                style={style}
                onClick={() => this.changeStudentsClicked()}
              />
              <RaisedButton
                label="Courses"
                primary={true}
                style={style}
                onClick={() => this.changeCourseClicked()}
              />
              <br />
              <RaisedButton
                label="Logout"
                primary={true}
                style={style}
                onClick={() => {
                  localStorage.removeItem("user");
                  this.setState({ user: null });
                }}
              />
            </div>
          </div>
        </MuiThemeProvider>
      </div>
    );
  }
}
const style = {
  margin: 15
};
export default App;
