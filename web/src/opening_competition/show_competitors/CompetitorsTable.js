import { Component } from "react";
import "../../creating_competition/styling/UniversalTableStyling.css";
import "./CompetitorsTableStyling.css";

export default class CompetitorsTable extends Component {
  constructor(props) {
    super(props);
    this.state = { results: [] };
  }

  static getDerivedStateFromProps(props, state) {
    return { results: props.results };
  }

  componentDidMount() {
    // setTimeout(() => {
    //     this.getMetaHeaders(this.state.results.participantsData);
    // }, 1000);
  }

  sortParticipants(participants) {
    if (participants) {
      return participants.sort(function (a, b) {
        var x = a["place"];
        var y = b["place"];
        return x < y ? -1 : x > y ? 1 : 0;
      });
    }

    return null;
  }

  addClassForBeatenParticipant(topRoundNumber, participants) {
    if (participants) {
      var maxValue = this.sortParticipants(participants)[0]["topRoundNumber"];

      if (parseInt(topRoundNumber) != parseInt(maxValue)) {
        return "beaten-participant";
      }
    }

    return "";
  }

  addClassForTableWithMetaData(metaData) {
    if (metaData) {
      return metaData.length != 0 ? "competitors-table-wide" : "";
    }

    return "";
  }

  getMetaHeaders(participants) {
    if (participants) {
      var metaData = this.sortParticipants(participants)[0]["meta"];
      var additionalHeaders = [];
      metaData.forEach((obj) => {
        additionalHeaders.push(obj["key"]);
      });

      return additionalHeaders;
    }

    return null;
  }

  render() {
    var additionalHeaders = this.getMetaHeaders(
      this.state.results.participantsData
    );

    return (
      <div className={"competitor-wrapper"}>
        <h2>Wszyscy zawodnicy</h2>
        <table
          className={
            "universal-table " +
            this.addClassForTableWithMetaData(additionalHeaders)
          }
        >
          <thead>
            <tr>
              <th>Miejsce</th>
              <th>Imię</th>
              <th>Nazwisko</th>
              <th>Nr startowy</th>
              <th>Nazwa klubu</th>
              {additionalHeaders?.map((header) => (
                <th>{header ?? "-"}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {this.sortParticipants(this.state.results.participantsData)?.map(
              (participant) => (
                <tr
                  key={participant.id}
                  className={
                    "hover-tr " +
                    this.addClassForBeatenParticipant(
                      participant.topRoundNumber,
                      this.state.results.participantsData
                    )
                  }
                >
                  <td>{participant.place ?? "-"}</td>
                  <td>{participant.name ?? "-"}</td>
                  <td>{participant.lastName ?? "-"}</td>
                  <td>{participant.startNumber ?? "-"}</td>
                  <td>{participant.clubName ?? "-"}</td>
                  {participant.meta?.map((entry) => (
                    <th>{(entry["value"].slice(0, 5)) ?? '-'}</th>
                  ))}
                </tr>
              )
            )}
          </tbody>
        </table>
      </div>
    );
  }
}
