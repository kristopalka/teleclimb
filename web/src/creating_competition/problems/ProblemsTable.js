import "../styling/UniversalTableStyling.css";
import "./styling/ProblemsTableStyling.css";
import { RouteTypeWrapper } from "../util/RouteTypeWrapper";
import noRoutesImage from "../../resources/img/mountain.gif";
import removeImg from "../../resources/img/trash-bin.png";

/*
This const is rendering a table of all routes from data.
Fields are as follows:
1. ID
2. Route name
3. Route description
4. Discipline
 */
export const ProblemsTable = ({ data }) => {
  /*
    Render the table
     */
  if (data.length > 0) {
    return (
      <div className={"route-table-wrapper"}>
        <h2>Wszystkie dodane drogi</h2>
        <p>Pasujące do wybranych zawodów</p>
        <div className={"table-container"}>
          <table className={"universal-table"}>
            <thead>
              <tr>
                <th>#</th>
                <th>Nazwa drogi</th>
                <th>Opis drogi</th>
                <th>Dyscyplina</th>
              </tr>
            </thead>
            <tbody className={"scroll-box"}>
              {data.map((item) => (
                <tr className={"hover-tr"} key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.name}</td>
                  <td>{item.description}</td>
                  <td>
                    <RouteTypeWrapper data={item} />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  } else {

  /*
    Render the no-routes available for this competition type text
    and show special gif
     */
    return (
      <div className={"no-routes-div"}>
        <img src={noRoutesImage} alt={""} />
        <h1>Dodaj drogi / problemy aby uzyskać podgląd tutaj</h1>
      </div>
    );
  }
};
