import { Component } from "react";
import "./styling/FooterStyling.css";

class Footer extends Component {
  render() {
    return (
      <div className={"footer"}>
        <ul>
          <p>
            Aplikacja webowa słżąca do konfigurowania, budowania i sędziowania
            zawodów wspinaczkowych.
          </p>
          <p>Wersja 1.0.0</p>
          <p>Autorzy: Andrzej Krzywda, Krzysztof Pałka, Szymon Bobrowski</p>
          <p>Kraków, grudzień 2021 - maj 2022</p>
        </ul>
        <ul>
          <p>Aplikacja wykorzytuje API Polskiego Związku Alpinizmu</p>
          <p>
            Projekt open-source,{" "}
            <a href={"https://github.com/AndrzejKrzywda00/Telewspinanie-Web"}>
              github
            </a>
          </p>
          <p>
            Do pełnego użytku potrzebna jest aplikacja mobilna -{" "}
            <a href={"https://github.com/AndrzejKrzywda00"}>github</a>
          </p>
          <p>
            Grafiki wykorzystane w projektcie (użytkowanie zgodnie z licencją)
            LINK
          </p>
        </ul>
      </div>
    );
  }
}

export default Footer;
