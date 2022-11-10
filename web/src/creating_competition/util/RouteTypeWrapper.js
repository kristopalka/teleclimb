import "./styling/RouteTypeWrapperStyling.css";

/*
This wrapper takes in raw data from endpoint json
And parses it into polish version with coloring according to route type
 */
export const RouteTypeWrapper = ({ data }) => {
  let disciplines = new Map();
  disciplines.set("BOULDERING", "Bouldering");
  disciplines.set("LEAD", "Prowadzenie");
  disciplines.set("SPEED", "Bieg");

  return (
    <button id={data.discipline.toLowerCase()}>
      {disciplines.get(data.discipline)}
    </button>
  );
};
