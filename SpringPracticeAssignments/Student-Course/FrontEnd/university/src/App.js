import React from 'react';
import './App.css';
import Login from './Security/Login.js';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";

class App extends React.Component{

async componentDidMount()
{
    const response=await fetch("/students")
    const course_response=await fetch("/students/2/courses")

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
    this.state={
        isLoggedIn:false,
        isStudentsClicked:false,
        isCourseClicked:false,
        students:[],
        courses:[],
    }
}

changeLogin()
{
    this.setState({isLoggedIn:true})
}

changeStudentsClicked()
{
    this.setState({isStudentsClicked:true})
}

changeCourseClicked()
{
    this.setState({isCourseClicked:true})
}

onChange(e)
{
    this.setState({
        name: e.target.value,
        branch: e.target.value,
        courseName:e.target.value
    })
}

render(){
   if(!this.state.isLoggedIn)
    return(<Login callback={this.changeLogin}/>)
   if(!this.state.isStudentsClicked && !this.state.isCourseClicked)
   {
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
                Courses
            </Button>
            </div>
        </div>
    )
   }

  if(this.state.isStudentsClicked){
      return (
        <div className="App">
                <div>
                {
                    this.state.students.map(
                    student =>
                    <div>
                        <div> {student.name} , {student.branch} </div> <br/>
                    </div>
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
}
}

export default App;