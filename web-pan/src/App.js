import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";

import "./App.css";

import Login from "./Pages/Login/index";

function App() {
  return (
      <Router>
        <div className="App">
          <Route exact path="/" component={Login} />
        </div>
      </Router>
  );
}

export default App;
