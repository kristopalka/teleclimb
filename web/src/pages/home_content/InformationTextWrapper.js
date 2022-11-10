import "./InformationTextWrapperStyling.css";
import pin from "../../resources/img/pin.png";

export const InformationTextWrapper = (props) => {
  let localization = props.localization;
  let date = props.date;
  let name = props.name;

  return (
    <h3 className={"wrapper"}>
      <img src={pin} alt={""} />
      {localization + ", "}
      {date + ": "}
      {name}
    </h3>
  );
};
