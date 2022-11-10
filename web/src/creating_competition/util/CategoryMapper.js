/*
Wrapper to connect behavior of the ages that depend on edge cases.
 */
export const CategoryMapper = ({ data }) => {
  /*
    Staring age is used so we use "<"
     */
  if (data.fromAge === 0) {
    return (
      <option value={data.id}>
        {data.name + " (" + data.shortName + ") " + " < " + data.toAge + " lat"}
      </option>
    );
  }

  /*
    Ending age is present so we use ">".
     */
  if (data.toAge === Number.MAX_VALUE) {
    return (
      <option value={data.id}>
        {data.name +
          " (" +
          data.shortName +
          ") " +
          " > " +
          data.fromAge +
          " lat"}
      </option>
    );
  }

  /*
    Default case
     */
  return (
    <option value={data.id}>
      {data.name +
        " (" +
        data.shortName +
        ") " +
        data.fromAge +
        "-" +
        data.toAge +
        " lat"}
    </option>
  );
};
