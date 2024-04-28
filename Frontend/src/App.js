import { Routes, Route } from "react-router-dom";
import { Home } from "./Pages/Home";
import Shop from "./Pages/Shop";
import Login from "./Pages/Login";
import SignUp from "./Pages/SignUp";
import { Cart1 } from "./Pages/Cart1";
import { Protected } from "./components/Protected";
import { useState } from "react";
import { GetLocalStorage } from "./components/LocalStorage";
import { Orders } from "./Pages/Orders";
import { AdminLogin } from "./Pages/AdminLogin";
import { AdminComp } from "./components/AdminComponent/AdminComp";
import { AdminOrder } from "./components/AdminOrders/AdminOrder";
import { AdminCustomr } from "./components/AdminCustomer/AdminCustomer";
import { Admins } from "./components/Admins/Admins";
import { AdminProduct } from "./components/AdminProducts/AdminProduct";

const App = () =>{
  const [isSignedIn, setIsSignedIn] = useState(!!GetLocalStorage("CustomerId"));
  const [isAdminSignIn,setAdminSignedIn] = useState(!!GetLocalStorage("AdminId"));
  return (
    <>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/shop" element={<Shop />} />
          <Route
            path="/cart"
            element={
              <Protected isSignedIn={isSignedIn}>
                <Cart1 />
               </Protected>
            }
          />
          <Route
            path="/adminpanel"
            element={
              <Protected isSignedIn={isAdminSignIn}>
                <AdminComp />
               </Protected>
            }
          />
          <Route
            path="/orders-api"
            element={
              <Protected isSignedIn={isAdminSignIn}>
                <AdminOrder />
               </Protected>
            }
          />
          <Route
            path="/customer-api"
            element={
              <Protected isSignedIn={isAdminSignIn}>
                <AdminCustomr />
               </Protected>
            }
          />
          <Route
            path="/admin-api"
            element={
              <Protected isSignedIn={isAdminSignIn}>
                <Admins/>
               </Protected>
            }
          />
           <Route
            path="/product-api"
            element={
              <Protected isSignedIn={isAdminSignIn}>
                <AdminProduct/>
               </Protected>
            }
          />

          <Route path="/login" element={<Login />} />
          <Route path = "/adminlogin" element = {<AdminLogin/>}/>
          <Route path="/signup" element={<SignUp />} />
          <Route path="/orders" element={
            <Protected isSignedIn={isSignedIn}>
              <Orders />
            </Protected>
          } />
        </Routes>
    </>
  );
}
export default App;