import "./ImageWrapperStyling.css";
import { InformationTextWrapper } from "./InformationTextWrapper";

export const ImageWrapper = () => {
  return (
    <div className={"image-wrapper"}>
      <div className={"single-image"} id={"div1"}>
        <InformationTextWrapper
          localization={"Kraków"}
          date={"27.02.2022"}
          name={"Zawody wydziałowe AGH"}
        />
      </div>
      <div className={"single-image"} id={"div2"}>
        <InformationTextWrapper
          localization={"Wrocław"}
          date={"3.03.2022"}
          name={"Zawody wspinaczkowe Dolnego Śląska"}
        />
      </div>
      <div className={"single-image"} id={"div3"}>
        <InformationTextWrapper
          localization={"Warszawa"}
          date={"12.07.2022"}
          name={"Mistrzostwa wspinaczkowe Polski"}
        />
      </div>
      <div className={"single-image"} id={"div4"}>
        <InformationTextWrapper
          localization={"Skała"}
          date={"23.09.2022"}
          name={"Zawody na jurze Krakowsko-Częstochowskiej"}
        />
      </div>
    </div>
  );
};
