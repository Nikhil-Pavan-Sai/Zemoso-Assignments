import React from "react";
import Forms from "../forms/Forms";

export function AddCourse(props) {
  return (
    <Forms
      callBack={props.callBack}
      goHome={props.goHome}
      goStudent={props.goStudent}
      goCourse={props.goCourse}
      submiturl="/courses"
      fields={{ courseName: "text", courseDescription: "text" }}
    />
  );
}
