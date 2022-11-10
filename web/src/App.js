import "./App.css";
import { Route, Routes } from "react-router-dom";
import Layout from "../src/macro-components/Layout";
import { Fragment } from "react";
import Navbar from "./macro-components/Navbar";
import Home from "./pages/Home";
import NewCompetition from "./pages/NewCompetition";
import OpenCompetition from "./pages/OpenCompetition";
import PageNotFound from "./pages/PageNotFound";

/*
Main and starting class of the application
 */
function App() {
  return (
    <Layout>
      <Navbar />
      <Fragment>
        <Routes>
          <Route index element={<Home />} />
          <Route path="/starting-page" element={<Home />} />
          <Route path="/new-competition" element={<NewCompetition />} />
          <Route path="/open-competition" element={<OpenCompetition />} />
          <Route path="*" element={<PageNotFound />} />
        </Routes>
      </Fragment>
    </Layout>
  );
}

export default App;
