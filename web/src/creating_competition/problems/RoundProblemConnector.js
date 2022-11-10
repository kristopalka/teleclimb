import "./styling/RoundProblemConnectorStyling.css";
import axios from "axios";

/*
This function is a multiple choice component for connecting many routes to many rounds.
Each round can hav a maximum of n routes where n is defined by data from API
by name of "numberOfRoutes"
 */
export default function RoundProblemConnector(props) {

  let markedData = new Map();

  /*
    Building map with 2-element key
    (roundId:problemId) => bool
    by this app can identify which exact route is checked
     */
  for (let j = 0; j < props.rounds.length; j++) {
    for (let i = 0; i < props.problems.length; i++) {
      let key = props.rounds[j].id + ":" + props.problems[i].id;
      markedData.set(key, false);
    }
  }

  /*
    This method takes pair of arguments : problemId and roundId
    which are necessary to construct key to map that stores information
    whether the route is checked or not
    Then, depending on the state links, or unlinks the data to each other
    ROUTE <=> ROUND
     */
  function routeChosen(problemId, roundId) {

    let key = roundId + ":" + problemId;

    /*
        If map contains 'true' for route, it is checked and association onClicked needs to be removed
         */
    if (markedData.get(key)) {
      console.log("Unlink " + problemId + " from round " + roundId);
      axios
        .post(
          "http://localhost:8080/round/" +
            roundId +
            "/remove-route/" +
            problemId
        )
        .then((response) => {
          markedData.delete(key);
          console.log(response);
        });
    } else {

        /*
        If map contains 'false' the connection is established
         */
      console.log("Link " + problemId + " to round " + roundId);
      axios
        .post(
          "http://localhost:8080/round/" + roundId + "/add-route/" + problemId
        )
        .then((response) => {
          markedData.set(key, true);
          console.log(response);
        });
    }
  }

  let rounds = props.rounds;
  let problems = props.problems;

  return (
    <div className={"round-connector"}>
      <h1>Dodawaj drogi do rund</h1>
      <h3>Do każdej wybranej rundy możesz zaznaczyć wiele wybranych dróg</h3>
      <div className={"rounds"}>
        {rounds.map((round) => (
          <div className={"round"} key={round.id}>
            <h1 key={round.id}>{round.name}</h1>
            <h4>Maksymalna liczba dróg do wyboru: {round.numberOfRoutes}</h4>
            <form className={"connector-form"}>
              {problems.map((problem) => (
                <div className={"option-wrapper"} key={problem.id}>
                  <span>{problem.description + " (" + problem.name + ")"}</span>
                  <input
                    type={"checkbox"}
                    onClick={() => routeChosen(problem.id, round.id)}
                  />
                </div>
              ))}
            </form>
          </div>
        ))}
      </div>
    </div>
  );
}
