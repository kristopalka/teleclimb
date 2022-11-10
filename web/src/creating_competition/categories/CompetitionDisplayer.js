import "./styling/AgeDisplayerStyling.css";
import noAgeCategories from "../../resources/img/calendar.gif";

export const CompetitionDisplayer = ({ data }) => {
  if (data.length > 0) {
    return (
      <div className={"age-displayer"}>
        <h2>Dostępne konkurencje</h2>
        {data.map((item) => (
          <button key={item.id}>
            {item.name + " " + item.gender + " " + item.discipline}
          </button>
        ))}
      </div>
    );
  } else {
    return (
      <div className={"no-age-categories"}>
        <img src={noAgeCategories} alt={""} />
        <h1>Dodaj konkurencje na zawodach</h1>
      </div>
    );
  }
};
