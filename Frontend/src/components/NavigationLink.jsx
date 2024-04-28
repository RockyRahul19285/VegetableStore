import React from "react";
import { Link } from "react-router-dom";

const NavigationLink = ({ to, children }) => {
  return <Link to={to}>{children}</Link>;
};

export default NavigationLink;
