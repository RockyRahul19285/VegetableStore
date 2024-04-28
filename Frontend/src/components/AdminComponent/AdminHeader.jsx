import { GetLocalStorage } from "../LocalStorage";
import { RemoveLocalStorage } from "../LocalStorage";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export const AdminHeader = () => {
  const [adminLogin, SetAdminLogin] = useState(!!GetLocalStorage("AdminId"));
  const navigate = useNavigate();

  const handleLogout = () => {
    RemoveLocalStorage("AdminId");
    SetAdminLogin(false);
    navigate(`/`);
  }

  return (
    <>
      <header className="header" data-header="">
        <div className="nav-wrapper">
          <div className="container">
            <h1 className="h1">
              <a href="/" className="logo">
                Pure <span className="span">Earth </span> Organics
              </a>
            </h1>

            <nav className="navbar" data-navbar="">
              <ul className="navbar-list">
                Welcome Admin
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
              {adminLogin ? <button
                className="header-action-btn"
                aria-label="Open shopping cart"
                data-panel-btn="cart"
                onClick={() => handleLogout()}
              >
                <ion-icon name="log-out-outline"></ion-icon>
              </button> : <></>}
            </div>
          </div>
        </div>
      </header>
    </>

  )

}