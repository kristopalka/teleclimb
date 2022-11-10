import "./RankingTablesStyling.css";

export const RankingTypeWrapper = (props) => {
  let ageMin = props.ageMin;
  let ageMax = props.ageMax;

  let gender = "";
  if (props.gender === "F") {
    gender = "kobiety";
  }
  if (props.gender === "M") {
    gender = "mężczyźni";
  }
  if (props.gender === "O") {
    gender = "inne";
  }

  let type = props.type;

  return (
    <h4>
      {ageMin} - {ageMax} | {gender} | {type}
    </h4>
  );
};
