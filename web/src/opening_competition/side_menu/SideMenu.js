import { Component } from "react";
import "./styling/SideMenuStyling.css";

export default class SideMenu extends Component {
  constructor(props) {
    super(props);
    this.state = { competitions: [] };
  }

  static getDerivedStateFromProps(props, state) {
    return { competitions: props.competitions };
  }

  addClassForCurrentCompetition(id) {
    if (id == localStorage.getItem("competitionId")) {
      return "current-competition";
    }

    return "";
  }

  clearRoute() {
    localStorage.removeItem("roundId");
  }

  clearStarts() {
    localStorage.removeItem('routeHash');
    localStorage.removeItem('routeName');
  }

  render() {
    const competitions = this.state.competitions;

    return (
      <div className={"side-menu"}>
        <h1>Dostępne zawody</h1>
        <div className={"all-competitions-list"}>
          {competitions.map((competition) => (
            <div
              key={competition.id}
              className={
                "side-menu-element " +
                this.addClassForCurrentCompetition(competition.id)
              }
              onClick={() => {
                this.props.changeCompetitionId(competition.id);
                this.clearRoute();
                this.clearStarts();
                window.location.reload();
              }}
            >
              <h3>{competition.name ?? "-"}</h3>
              <p>
                {competition.discipline ?? "-"}, {competition.gender ?? "-"},{" "}
                {competition.categoryName ?? "-"}
              </p>
            </div>
          ))}
        </div>
      </div>
    );
  }
}
