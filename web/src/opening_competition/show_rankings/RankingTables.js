import { Component } from "react";
import { RankingTypeWrapper } from "./RankingTypeWrapper";
import "./RankingTablesStyling.css";

/*
This class shows all ranking tables
For each category, if one has any finished attempts, show the ranking of the competitors
Also; for each category show attempts that are disqualified, or modified
 */
class RankingTables extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    let categories = [
      {
        id: 1,
        ageMin: 19,
        ageMax: 26,
        gender: "M", // F, O available
        type: "bouldering", // speed, lead available
        competitors: [
          {
            id: 1,
            name: "Andrzej",
            surname: "Krzywda",
            points: 2,
          },
          {
            id: 2,
            name: "Krzysztof",
            surname: "Pałka",
            points: 54,
          },
        ],
      },
      {
        id: 2,
        ageMin: 20,
        ageMax: 34,
        gender: "F",
        type: "speed",
        competitors: [
          {
            id: 5,
            name: "Andrzej",
            surname: "Krzywda",
            points: 11,
          },
          {
            id: 6,
            name: "Krzysztof",
            surname: "Pałka",
            points: 21,
          },
        ],
      },
      {
        id: 3,
        ageMin: 35,
        ageMax: 56,
        gender: "O",
        type: "lead",
        competitors: [
          {
            id: 11,
            name: "Andrzej",
            surname: "Krzywda",
            points: 12,
          },
          {
            id: 12,
            name: "Krzysztof",
            surname: "Pałka",
            points: 34,
          },
        ],
      },
    ];

    return (
      <div className={"ranking-tables"}>
        <h2>Dostępne rankingi</h2>
        <div className={"tables-wrapper"}>
          {categories.map((item) => (
            <div className={"single-component"} key={item.id}>
              <RankingTypeWrapper
                ageMin={item.ageMin}
                ageMax={item.ageMax}
                gender={item.gender}
                type={item.type}
              />
              <table className={"ranking-table"}>
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <th>Punkty</th>
                  </tr>
                </thead>
                <tbody>
                  {item.competitors.map((competitor) => (
                    <tr className={"hover-tr"} key={competitor.id}>
                      <td>{competitor.id}</td>
                      <td>{competitor.name}</td>
                      <td>{competitor.surname}</td>
                      <td>{competitor.points}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default RankingTables;
