import { Component } from "react";
import { StatusWrapper } from "./StatusWrapper";
import "../../creating_competition/styling/UniversalTableStyling.css";
import "./JudgesTableStyling.css";

class JudgesTable extends Component {
  render() {
    let data = [
      {
        id: 1,
        nick: "Andrzej",
        problem: "problem skalny nr 2",
        status: true,
      },
      {
        id: 2,
        nick: "Staszek",
        problem: "problem skalny nr 4",
        status: false,
      },
      {
        id: 3,
        nick: "Marcin",
        problem: "problem skalny nr 1",
        status: false,
      },
      {
        id: 4,
        nick: "Grzesiek",
        problem: "problem skalny nr 5",
        status: true,
      },
    ];

    return (
      <div className={"judges-wrapper"}>
        <h2>Tabela sędziów</h2>
        <table className={"universal-table"}>
          <thead>
            <tr>
              <th>#</th>
              <th>Imię / Ksywka</th>
              <th>Obsługiwany problem</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {data.map((item) => (
              <tr key={item.id} className={"hover-tr"}>
                <td>{item.id}</td>
                <td>{item.nick}</td>
                <td>{item.problem}</td>
                <StatusWrapper status={item.status} />
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default JudgesTable;
