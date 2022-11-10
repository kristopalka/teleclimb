import { Component } from "react";
import "./styling/OpenCompetitionWrapperStyling.css";
import CompetitorsTable from "./show_competitors/CompetitorsTable";
import RoundsTable from "./show_rounds/RoundsTable";
import SideMenu from "./side_menu/SideMenu";
import axios from "axios";
import RoutesTable from "./show_routes/RoutesTable";
import StartsTable from "./show_start/StartsTable";

/*
This class presents menu for showing the ongoing competition
 */
class OpenCompetitionWrapper extends Component {
  constructor(props) {
    super(props);
    this.state = {
      competitionId: undefined,
      competitions: [],
      results: [],
      rounds: [],
      category: [],
      formula: [],
    };
  }

  componentDidMount() {
    this.getCompetitions();

    const competitionId = localStorage.getItem("competitionId");
    if (competitionId) {
      this.changeCompetitionId(competitionId);
    }
  }

  changeCompetitionId = (id) => {
    this.setState({ competitionId: id });
    this.getResults(id);
    this.getRounds(id);
    localStorage.setItem("competitionId", id);
  };

  getCompetitions() {
    axios
      .get("http://localhost:8080/competition/all")
      .then((response) => {
        this.setState({ competitions: response.data });
      })
      .catch((err) => console.error(err));
  }

  getResults(competitionId) {
    axios
      .get("http://localhost:8080/results/competition/" + competitionId)
      .then((response) => {
        this.setState({ results: response.data });
      })
      .catch((err) => console.error(err));
  }

  getRounds(competitionId) {
    axios
      .get("http://localhost:8080/round/all/by/" + competitionId)
      .then((response) => this.setState({ rounds: response.data }))
      .catch((err) => console.error(err));
  }

  render() {
    const competitions = this.state.competitions;
    const results = this.state.results;
    const rounds = this.state.rounds;

    if (this.state.competitionId) {
      return (
        <div className={"open-competition-wrapper"}>
          <div className={"main-app-view"}>
            <RoundsTable
              rounds={rounds}
              competitionId={this.state.competitionId}
            />
            <RoutesTable competitionId={this.state.competitionId} />
            <StartsTable />
            <CompetitorsTable results={results} />
          </div>
          <SideMenu
            changeCompetitionId={this.changeCompetitionId}
            competitions={competitions}
          />
        </div>
      );
    } else {
      return (
        <div className={"open-competition-wrapper"}>
          <div className={"main-app-view"}>
            <div className={"title-wrapper"}>
              <h1>Podgląd zawodów na żywo</h1>
              <h4>
                Wybierz konkurencję z listy dostępnych zawodów aby wyświetlić
                wszystkie informacje
              </h4>
            </div>
          </div>
          <SideMenu
            changeCompetitionId={this.changeCompetitionId}
            competitions={competitions}
          />
        </div>
      );
    }
  }
}

export default OpenCompetitionWrapper;
