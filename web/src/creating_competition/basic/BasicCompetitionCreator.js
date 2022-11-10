import { useForm } from "react-hook-form";
import "../styling/FormStyling.css";
import "./styling/BasicCompetitionCreatorStyling.css";
import { useEffect, useState } from "react";
import axios from "axios";
import { CategoryMapper } from "../util/CategoryMapper";

/*
This function creates view for inserting basic data when starting to build a competition.
 */
export default function BasicCompetitionCreator(props) {

  /*
    Universal legend for API:
    (*) POSTING this field
    (x) NOT POSTING this field
     */

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const [categories, setCategories] = useState([]);
  const [formulas, setFormulas] = useState([]);

  /*
    Dynamically load data from endpoints
    /category/all -> all categories from db
    /formula/all -> all formulas from db
    Both are static data, declared in the backend server
     */
  useEffect(() => {
    axios
      .get("http://localhost:8080/category/all")
      .then((response) => setCategories(response.data));

    axios
      .get("http://localhost:8080/formula/all")
      .then((response) => setFormulas(response.data));
  }, []);

  /*
    Use POST endpoint /competition to save new competition
    + after receiving response we automatically generate rounds
     */
  function postCompetitionAndGenerateRounds(data) {
    axios
      .post("http://localhost:8080/competition", {
        categoryId: data.categoryId,
        formulaId: data.formulaId,
        name: data.name,
        gender: data.gender,
      })
      .then((response) => {
        props.saveData(response.data);
        postGenerateRounds(response.data.id);
      });
  }

  /*
    Asking the POST /competition/{id}/generate-rounds to create rounds in backend
    And later use GET /competition/{id} to get rounds at any moment later
     */
  function postGenerateRounds(competitionId) {
    axios
      .post(
        "http://localhost:8080/competition/" +
          competitionId +
          "/generate-rounds"
      )
      .then((response) => {});
  }

  /*
    Perform tasks on creating a competition
    1. Post the information through API
    2. Get - generating the rounds connected to just created competition
    3. Trigger function to change the view
     */
  const onSubmit = (data) => {
    postCompetitionAndGenerateRounds(data);
    props.nextLevelTrigger();
  };

  /*
    Schema of the form looks like:
    1. Name of the competition      (*)
    2. Main judge                   (x)
    3. Description                  (x)
    4. Category                     (*)
    5. Gender                       (*)
    6. Formula                      (*)
     */
  return (
    <div className={"basic-competition-creator"}>
      <form onSubmit={handleSubmit(onSubmit)} className={"form"}>
        <span id={"time-span"}>Dane ogólne</span>
        <span>Nazwa zawodów</span>
        <input
          {...register("name", { required: true })}
          id={"competition-name"}
        />
        {errors.competitionName && (
          <span className={"error-span"}>To pole jest wymagane</span>
        )}
        <span>Sędzia główny</span>
        <input {...register("judgeName", { required: false })} />
        {errors.judgeName && (
          <span className={"error-span"}>To pole jest wymagane</span>
        )}
        <span>Opis wydarzenia</span>
        <input />
        <span>Kategoria</span>
        <select {...register("categoryId")}>
          {categories.map((category) => (
            <CategoryMapper data={category} key={category.id} />
          ))}
        </select>
        <span>Płeć</span>
        <select {...register("gender")}>
          <option value={"MALE"}>Mężczyzna</option>
          <option value={"FEMALE"}>Kobieta</option>
          <option value={"UNISEX"}>Uniwersalna</option>
        </select>
        <span>Formuła</span>
        <select {...register("formulaId")}>
          {formulas.map((formula) => (
            <option
              defaultValue={formula.id}
              value={formula.id}
              key={formula.id}
            >
              {formula.name + ": " + formula.description}
            </option>
          ))}
        </select>
        <button onSubmit={() => onSubmit}>Tworzę nowe zawody</button>
      </form>
    </div>
  );
}
