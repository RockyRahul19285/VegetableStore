import React, { useEffect } from 'react'
import { Header } from '../components/Header';
import { Hero } from '../components/HomeComponent/Hero';
import { ListProduct } from '../components/HomeComponent/ListProducts';
import { Footer } from '../components/Footer';
import { ToastContainer } from 'react-toastify';




export const Home = () => {

  useEffect(() => {
    window.scrollTo(0, 0)
  }
    , []);



  return (
    <>

      <Header />
      <main>
        <article>
          <Hero />
          <ListProduct />

        </article>
      </main>
      <Footer />
      <ToastContainer />

    </>

  )
}
