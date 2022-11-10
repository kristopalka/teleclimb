import "../styling/FormStyling.css";
import "./styling/AgeCategoryStyling.css";
import {useForm} from "react-hook-form";
import { CompetitionDisplayer } from "./CompetitionDisplayer";
import axios from "axios";
import { useEffect, useState } from "react";

/*
This function creates page for creating an entire competition.
 */
export default function CompetitionCreator(props) {
  const {
    register,
    handleSubmit,
    formState: {},
  } = useForm();

  const onSubmit = (data) => {
    postCompetition(data);
  };

  const [categories, setCategories] = useState([]);
  const [competitions, setCompetitions] = useState([]);

  function triggerUpdate() {
    props.nextLevelTrigger();
  }

  useEffect(() => {
    axios
      .get("http://localhost:8080/category/all")
      .then((response) => setCategories(response.data));
  }, []);

  useEffect(() => {
    axios
      .get("http://localhost:8080/competition/all")
      .then((response) => setCompetitions(response.data));
  }, []);

  function postCompetition(data) {
    let gender = undefined;
    if (data.gender === "Inna") {
      gender = "OTHER";
    }
    if (data.gender === "Mężczyzna") {
      gender = "MALE";
    }
    if (data.gender === "Kobieta") {
      gender = "FEMALE";
    }

    let discipline = undefined;
    if (data.discipline === "Bieg") {
      discipline = "SPEED";
    }
    if (data.discipline === "Bouldering") {
      discipline = "BOULDERING";
    }
    if (data.discipline === "Prowadzenie") {
      discipline = "LEAD";
    }

    let category = undefined;

    if (data.categoryId === "") {
      data.categoryId = 1;
    }

    for (let i = 0; i < categories.length; i++) {
      if (categories[i].id === parseInt(data.categoryId)) {
        category = categories[i];
      }
    }

    let formula = {
      description: data.formulaDescription,
      discipline: discipline,
      identifier: data.identifier,
      name: data.formulaName,
    };

    axios.post("http://localhost:8080/competition", {
      category: category,
      formula: formula,
      gender: gender,
      name: data.name,
    });

    axios
      .get("http://localhost:8080/competition/all")
      .then((response) => setCompetitions(response.data));
  }

  /*
    This is form for adding all age categories to the system.
    1. Starting age
    2. Ending age
    3. Description (optional)
     */

  return (
    <div>
      <div className={"age-category-div"}>
        <form className={"form"} onSubmit={handleSubmit(onSubmit)}>
          <span className={"title-span"}>Konkurencja</span>
          <span>Nazwa konkurencji</span>
          <input {...register("name", { required: true })} />
          <span>Płeć</span>
          <select {...register("gender")}>
            <option>Kobieta</option>
            <option>Mężczyzna</option>
            <option>Inna</option>
          </select>
          <span>Kategoria wiekowa</span>
          <select {...register("categoryId")}>
            {categories.map((category) => (
              <option key={category.id} value={category.id} defaultValue={1}>
                {category.name +
                  " (" +
                  category.shortName +
                  ") " +
                  category.fromAge +
                  " - " +
                  category.toAge}
              </option>
            ))}
          </select>
          <span className={"title-span"}>Formuła</span>
          <span>Nazwa formuły</span>
          <input {...register("formulaName")} />
          <span>Opis formuły</span>
          <input {...register("formulaDescription")} />
          <span>Identyfikator</span>
          <input type={"number"} {...register("identifier")} />
          <span>Dyscyplina</span>
          <select {...register("discipline")}>
            <option>Prowadzenie</option>
            <option>Bieg</option>
            <option>Bouldering</option>
          </select>
          <button onSubmit={onSubmit} id={"age-submit"}>
            Dodaj konkurencję
          </button>
        </form>
        <div className={"age-table"}>
          <CompetitionDisplayer data={competitions} />
        </div>
      </div>
      <div className={"btn-div"}>
        <button onClick={() => triggerUpdate()}>Przejdź dalej</button>
      </div>
    </div>
  );
}
