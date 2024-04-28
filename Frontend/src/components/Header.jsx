import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { GetLocalStorage, RemoveLocalStorage } from "./LocalStorage";
import * as Scroll from 'react-scroll';

// Or Access Link,Element,etc as follows
let Link = Scroll.Link;



export const Header = () => {
  

  const [islogin, setislogin] = useState(!!GetLocalStorage("CustomerId"));
  
  const quantity = GetLocalStorage("quant") ;
  const navigate = useNavigate();
  const handleRedirect = () => {
    if (islogin) {
      navigate(`/cart`);
   
    } else {
      navigate(`/login`);

    }
  };

  const handalLogout = () => {
    RemoveLocalStorage("CustomerId");
   
    setislogin(false)
    navigate(`/`);
  };

  

    


  return (
    <header className="header" data-header="">
      <div className="nav-wrapper">
        <div className="container">
          <h1 className="h1">
            <a href="/" className="logo">
            Pure <span className="span">Earth </span> Organics
            </a>
          </h1>
          <button
            className="nav-open-btn"
            aria-label="Open Menu"
            data-nav-open-btn=""
          >
            <ion-icon name="menu-outline" />
          </button>
          <nav className="navbar" data-navbar="">
            <button
              className="nav-close-btn"
              aria-label="Close Menu"
              data-nav-close-btn=""
            >
              <ion-icon name="close-outline" />
            </button>
            <ul className="navbar-list">
              <li>
                <a href="/" className="navbar-link">
                  Home
                </a>
              </li>
              <li>
                {/* <Link to="/shop" activeClass="active" className="navbar-link"  >
            Shop
              </Link> */}
                <a href="/shop" className="navbar-link">
                  Shop
                </a>
              </li>
             
              {islogin ? (
              <li>
                <a href="/orders" className="navbar-link">
                 My Orders
                </a>
              </li>
              ):(<></>)}

              <li>
                <Link
                  activeClass="active"
                  className="navbar-link"
                  smooth="linear"
                  spy
                  to="products"
                  offset={-30}
                >
                  Products
                </Link>
              </li>
              <li>
                <Link
                  activeClass="active"
                  className="navbar-link"
                  smooth="linear"
                  spy
                  to="contact"
                  offset={-30}
                >
                  Contact
                </Link>
              </li>
            </ul>
          </nav>
          <div className="header-action">
            <div className="search-wrapper" data-search-wrapper="">
              <button
                className="header-action-btn"
                aria-label="Toggle search"
                data-search-btn=""
              >
                <ion-icon name="search-outline" className="search-icon" />
                {/* <ion-icon name="close-outline" className="close-icon" /> */}
              </button>
              <div className="input-wrapper">
                <input
                  type="search"
                  name="search"
                  placeholder="Search here"
                  className="search-input"
                />
                <button className="search-submit" aria-label="Submit search">
                  <ion-icon name="search-outline" />
                </button>
              </div>
            </div>
            {!islogin  ? (
              <button
                className="header-action-btn"
                aria-label="Open shopping cart"
                data-panel-btn="cart"
                onClick={handleRedirect}
              >
                <ion-icon name="person-circle-outline"></ion-icon>
              </button>
            ) : (
              <>
                <button
                  className="header-action-btn"
                  aria-label="Open shopping cart"
                  data-panel-btn="cart"
                  onClick={handleRedirect}
                >
                  <ion-icon name="basket-outline" />
                  <data className="btn-badge" value={2}>
                   {quantity}
                  </data>
                </button>
              </>
              
            )}
           {islogin ? <button
                  className="header-action-btn"
                  aria-label="Open shopping cart"
                  data-panel-btn="cart"
                  onClick={() => handalLogout()}
                >
                 <ion-icon name="log-out-outline"></ion-icon>
                </button>:<></>}
          </div>
        </div>
      </div>
    </header>
  );
};
