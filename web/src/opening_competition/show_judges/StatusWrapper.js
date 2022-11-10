import "./StatusWrapperStyling.css";
import smartphone from "../../resources/img/smartphone.png";

export const StatusWrapper = (props) => {
  let status = props.status === true ? "połączono" : "nie połączono";
  let c = props.status === true ? "0" : "1";

  if (status === "połączono") {
    return (
      <td className={"connected0"}>
        {status}
        <img src={smartphone} alt={""} />
      </td>
    );
  }
  return <td className={"connected1"}>{status}</td>;
};
