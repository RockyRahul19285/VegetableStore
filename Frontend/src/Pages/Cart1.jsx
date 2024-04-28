import React, { useEffect, useState } from "react";
import { Header } from "../components/Header";
import { Footer } from "../components/Footer";
import { GetLocalStorage, SetLocalStorage } from "../components/LocalStorage";
import { Items } from "../components/CartComponent/Items";
import { toast } from 'react-toastify';

export const Cart1 = () => {
  useEffect(() => { window.scrollTo(0, 0) }, []);
  
      const [data, setdata] = useState();
      const[item,setItem]=useState([]);
      const [loading, setLoading] = useState(9);
      const [totalAmount, setTotalAmount] = useState(0);
      const[quantity,setQuantity] = useState();
      const custId = GetLocalStorage("CustomerId");

      const onToast = () => {
        toast.success('Placed order Successfully', {
          position: "bottom-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "light",
          });
      }

      const fetchCart = async () => {
        const res = await fetch(`http://localhost:8080/cart/getByCustomerId/${custId}`, {
          method:"GET",
        });
        const data = await res.json();
        setTotalAmount(data.totalPrice);
        setQuantity(data.totalQuantity);
        SetLocalStorage("quant",data.totalQuantity);
        if (data.productIds) {
          const productPromises = data.productIds.map(async (productId) => {
              const productRes = await fetch(`http://localhost:8080/product/getById/${productId}`, {
                  method: "GET",
              });
              const productData = await productRes.json();
              return productData;
          });
  
          const productDetails = await Promise.all(productPromises);
          setItem(productDetails);
      } else {
          setItem([]);
      }
     
      };
     

      useEffect(() => {
        fetchCart();

      }, [loading]);

      

      const createOrder = async (e) => {
        
        const res = await fetch(`http://localhost:8080/orders/add/${custId}`, {
          method: "POST",
        });
        onToast();
        setTimeout(() => {
          window.location.href = '/shop';
      }, 2000);
        const da = await res.json();
        setdata(da);
        return da;
        
      }

     





  return (
    <>
      <Header />
      <div className="shopping-cart">
        <div className="px-4 px-lg-0">
          <div className="pb-5">
            <div className="container">
              <div className="row">
                <div className="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                  {/* Shopping cart table */}
                  <div className="table-responsive">
                    <table className="table">
                      <thead>
                        <tr>
                          <th scope="col" className="border-0 bg-light">
                            <div className="p-2 px-3 text-uppercase">
                              Product
                            </div>
                          </th>
                          <th scope="col" className="border-0 bg-light">
                            <div className="py-2 text-uppercase">Price</div>
                          </th>
                          <th scope="col" className="border-0 bg-light">
                            <div className="py-2 text-uppercase">Remove</div>
                          </th>
                        </tr>
                      </thead>
                      <tbody>


                    {item && item.length > 0 ?   item.map((elem,index) => {
                          return (
                            <>

                              <Items
                                key={index} prop={elem} setLoading={setLoading} />
                              </>
                          )})
                        :<></>}

                      </tbody>
                    </table>
                  </div>
                  {/* End */}
                </div>
              </div>
              <div className="row py-5 p-4 bg-white rounded shadow-sm">
                <div className="col-lg-6"></div>
                <div className="col-lg-6">
                  <div className="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">
                    Order summary{" "}
                  </div>
                  <div className="p-4">
                    <p className="font-italic mb-4">
                      Shipping and additional costs are calculated based on
                      values you have entered.
                    </p>
                    <ul className="list-unstyled mb-4">
                      <li className="d-flex justify-content-between py-3 border-bottom">
                        <strong className="text-muted">Order Subtotal </strong>
                        <strong>Rs {totalAmount}</strong>
                      </li>
                      <li className="d-flex justify-content-between py-3 border-bottom">
                        <strong className="text-muted">Number of items </strong>
                        <strong> {quantity}</strong>
                      </li>
                      <li className="d-flex justify-content-between py-3 border-bottom">
                        <strong className="text-muted">
                          Shipping and handling
                        </strong>
                        <strong>Rs 100.00</strong>
                      </li>
                      <li className="d-flex justify-content-between py-3 border-bottom">
                        <strong className="text-muted">Tax</strong>
                        <strong>Rs 0.00</strong>
                      </li>
                      <li className="d-flex justify-content-between py-3 border-bottom">
                        <strong className="text-muted">Total</strong>
                        <h3 className="font-weight-bold">Rs {totalAmount +100}</h3>
                      </li>
                    </ul>
                    <button
                      href=""
                      className="btn btn-dark rounded-pill py-2 btn-block"
                      onClick={(e) => createOrder(e)}
                    >
                      Place Order as Cash On Delivery
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};
