import { Component } from "react";
import "./styling/Home.css";
import { ImageWrapper } from "./home_content/ImageWrapper";
import { Navigate } from "react-router-dom";

/***
 * This is what users see when application starts.
 * First hero is general info + way to open helper
 * Second one is about commercial applications
 * Third one is about practical usage and some tips
 */
class Home extends Component {
  // TODO -- in future add a small gallery of events that used the system
  // TODO -- make this button really redirect

  componentDidMount() {
    localStorage.clear();
  }

  tryOut() {
    return <Navigate to={"/new-competition"}></Navigate>;
  }

  render() {
    return (
      <div className={"home-content"}>
        <div className={"text-wrapper"}>
          <h1>Telewspinaczka</h1>
          <h4>
            Oglądasz właśnie pierwszy polski system do sędziowania zawodów
            wspinaczkowych powstały w wyniku połączenia pasji trzech osób.
            System pozwala na niezwykle swobodne konfigurowanie, tworzenie i
            zarządzanie zawodami, ułatwiając cały proces w sposób wcześniej
            nieznany. Kliknij przycisk wypróbuj, a samouczek przeprowadzi Cię
            przez wszystkie kroki za rękę. I baw się dobrze!
          </h4>
          <button className={"check-button"} onClick={() => this.tryOut()}>
            Wypróbuj
          </button>
        </div>
        <div className={"img-wrapper"}>
          <ImageWrapper />
        </div>
      </div>
    );
  }
}

export default Home;
