import { Component } from "react";
import "./styling/NewCompetition.css";
import ContestCreator from "../creating_competition/ContestCreator";

class NewCompetition extends Component {
  componentDidMount() {
    localStorage.clear();
  }

  render() {
    return (
      <div className={"new-competition-wrapper"}>
        <h1>
          Witaj w kreatorze zawodów. Przejdź przez kolejne kroki tworzenia
          konkursu
        </h1>
        <div className={"competition-configurator-wrapper"}>
          <ContestCreator />
        </div>
      </div>
    );
  }
}

export default NewCompetition;
