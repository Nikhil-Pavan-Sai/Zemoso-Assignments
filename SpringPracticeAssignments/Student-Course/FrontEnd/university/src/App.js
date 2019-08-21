import React from 'react';
import './App.css';
import Login from './Security/Login.js';

class App extends React.Component{

async componentDidMount()
{
    const response=await fetch("/students/")
    const body=await response.json()
    this.setState({students:body})
}

constructor(props)
{
    super(props)
    this.onChange=this.onChange.bind(this)
    this.changeLogin=this.changeLogin.bind(this)
    this.state={
        isLoggedIn:false,
        students:[],
    }
}

changeLogin()
{
    this.setState({isLoggedIn:true})
}

onChange(e)
{
    this.setState({
        name: e.target.value
    })
}

render(){
   if(!this.state.isLoggedIn)
    return(<Login callback={this.changeLogin}/>)
  return (
    <div className="App">
        <div> <header className = "App-header">
            <div>
            {
                this.state.students.map(
                student =>
                <div>
                    <div> {student.name} </div> <br/>
                </div>
                )
            }
            </div>
        </header></div>
    </div>
  );
}
}

export default App;