import waitImg from "../../resources/img/stopwatch.png";
import freeImg from "../../resources/img/correct.png";
import "./StateWrapperStyling.css";

/*
This constant wraps data into a simple icon to make it visible better
 */
export const StateWrapper = (props) => {
  if (props.state === "free") {
    return (
      <div className={"state-wrapper"}>
        <img src={freeImg} alt={""} />
      </div>
    );
  }

  return (
    <div className={"state-wrapper"}>
      <img src={waitImg} alt={""} />
    </div>
  );
};
