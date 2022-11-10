import { Component } from "react";
import "./styling/CompetitionCreatorStyling.css";
import ProblemsCreator from "./problems/ProblemsCreator";
import CompetitorCreator from "./competitors/CompetitorCreator";
import LevelSlider from "./LevelSlider";
import BasicCompetitionCreator from "./basic/BasicCompetitionCreator";
import { Navigate } from "react-router-dom";

/***
 * This class wraps all behavior of creating a competition
 */
class ContestCreator extends Component {

    constructor(props) {
    super(props);
    this.state = {
      realLevel: 0,
      competition: [],
      problems: [],
      competitors: [],
    };
    this.triggerLevelUpdate = this.triggerLevelUpdate.bind(this);
    this.setCompetition = this.setCompetition.bind(this);
  }

  /*
    Endpoint for all child components to change the view of the creator
    Increments the realLevel via setState so view changes dynamically
     */
  triggerLevelUpdate() {
    this.setState({ realLevel: this.state.realLevel + 1 });
  }

  /*
    Endpoint function in contest to save crucial data
    from user, to make additional requests co correct
    entity in the backend
     */
  setCompetition(data) {
      this.setState({competition: data});
  }

  /*
    Rendering entire page to show element and slider.
    0. Showing basic data view
    1. Showing panel to add competitors
    2. Showing page to connect routes with rounds
    3. Redirecting to see all competitions
     */
  render() {
    let realLevel = this.state.realLevel;

    switch (realLevel) {
      case 0:
        return (
          <div className={"creator"}>
            <BasicCompetitionCreator
              saveData={this.setCompetition}
              nextLevelTrigger={this.triggerLevelUpdate}
            />
            <LevelSlider activity={realLevel} />
          </div>
        );
      case 1:
        return (
          <div className={"creator"}>
            <CompetitorCreator
              nextLevelTrigger={this.triggerLevelUpdate}
              competition={this.state.competition}
            />
            <LevelSlider activity={realLevel} />
          </div>
        );
      case 2:
        return (
          <div className={"creator"}>
            <ProblemsCreator
              nextLevelTrigger={this.triggerLevelUpdate}
              competition={this.state.competition}
            />
            <LevelSlider activity={realLevel} />
          </div>
        );
      case 3:
        return <Navigate to={"/open-competition"} replace={true}/>;
    }
  }
}

export default ContestCreator;
