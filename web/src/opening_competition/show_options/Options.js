import { Component } from "react";
import "./OptionsStyling.css";

/*
This class presents all accessible options for any competition
 */
class Options extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className={"options-wrapper"}>
        <h2>Opcje konfiguracji</h2>
        <div className={"buttons-wrapper"}>
          <button className={"options-button"}>Zapisz i zakończ zawody</button>
          <button className={"options-button"}>Modyfikuj dane</button>
          <button className={"options-button"}>Generuj PDF</button>
        </div>
      </div>
    );
  }
}

export default Options;
