import { Component } from "react";
import "./styling/PageNotFound.css";

class PageNotFound extends Component {
  render() {
    return (
      <>
        <div className="page-not-found">
          <h1>404</h1>
          <h2>Strona, której szukasz nie istnieje</h2>
        </div>
      </>
    );
  }
}

export default PageNotFound;
