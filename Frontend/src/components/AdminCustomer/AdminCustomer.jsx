import { useState,useEffect } from "react";
import { AdminHeader } from "../AdminComponent/AdminHeader";
import { Footer } from "../Footer";

export const AdminCustomr=()=>{
    const [custData, setCustData] = useState([]);

    useEffect(() => {
        const fetchCustData = async () => {
          try {
            const response = await fetch("http://localhost:8080/user/allCustomers");
            const data = await response.json();
            setCustData(data);
          } catch (error) {
            console.error("Error fetching orders:", error);
          }
        };
    
        fetchCustData();
      }, []);
    

    return(
        <>
         <AdminHeader />
      <div style={{paddingBottom:'3rem'}}>
        <h1>All Customers </h1>

        <table className="admin-order-table">
          <thead>
            <tr>
              <th>Customer Id</th>
              <th>Customer Username</th>
              <th>Email</th>
              <th>Phone Number</th>
              <th>Address</th>
            </tr>
          </thead>
          <tbody>
            {custData.map((customer) => (
              <tr key={customer.id}>
                <td>{customer.id}</td>
                <td>{customer.username}</td>
                <td>{customer.email}</td>
                <td>{customer.number}</td>
                <td>{customer.address}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Footer />
        </>
    )
}