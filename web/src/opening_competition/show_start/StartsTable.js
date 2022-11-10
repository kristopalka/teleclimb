import { Component } from "react";
import "./StartsTableStyling.css";
import axios from "axios";

class StartsTable extends Component {
  constructor(props) {
    super(props);
    this.state = { starts: [] };
  }

  componentDidMount() {
    this.getStarts();
    setTimeout(() => {
      console.log(this.state.starts);
    }, 1000);
  }

  getStarts() {
    var hash = localStorage.getItem("routeHash");

    if (hash) {
      axios
        .get(
          "http://localhost:8080/mobile/starts-by-referee-position-hash/" + hash
        )
        .then((response) => this.setState({ starts: response.data }))
        .catch((err) => console.error(err));
    }
  }

  getRefereePositionName() {
    return localStorage.getItem("routeName") ?? "";
  }

  render() {
    return (
      <div className={"starts-wrapper"}>
        <h2>
          Starty dla stanowiska sędziowskiego: {this.getRefereePositionName()}
        </h2>
        <table className={"universal-table"}>
          <thead>
            <tr>
              <th>Nr</th>
              <th>Imię zawodnika</th>
              <th>Nazwisko zawodnika</th>
              <th>Numer startowy</th>
              <th>Klub</th>
            </tr>
          </thead>
          <tbody>
            {this.state.starts?.map((start) => (
              <tr key={start.id}>
                <td>{start.positionSequenceNumber ?? "-"}</td>
                <td>{start.participant.name ?? "-"}</td>
                <td>{start.participant.lastName ?? "-"}</td>
                <td>{start.participant.startNumber ?? "-"}</td>
                <td>{start.participant.clubName ?? "-"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default StartsTable;
