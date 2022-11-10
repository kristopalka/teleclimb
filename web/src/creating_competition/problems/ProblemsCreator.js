import { useForm } from "react-hook-form";
import "../styling/FormStyling.css";
import "./styling/ProblemsCreatorStyling.css";
import { ProblemsTable } from "./ProblemsTable";
import axios from "axios";
import { useEffect, useState } from "react";
import RoundProblemConnector from "./RoundProblemConnector";

/*
This function creates panel for building problems and adding them to the rounds.
 */
export default function ProblemsCreator(props) {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();
  const onSubmit = (data) => {
    postProblem(data);
  };
  const [problems, setProblems] = useState([]);
  const [rounds, setRounds] = useState([]);
  const [formula, setFormula] = useState(undefined);

  /*
    Loading routes from the database in the initialization of the component
    which are connected to the created competition
    GET /route/all -> all the created routes for this contest
    GET /rounds/all/by/id/ -> getting generated rounds to assign routes to them
     */
  useEffect(() => {
    axios
      .get("http://localhost:8080/formula/" + props.competition.formulaId)
      .then((response) => {
        if (formula === undefined) setFormula(response.data);
        else {
          axios
            .get(
              "http://localhost:8080/route/all/by/discipline/" +
                formula.discipline
            )
            .then((response) => setProblems(response.data));
        }
      });

    axios
      .get("http://localhost:8080/round/all/by/" + props.competition.id)
      .then((response) => setRounds(response.data));
  }, [props, formula]);

  /*
    POST to /route
    to save the route in the database
    then
    GET /route/all
    to refresh the view
     */
  function postProblem(data) {
    axios
      .post("http://localhost:8080/route", {
        description: data.description,
        discipline: data.discipline.toUpperCase(),
        name: data.name,
        timeLimitSeconds: getSeconds(data.timeLimitSeconds),
      })
      .then((response) => {
        axios
          .get(
            "http://localhost:8080/route/all/by/discipline/" +
              formula.discipline
          )
          .then((response) => setProblems(response.data));
      });
  }

  /*
    Util function to covert time string in format
    mm:ss to just number in seconds
     */
  function getSeconds(time) {
    let segments = time.split(":");
    return parseInt(segments[0] * 60 + segments[1]);
  }

  /*
    Controller to change the level of creation process
     */
  function updateLevel() {
    props.nextLevelTrigger();
  }

  /*
    Returining three component space
    On the left - form to add all the routes
    On the right - table of the added routes
    Down - form to assign routes to the rounds
     */
  return (
    <div>
      <div className={"problems-creator"}>
        <form onSubmit={handleSubmit(onSubmit)} className={"form"}>
          <span className={"title-span"}>Nowa droga</span>
          <span>Nazwa drogi</span>
          <input {...register("name", { required: true })} />
          {errors.name && (
            <span className={"error-span"}>To pole jest wymagane</span>
          )}
          <span>Opis drogi</span>
          <input {...register("description", { required: true })} />
          {errors.routeName && (
            <span className={"error-span"}>To pole jest wymagane</span>
          )}
          <span>Rodzaj konkurencji</span>
          <select {...register("discipline")}>
            <option value={"bouldering"}>Bouldering</option>
            <option value={"lead"}>Prowadzenie</option>
            <option value={"speed"}>Bieg</option>
          </select>
          <span>Czas na przejście</span>
          <input type={"time"} {...register("timeLimitSeconds")} />
          <button onSubmit={onSubmit}>Dodaj problem</button>
        </form>
        <hr />
        <div className={"table-data"}>
          <ProblemsTable data={problems} />
        </div>
      </div>
      <div>
        <RoundProblemConnector rounds={rounds} problems={problems} />
      </div>
      <div className={"btn-div"}>
        <button onClick={() => updateLevel()}>Zakończ tworzenie</button>
      </div>
    </div>
  );
}
