
import React, { useState, useEffect } from "react";
import { AdminHeader } from "../AdminComponent/AdminHeader";
import { Footer } from "../Footer";
import { Button } from "react-bootstrap";
import './AdminOrders.css';
import { toast } from 'react-toastify';

export const AdminOrder = () => {
  const [orders, setOrders] = useState([]);

  const onToast = (s) => {
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
  }

  const handleAccept = async (orderId) => {
    try {
      const response = await fetch(`http://localhost:8080/orders/accept/${orderId}`, {
        method: 'PUT',
      });
      if(response.ok){
        onToast("Order Accepted");
      }
    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  }
  const handleDelivered = async (orderId) => {
    try {
      const response = await fetch(`http://localhost:8080/orders/deliver/${orderId}`, {
        method: 'PUT',
      });
      if(response.ok){
        onToast("Order Delivered");
      }

    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  }
  const handleDelete = async (orderId) => {
    try {
      const response = await fetch(`http://localhost:8080/orders/delete/${orderId}`, {
        method: 'DELETE',
      });
      if(response.ok){
        onToast("Order Deleted");
      }

    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  }
  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await fetch("http://localhost:8080/orders/findAll");
        const data = await response.json();
        setOrders(data);
      } catch (error) {
        console.error("Error fetching orders:", error);
      }
    };

    fetchOrders();
  }, []);

  return (
    <>
      <AdminHeader />
      <div className="adminOrder" style={{paddingBottom:'3rem'}}>
        <h1>Orders Page</h1>

        <table className="admin-order-table">
          <thead>
            <tr>
              <th>Order Id</th>
              <th>Customer Id</th>
              <th>CartItems Id</th>
              <th>Total Price</th>
              <th>Accepted</th>
              <th>Delivered</th>
              <th>Delete Order</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order) => (
              <tr key={order.orderId}>
                <td>{order.orderId}</td>
                <td>{order.customerId}</td>
                <td>{order.cartItems.join(", ")}</td>
                <td>Rs{" "}{order.totalPrice.toFixed(2)}</td>
                <td>
                  <Button variant="success" onClick={() => handleAccept(order.orderId)}>Accept</Button>
                </td>
                <td>
                  <Button variant="info" onClick={() => handleDelivered(order.orderId)}>Delivered</Button>
                </td>
                <td>
                  <Button variant="danger" onClick={() => handleDelete(order.orderId)}>Delete</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Footer />
    </>
  );
};

