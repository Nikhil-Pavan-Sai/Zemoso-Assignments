import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";

const useStyles = makeStyles(theme => ({
  image: {
    margin: 10
  },
  black: {
    backgroundColor: "#fff",
    color: "#000"
  },
  white: {
    backgroundColor: "#000",
    color: "#fff"
  }
}));

function MyToolbar() {
  const classes = useStyles();
  const [color, setColor] = React.useState({
    bold: true,
    highlightColor: true,
    insertLink: true,
    italic: true,
    print: true,
    redo: true,
    spellCheck: true,
    underline: true,
    undo: true
  });

  const imageClick = id => {
    switch (id) {
      case "bold":
        setColor({ ...color, bold: false });
        break;
      case "highlightColor":
        setColor({ ...color, highlightColor: false });
        break;
      case "insertLink":
        setColor({ ...color, insertLink: false });
        break;
      case "italic":
        setColor({ ...color, italic: false });
        break;
      case "print":
        setColor({ ...color, print: false });
        break;
      case "redo":
        setColor({ ...color, redo: false });
        break;
      case "spellCheck":
        setColor({ ...color, spellCheck: false });
        break;
      case "underline":
        setColor({ ...color, underline: false });
        break;
      case "undo":
        setColor({ ...color, undo: false });
        break;
      default:
        setColor({
          ...color,
          bold: true,
          highlightColor: true,
          insertLink: true,
          italic: true,
          print: true,
          redo: true,
          spellCheck: true,
          underline: true,
          undo: true
        });
    }
  };
  return (
    <Container fixed>
      <img
        id="undo"
        className={classes.image}
        src={require("../../LogoRes/undo.svg")}
        title="Undo(Ctrl+Z)"
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("undo")}
      />
      <img
        id="redo"
        className={classes.image}
        src={require("../../LogoRes/redo.svg")}
        title="Redo(Ctrl+Y)"
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("redo")}
      />
      <img
        id="italic"
        className={classes.image}
        src={require("../../LogoRes/italic.svg")}
        title="Italic(Ctrl+I)"
        style={{ fontStyle: "italic" }}
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("id")}
      />
      <img
        id="underline"
        className={classes.image}
        src={require("../../LogoRes/underline.svg")}
        title="Underline(Ctrl+U)"
        style={{ textDecorationLine: "underLine" }}
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("underline")}
      />
      <img
        id="bold"
        className={classes.image}
        src={require("../../LogoRes/bold.svg")}
        title="Bold(Ctrl+B)"
        style={{ fontWeight: "bold" }}
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("bold")}
      />
      <img
        id="spellCheck"
        className={classes.image}
        src={require("../../LogoRes/spellCheck.svg")}
        title="Spell Check"
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("spellCheck")}
      />
      <img
        id="print"
        className={classes.image}
        src={require("../../LogoRes/print.svg")}
        title="Print(Ctrl+P)"
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("print")}
      />
      <img
        id="insertLink"
        className={classes.image}
        src={require("../../LogoRes/insertLink.svg")}
        title="Insert Link"
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("insertLink")}
      />
      <img
        id="highlightColor"
        className={classes.image}
        src={require("../../LogoRes/highlightColor.svg")}
        title="Highlight Color"
        alt="undo"
        height="20"
        width="20"
        onClick={() => imageClick("highlightColor")}
      />
    </Container>
  );
}

export default MyToolbar;
