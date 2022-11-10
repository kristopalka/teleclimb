import { Component } from "react";
import "../../creating_competition/styling/UniversalTableStyling.css";
import "./RoundsTableStyling.css";
import axios from "axios";

export default class RoundsTable extends Component {
  constructor(props) {
    super(props);
    this.state = { rounds: [], competitionId: undefined };
  }

  static getDerivedStateFromProps(props, state) {
    return { rounds: props.rounds, competitionId: props.competitionId };
  }

  updateRoundState(roundState, roundId) {
    switch (roundState) {
      case "NOT_STARTED":
        axios
          .post("http://localhost:8080/manage/round/" + roundId + "/start", {})
          .then(this.getRounds(this.props.competitionId))
          .catch((err) => console.error(err));
        break;
      case "IN_PROGRESS":
        axios
          .post("http://localhost:8080/manage/round/" + roundId + "/finish")
          .then(this.getRounds(this.props.competitionId))
          .catch((err) => console.error(err));
        break;
      default:
        break;
    }

    setTimeout(() => {
      window.location.reload();
    }, 100);
  }

  chooseRoundMessageByState = (roundState) => {
    let message = "";
    switch (roundState) {
      case "NOT_STARTED":
        message = "Rozpocznij rundę";
        break;
      case "IN_PROGRESS":
        message = "Zakończ rundę";
        break;
      default:
        message = "Runda zakończona";
        break;
    }

    return message;
  };

  chooseRoundClassByState = (roundState) => {
    let roundClass = "";
    switch (roundState) {
      case "NOT_STARTED":
        roundClass = "round-not-started";
        break;
      case "IN_PROGRESS":
        roundClass = "round-started";
        break;
      default:
        roundClass = "round-finished";
        break;
    }

    return roundClass;
  };

  getRounds(competitionId) {
    axios
      .get("http://localhost:8080/round/all/by/" + competitionId)
      .then((response) => this.setState({ rounds: response.data }))
      .catch((err) => console.error(err));
  }

  seeRoundDetails(roundId, roundName) {
    localStorage.setItem("roundId", roundId);
    localStorage.setItem("roundName", roundName);
    window.location.reload();
  }

  render() {
    return (
      <div className={"competitor-wrapper"}>
        <h2>Rundy</h2>
        <table className={"universal-table"}>
          <thead>
            <tr>
              <th>Nazwa</th>
              <th>Stan</th>
              <th>Max. uczestników</th>
              <th>Pozycje sędziowskie</th>
              <th>Aktualizuj stan</th>
            </tr>
          </thead>
          <tbody>
            {this.state.rounds.map((round) => (
              <tr key={round.id}>
                <td>{round.name ?? "-"}</td>
                <td>{round.state ?? "-"}</td>
                <td>{round.maxParticipants ?? "-"}</td>
                <td>
                  <button
                    className="route-details"
                    onClick={() => {
                      this.seeRoundDetails(round.id, round.name);
                    }}
                  >
                    Zobacz szczegóły
                  </button>
                </td>
                <td>
                  <button
                    className={
                      "state-updater " +
                      this.chooseRoundClassByState(round.state)
                    }
                    onClick={() => {
                      this.updateRoundState(round.state, round.id);
                    }}
                  >
                    {this.chooseRoundMessageByState(round.state)}
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
