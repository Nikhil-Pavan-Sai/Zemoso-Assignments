import React, { useState, useEffect } from "react";
import NewLogin from "./Security/NewLogin";
import AllStudents from "./Screens/AllStudents";
import AllCourses from "./Screens/AllCourses";
import Sample from "./Screens/Sample";

export default function App() {
  const [pageStatus, setPageStatus] = useState("MAIN");
  const [students, setStudents] = useState([]);
  const [courses, setCourses] = useState([]);
  const [userName, setUserName] = useState(localStorage.getItem("uname"));
  let [user, setUser] = useState(localStorage.getItem("user"));

  useEffect(() => {
    async function fetchData() {
      const student_response = await fetch("/students");

      const course_response = await fetch("/courses");

      const student_body = await student_response.json();
      const course_body = await course_response.json();

      setStudents(student_body);
      setCourses(course_body);
    }
    fetchData();
  }, []);

  useEffect(() => {
    if (user === "false") setPageStatus("LOGGED_OUT");
    else setPageStatus("LOGGED_IN");
  }, [user]);

  async function changeLogin(x) {
    localStorage.setItem("user", "true");
    let data = await x.json();
    setUserName(data.firstName);
    localStorage.setItem("uname", data.firstName);
    setPageStatus("LOGGED_IN");
    setUser("true");
  }

  async function expandStudent(id) {
    const studentInfo = await fetch("students/" + id);
    const stInfo = await studentInfo.json();
    setStudents([stInfo]);
    setPageStatus("STUDENT_EXPANDED");
  }

  function removeStudent(id, event) {
    fetch("/students/" + id, { method: "DELETE" });
    const isNotId = item => item.id !== id;
    const newStudents = students.filter(isNotId);
    setPageStatus("STUDENTS_CLICKED");
    setStudents(newStudents);
  }

  async function expandCourse(id) {
    const courseInfo = await fetch("courses/" + id);
    const coInfo = await courseInfo.json();
    setCourses([coInfo]);
    setPageStatus("COURSE_EXPANDED");
  }

  function removeCourse(id) {
    fetch("/courses/" + id, { method: "DELETE" });
    const isNotId = item => item.id !== id;
    const newCourses = courses.filter(isNotId);
    setPageStatus("COURSES_CLICKED");
    setCourses(newCourses);
  }

  if (pageStatus === "LOGGED_IN") {
    return (
      <Sample
        res_student={() => setPageStatus("STUDENTS_CLICKED")}
        res_course={() => setPageStatus("COURSES_CLICKED")}
        onLogout={() => {
          localStorage.setItem("user", "false");
          setPageStatus("MAIN");
        }}
        zoro={userName}
      />
    );
  }
  if (pageStatus === "STUDENTS_CLICKED") {
    return (
      <AllStudents
        goHome={() => setPageStatus("LOGGED_IN")}
        goStudents={() => setPageStatus("STUDENTS_CLICKED")}
        goCourses={() => setPageStatus("COURSES_CLICKED")}
        expand={x => expandStudent(x)}
        remove={x => removeStudent(x)}
        addStudent={() => setPageStatus("ADD_STUDENT")}
        students={students}
        courses={courses}
      />
    );
  }

  if (pageStatus === "COURSES_CLICKED") {
    return (
      <AllCourses
        goHome={() => setPageStatus("LOGGED_IN")}
        goStudents={() => setPageStatus("STUDENTS_CLICKED")}
        goCourses={() => setPageStatus("COURSES_CLICKED")}
        expand={x => expandCourse(x)}
        remove={x => removeCourse(x)}
        students={students}
        courses={courses}
      />
    );
  }

  return <NewLogin callback={x => changeLogin(x)} />;
}
