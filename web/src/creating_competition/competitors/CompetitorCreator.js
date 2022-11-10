import "../styling/FormStyling.css";
import "./styling/CompetitorCreatorStyling.css";
import { useForm } from "react-hook-form";
import { CompetitorsTable } from "./CompetitorsTable";
import { useEffect, useState } from "react";
import axios from "axios";

/*
This function creates participants of the created competition.
 */
export default function CompetitorCreator(props) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data) => {
    postCompetitor(data);
  };

  const [participants, setParticipants] = useState([]);

  /*
    GET /participant/all/by/{id}/
    asking endpoint for all active participants for created competition
     */
  useEffect(() => {
    if (props.competition.id) {
      axios
        .get("http://localhost:8080/participant/all/by/" + props.competition.id)
        .then((response) => setParticipants(response.data));
    }
  }, [props]);

  /*
    Standard function to go to the next level of configuration.
     */
  function updateLevel() {
    props.nextLevelTrigger();
  }

  /*
    Saving data function for competitors
    POST /participant
    I push the last added competition object,
    because this competitor is for it.
     */
  function postCompetitor(data) {
    axios
      .post("http://localhost:8080/participant", {
        competitionId: props.competition.id,
        lastName: data.lastName,
        name: data.name,
        startNumber: data.startNumber,
        birthDate: data.birthDate,
        clubName: data.clubName,
      })
      .then((response) => {
        axios
          .get(
            "http://localhost:8080/participant/all/by/" + props.competition.id
          )
          .then((response) => setParticipants(response.data));
      });
  }

  /*
    Returning form of fields that will be POSTed to API.
    1. Name                     (*)
    2. Last name                (*)
    3. Birth date               (*)
    4. Start number             (*)
    5. Club name                (*)
    Actually no fields are mandatory, you can just skip without posting data.
     */
  return (
    <div>
      <div className={"competitors-creator"}>
        <form className={"form"} onSubmit={handleSubmit(onSubmit)}>
          <span className={"title-span"}>Nowy zawodnik</span>
          <span>Imię</span>
          <input {...register("name", { required: false })} />
          {errors.firstName && (
            <span className={"error-span"}>To pole jest wymagane</span>
          )}
          <span>Nazwisko</span>
          <input {...register("lastName", { required: false })} />
          {errors.secondName && (
            <span className={"error-span"}>To pole jest wymagane</span>
          )}
          <span>Data urodzenia</span>
          <input
            type={"date"}
            {...register("birthDate", { required: false })}
          />
          {errors.birthDate && (
            <span className={"error-span"}>To pole jest wymagane</span>
          )}
          <span>Numer startowy</span>
          <input
            type={"number"}
            {...register("startNumber", { required: false })}
          />
          {errors.startNumber && (
            <span className={"error-span"}>To pole jest wymagane</span>
          )}
          <span>Nazwa klubu</span>
          <input {...register("clubName", { required: false })} />
          {errors.clubName && (
            <span className={"error-span"}>To pole jest wymagane</span>
          )}
          <button onSubmit={onSubmit} id={"competitor-btn"}>
            Dodaj zawodnika
          </button>
        </form>
        <div className={"side-data"}>
          <CompetitorsTable
            data={participants}
            competition={props.competition}
          />
        </div>
      </div>
      <div className={"btn-div"}>
        <button onClick={() => updateLevel()}>Przejdź dalej</button>
      </div>
    </div>
  );
}
