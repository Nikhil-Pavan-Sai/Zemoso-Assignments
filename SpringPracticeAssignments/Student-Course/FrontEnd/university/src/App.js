import React from 'react';
import './App.css';
import Login from './Security/Login.js';
import Register from './Security/Register.js'
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";

class App extends React.Component{

async componentDidMount()
{
    const response=await fetch("/students")
    const course_response=await fetch("/courses")

    const body=await response.json()
    const course_body=await course_response.json()

    this.setState({students:body})
    this.setState({courses:course_body})
}

constructor(props)
{
    super(props)
    this.onChange=this.onChange.bind(this)
    this.changeLogin=this.changeLogin.bind(this)
    this.changeStudentsClicked=this.changeStudentsClicked.bind(this)
    this.changeCourseClicked=this.changeCourseClicked.bind(this)
    this.expandStudent=this.expandStudent.bind(this)
    this.changeRegisteredCourse=this.changeRegisteredCourse.bind(this)
    this.state={
        isLoggedIn:false,
        isStudentsClicked:false,
        isCourseClicked:false,
        isExpandStudent:false,
        isRegisteredCourse:false,
        students:[],
        courses:[],
        student_courses:[],
    }
}

changeLogin()
{
    this.setState({isLoggedIn:true, isRegistered:true})
}


changeStudentsClicked()
{
    this.setState({isStudentsClicked:true})
}

changeCourseClicked()
{
    this.setState({isCourseClicked:true})
}

async expandStudent(id)
{
    this.setState({isExpandStudent:true, isCourseClicked:false, isStudentsClicked:false})
    const studentInfo=await fetch("students/"+id)
    const stInfo=await studentInfo.json()
    this.setState({students:[stInfo]})
}

async changeRegisteredCourse(id)
{
    this.setState({isRegisteredCourse:true, isExpandStudent:false, isCourseClicked:false, isStudentsClicked:false})
    const data=await fetch("students/"+id+"/courses")
    const st_course=await data.json()
    this.setState({student_courses:st_course})
}

onChange(e)
{
    this.setState({
        name: e.target.value,
        branch: e.target.value,
        standard:e.target.value,
        courseName:e.target.value
    })
}

render(){
   if(!this.state.isLoggedIn)
        return(<Login callback={this.changeLogin}/>)


  if(this.state.isStudentsClicked){
      return (
        <div className="App">
                <div>
                {
                    this.state.students.map(
                    student =>
                    <div>  <button class="btn" onClick={() => {this.expandStudent(student.id)}}> {student.name}

                    </button>  </div>
                    )
                }
                </div>
        </div>
      );
  }

  if(this.state.isCourseClicked){
        return(
            <div className="App">
                <div>
                {
                    this.state.courses.map(
                        course =>
                        <div>
                            <div> {course.courseName} </div> <br/>
                        </div>
                    )
                }
                </div>
            </div>
        );
  }

  if(this.state.isExpandStudent){
        return(
            <div className="App">
                <div>
                {    this.state.students.map(
                        student =>
                        <div>
                            <div>
                                <h3> {student.name} </h3>
                                <p> Branch: {student.branch} </p>
                                <p> Standard: {student.standard} </p>
                                <button onClick={() => {this.changeRegisteredCourse(student.id)}}> Registered Courses  </button><br/>
                            </div>
                        </div>
                    )
                }
                </div>
            </div>
        );
  }

  if(this.state.isRegisteredCourse){
        return(
            <div className="App">
                <div>
                {
                    this.state.student_courses.map(
                        stCourse =>
                        <div>
                            {stCourse.courseName}<br/>
                        </div>
                    )
                }
                </div>
            </div>
        );
  }

   return(
          <div className="App">
          <div> <header className = "button" />
              <ControlLabel> University Of Starks </ControlLabel> <br/> <br/>

              <Button className="button"
                  block
                  bsSize="large"
                  onClick={() => {this.changeStudentsClicked()}}
                  type="submit" >
                  Students
              </Button>

              <Button className="button"
                  block
                  bsSize="large"
                  onClick={() => {this.changeCourseClicked()}}
                  type="submit" >
                  Total Courses
              </Button>
              </div>
          </div>
      )

}
}

export default App;