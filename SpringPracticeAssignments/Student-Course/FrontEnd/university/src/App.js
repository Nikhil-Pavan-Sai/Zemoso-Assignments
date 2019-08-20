import React from 'react';
import logo from './logo.svg';
import './App.css';

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
    this.state={
        students:[]
    }
}

render(){
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div>
        {
            this.state.students.map(
            student=><div>
            <div>{student.name}</div><br/></div>
            )
        }</div>
      </header>
    </div>
  );
}
}

export default App;
