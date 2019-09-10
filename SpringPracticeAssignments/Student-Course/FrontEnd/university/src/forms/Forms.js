import React,{Component} from 'react';
import '/home/user/Documents/Assignments/SpringPracticeAssignments/Student-Course/FrontEnd/university/src/Security/Login.css'

class Forms extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    };
    console.log(props.fields);
  }
  myChangeHandler = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    this.setState({[nam]: val});
    this.postchanges = this.postchanges.bind(this);
  }

  async postchanges(event)
  {

     if(this.props.submiturl){
     await fetch(this.props.submiturl, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(this.state)
    });
    }
    if(this.props.callBack)
        this.props.callBack(event,this.state);
  }

  render() {
    return (
      <form className="Login">{
     Object.entries(this.props.fields).map(
        ([key,value])=>
        <div>
          <p>Enter {key} </p>
          <input
            type={value}
            name={key}
            onChange={this.myChangeHandler}
          /><br />
        </div>
      )
      }
      <br />
      <button onClick={this.postchanges}>Submit</button>
      </form>
    );
  }
}

export default Forms;