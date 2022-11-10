import { Component } from "react";
import "./ActiveProblemsTablesStyling.css";
import "../../creating_competition/styling/UniversalTableStyling.css";
import { StateWrapper } from "./StateWrapper";

/*
This class wraps array of objects of type Problem into table of problems,
showing the live progress on the route.
 */
class ActiveProblemsTables extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: props.data,
    };
  }

  /*
    The build looks like this:
    First the table containing all the routes that are currently climbed is shown
    Then all the free routes are shown - either free or in state of preparing
    Coloring shows the percentage of the route reached

    TABLE 1
    id / name / judge name / competitor name / percentage / points

    TABLE 2
    id / name / judge name / competitor name / state : WAIT
    or
    id / name / judge name / state: FREE
     */

  render() {
    let data = [
      {
        id: 1,
        routeName: "droga skalna nr 2",
        judgeName: "Andrzej Krzywda",
        competitor: "Krzysztof Pałka",
        reached: 7,
        top: 13,
        time: "0:45.321",
        rivalry: "bieg mężczyźni 16-24 lata",
      },
      {
        id: 2,
        routeName: "droga skalna nr 1",
        judgeName: "Stanisław",
        competitor: "Jasiek Kowalski",
        reached: 3,
        top: 8,
        time: "3:11.210",
        rivalry: "prowadzenie kobiety 18-45 lat",
      },
    ];

    let routesData = [
      {
        id: 4,
        routeName: "droga skalna nr 3",
        judgeName: "Jerzy W",
        rivalry: "bouldering mężczyźni 14-18 lat",
        top: 5,
        state: "free",
      },
      {
        id: 7,
        routeName: "droga skalna nr 5",
        judgeName: "Wojciech P",
        rivalry: "bouldering kobiety 5-10 lat",
        top: 7,
        state: "wait",
      },
    ];

    return (
      <div className={"problems-tables"}>
        <div className={"table-wrapper"}>
          <h2>Aktywne próby</h2>
          <table className={"universal-table"}>
            <thead>
              <tr>
                <th>#</th>
                <th>Nazwa</th>
                <th>Sędzia</th>
                <th>Zawodnik</th>
                <th>Przejście</th>
                <th>Czas</th>
                <th>Rodzaj rywalizacji</th>
              </tr>
            </thead>
            <tbody>
              {data.map((item) => (
                <tr className={"hover-tr"} key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.routeName}</td>
                  <td>{item.judgeName}</td>
                  <td>{item.competitor}</td>
                  <td>
                    {item.reached}/{item.top}
                  </td>
                  <td>{item.time}</td>
                  <td>{item.rivalry}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className={"table-wrapper"}>
          <h2>Wolne drogi</h2>
          <table className={"universal-table"}>
            <thead>
              <tr>
                <th>#</th>
                <th>Nazwa</th>
                <th>Rodzaj rywalizacji</th>
                <th>Sędzia</th>
                <th>Top</th>
                <th>Stan</th>
              </tr>
            </thead>
            <tbody>
              {routesData.map((item) => (
                <tr className={"hover-tr"} key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.routeName}</td>
                  <td>{item.rivalry}</td>
                  <td>{item.judgeName}</td>
                  <td>{item.top}</td>
                  <td>
                    <StateWrapper state={item.state} />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ActiveProblemsTables;
