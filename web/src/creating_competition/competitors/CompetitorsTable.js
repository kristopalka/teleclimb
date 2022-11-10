import "./styling/CompetitorsTableStyling.css";
import "../../creating_competition/styling/UniversalTableStyling.css";
import noCompetitorsImage from "../../resources/img/user.gif";
import { useEffect } from "react";

/*
CompetitorsTable wraps the behavior of empty and non-empty table of all participants,
of currently configured competition.
 */
export const CompetitorsTable = ({ data, competition }) => {
  /*
    If data is not empty show table of competitors, added to the current competition.
    With columns respectively:
    1. Id
    2. Name
    2. Last name
    3. Club name
    4. Starting number
    5. Competition name.
     */

  if (competition && data.length > 0) {
    return (
      <div className={"table-container"}>
        <h2>Dodani zawodnicy</h2>
        <table className={"universal-table"}>
          <thead>
            <tr>
              <th>Id</th>
              <th>Imię</th>
              <th>Nazwisko</th>
              <th>Klub</th>
              <th>Nr startowy</th>
              <th>Zawody</th>
            </tr>
          </thead>
          <tbody>
            {data.map((competitor) => (
              <tr className={"hover-tr"} key={competitor.id}>
                <td>{competitor.id}</td>
                <td>{competitor.name}</td>
                <td>{competitor.lastName}</td>
                <td>{competitor.clubName}</td>
                <td>{competitor.startNumber}</td>
                <td>{competition.name}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  } else {

  /*
    If no data is available for this particular competition,
    show text and informational gif image.
     */
    return (
      <div className={"no-competitors-div"}>
        <img src={noCompetitorsImage} alt={""} />
        <h1>Dodaj zawodników aby zobaczyć podgląd</h1>
      </div>
    );
  }
};
