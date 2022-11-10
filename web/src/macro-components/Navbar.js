import { Component } from "react";
import "./styling/NavbarStyles.css";
import Logo from "../resources/img/climbingLogo1000.png";
import { Link } from "react-router-dom";

/***
 * This is navbar class, it wraps all functions and data necessary to correctly render navigation of the page.
 * {LOGO} "Telewspinaczka"                      Home    New Competition     Open Competition
 */
class Navbar extends Component {
  // TODO -- make image and title lead to main page

  render() {
    return (
      <nav className={"navbar"}>
        <img src={Logo} id={"logo"} alt={"none"} />
        <h1 className={"title"}>TeleClimb</h1>
        <div className={"nav-links"}>
          <Link className={"nav-link"} to={"/starting-page"}>
            Strona domowa
          </Link>
          <Link className={"nav-link"} to={"/new-competition"}>
            Nowe zawody
          </Link>
          <Link className={"nav-link"} to={"/open-competition"}>
            Otwórz zawody
          </Link>
        </div>
      </nav>
    );
  }
}

export default Navbar;
