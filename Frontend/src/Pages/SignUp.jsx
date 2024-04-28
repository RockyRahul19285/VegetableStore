import React, { useState } from "react";
import { Header } from "../components/Header";
import { toast } from 'react-toastify';
import { SetLocalStorage } from "../components/LocalStorage";


const SignUp = () => {
  const [user, setUser] = useState({
    username: "",
    email: "",
    password: "",
    number:"",
    address:"",
  });
  const [pass,setPass]=useState({
    confirmPassword: "",
  })
  const onToast = (s) => {
    if ('Account Created Successfully!!' === s) {
      toast.success(s, {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
        });
    }else{
      toast.error(s, {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
        });
    }
  }

  const handleSinup = async (e) => {
    // window.location.href = "/login";
    e.preventDefault();
    if (user.password === pass.confirmPassword) {
      const res = await fetch("http://localhost:8080/user/registerCustomer", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        
        body: JSON.stringify({
          ...user,
        }),
      });
      if (res.status === 200) {
        console.log(res);
        onToast("Account Created Successfully!!")
        setTimeout(() => {
          window.location.href = "/login";
        }, 2000);
       

      } else {
       
          onToast("Something went wrong!!")
      
          
      }

    }else{
      onToast("Password not match!!")
    }
        
  };

  return (
    <>

      <Header />
      <div className="d-flex flex-column justify-content-center" id="login-box">
        <div className="login-box-header">
          <h4
            style={{
              color: "rgb(139,139,139)",
              marginBottom: 0,
              fontWeight: 400,
              fontSize: 27,
            }}
          >
            Create an Account
          </h4>
        </div>
        <div className="d-flex flex-row align-items-center login-box-seperator-container">
          <div className="login-box-seperator" />
        </div>
        <div className="email-login" style={{ backgroundColor: "#ffffff" }}>
          <input
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Name"
            name="name"
            onChange={(e) => setUser({ ...user, username: e.target.value })}
            value={user.username}
            minLength={6}
          />
          <input
            type="email"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Email"
            name="email"
            onChange={(e) => setUser({ ...user, email: e.target.value })}
            value={user.email}
            minLength={6}
          />
          <input
            type="password"
            className="password-input form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Password"
            name="password"
            onChange={(e) => setUser({ ...user, password: e.target.value })}
            value={user.password}
            minLength={6}
          />
          <input
            type="password"
            className="password-input form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Confirm Password"
            onChange={(e) =>setPass({ ...pass, confirmPassword: e.target.value })}
            value={pass.confirmPassword}
            name="confirmPassword"
            minLength={6}
          />
           <input
            type="number"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Mobile Number"
            name="number"
            onChange={(e) => setUser({ ...user, number: e.target.value })}
            value={user.number}
            minLength={10}
          />
           <textarea
            type="text"
            className="email-imput form-control"
            style={{ marginTop: 10 }}
            required=""
            placeholder="Address"
            name="address"
            onChange={(e) => setUser({ ...user, address: e.target.value })}
            value={user.address}
            minLength={6}
          />
        </div>
        <div className="submit-row" style={{ marginBottom: 0, paddingTop: 0 }}>
          <button
            className="btn btn-primary d-block box-shadow w-100"
            id="submit-id-submit"
            type="submit"
            onClick={(e) => handleSinup(e)}
          >
            Sign Up
          </button>
          <div className="d-flex justify-content-between">
            <div
              className="form-check form-check-inline"
              id="form-check-rememberMe"
            >
             
            </div>
          </div>
        </div>
        <div
          id="login-box-footer"
          style={{ padding: "10px 20px", paddingBottom: 23, paddingTop: 5 }}
        >
          <p style={{ marginBottom: 0 }}>
            Alredy have an account?
            <a id="register-link" href="/login">
              Sign In!
            </a>
          </p>
        </div>
      </div>

    </>
  );
};

export default SignUp;