import React from "react";
import Forms from "../forms/Forms";

export function AddStudent(props) {
  return (
    <Forms
      callBack={props.callBack}
      goHome={props.goHome}
      goStudent={props.goStudent}
      goCourse={props.goCourse}
      submiturl="/students"
      fields={{ name: "text", standard: "text", branch: "text" }}
    />
  );
}
