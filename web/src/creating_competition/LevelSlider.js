import "./styling/LevelSliderStyling.css";
import { Component } from "react";

/*
The level slider is the bottom slider to navigate the process of the creation of the competition.
 */
const LevelSlider = (props) => {
  let activity = [];
  for (let i = 0; i < 5; i++) {
    if (i === props.activity) {
      activity[i] = 1;
    } else {
      activity[i] = 0;
    }
  }

  /*
    It looks like this:
    Competition         Competitors         Problems
        (1)                 (2)                (3)
     */
  return (
    <div className={"container"}>
      <div className={"main-element" + activity[0]}>
        <p>Tworzenie zawodów</p>
        <button>1</button>
      </div>
      <hr className={"vertical-line"} />
      <div className={"main-element" + activity[1]}>
        <p>Dodawanie zawodników</p>
        <button>2</button>
      </div>
      <hr className={"vertical-line"} />
      <div className={"main-element" + activity[2]}>
        <p>Wybór dróg</p>
        <button>3</button>
      </div>
    </div>
  );
};

export default LevelSlider;
