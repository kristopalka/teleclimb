import { Component } from "react";
import "../../creating_competition/styling/UniversalTableStyling.css";
import "./RoutesTableStyling.css";
import axios from "axios";

export default class RoutesTable extends Component {
  constructor(props) {
    super(props);
    this.state = { refereePositions: [] };
  }

  componentDidMount() {
    this.getRoutes();
  }

  getRoutes() {
    var roundId = localStorage.getItem("roundId");

    if (roundId) {
      axios
        .get("http://localhost:8080/referee-position/all/by/" + roundId)
        .then((response) => this.setState({ refereePositions: response.data }))
        .catch((err) => console.error(err));
    }
  }

  getRoundName() {
    return localStorage.getItem("roundName") ?? "";
  }

  seeStartDetails(routeHash, routeName) {
    localStorage.setItem("routeHash", routeHash);
    localStorage.setItem("routeName", routeName);
    window.location.reload();
  }

  render() {
    return (
      <div className={"competitor-wrapper"}>
        <h2>Stanowiska sędziowskie w rundzie: {this.getRoundName()}</h2>
        <table className={"universal-table"}>
          <thead>
            <tr>
              <th>Nazwa</th>
              <th>Opis</th>
              <th>Limit czasu</th>
              <th>Kod</th>
              <th>Starty</th>
            </tr>
          </thead>
          <tbody>
            {this.state.refereePositions.map((refereePosition) => (
              <tr key={refereePosition.id}>
                <td>{refereePosition.route.name ?? "-"}</td>
                <td>{refereePosition.route.description ?? "-"}</td>
                <td>{refereePosition.route.timeLimit ?? "-"}</td>
                <td className="hash">{refereePosition.hash ?? "-"}</td>
                <button
                  className="start-details"
                  onClick={() => {
                    this.seeStartDetails(
                      refereePosition.hash,
                      refereePosition.route.name
                    );
                  }}
                >
                  Zobacz szczegóły
                </button>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
